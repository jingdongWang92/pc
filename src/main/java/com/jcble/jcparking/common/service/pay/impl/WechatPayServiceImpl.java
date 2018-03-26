package com.jcble.jcparking.common.service.pay.impl;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcble.jcparking.common.CommonConstants;
import com.jcble.jcparking.common.CommonEnum;
import com.jcble.jcparking.common.dao.pay.PayLogDao;
import com.jcble.jcparking.common.dao.pay.WxPayCallbackLogDao;
import com.jcble.jcparking.common.dto.request.PayRecordReqDto;
import com.jcble.jcparking.common.exception.ParkingServiceException;
import com.jcble.jcparking.common.model.pay.PayLog;
import com.jcble.jcparking.common.model.pay.WxPayCallbackLog;
import com.jcble.jcparking.common.service.pay.PayLogService;
import com.jcble.jcparking.common.service.pay.WechatPayService;
import com.jcble.jcparking.common.utils.GetSystemConfig;
import com.jcble.jcparking.common.utils.MathUtil;

import baseproj.common.util.DateUtil;
import weixin.popular.api.PayMchAPI;
import weixin.popular.bean.paymch.MchBaseResult;
import weixin.popular.bean.paymch.MchPayNotify;
import weixin.popular.bean.paymch.SecapiPayRefund;
import weixin.popular.bean.paymch.SecapiPayRefundResult;
import weixin.popular.bean.paymch.Unifiedorder;
import weixin.popular.bean.paymch.UnifiedorderResult;
import weixin.popular.client.LocalHttpClient;
import weixin.popular.support.ExpireKey;
import weixin.popular.support.expirekey.DefaultExpireKey;
import weixin.popular.util.SignatureUtil;
import weixin.popular.util.StreamUtils;
import weixin.popular.util.XMLConverUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class WechatPayServiceImpl implements WechatPayService {

	/** logger */
	protected static final Logger logger = LoggerFactory.getLogger(WechatPayServiceImpl.class);

	//重复通知过滤
    private static ExpireKey expireKey = new DefaultExpireKey();
	@Autowired
	private PayLogDao payLogDao;
	@Autowired
	private WxPayCallbackLogDao wxPayCallbackLogDao;
	@Autowired
	private PayLogService payLogService;
	protected static Header xmlHeader = new BasicHeader("Content-Type", ContentType.APPLICATION_XML.toString());

	@Override
	public SecapiPayRefundResult refund(String orderNo) throws Exception {
		SecapiPayRefundResult result = new SecapiPayRefundResult();
		try {
			//根据停车订单号获取交易支付记录
			PayLog payLog = payLogDao.getLatestPayRecordByOrderNo(orderNo);
			
			SecapiPayRefund secapiPayRefund = new SecapiPayRefund();
			secapiPayRefund.setAppid(GetSystemConfig.getValueByKey("weixin_pay_appid"));
			secapiPayRefund.setMch_id(GetSystemConfig.getValueByKey("weixin_mch_id"));
			secapiPayRefund.setNonce_str(UUID.randomUUID().toString().replaceAll("-", ""));
			secapiPayRefund.setOp_user_id(GetSystemConfig.getValueByKey("weixin_mch_id"));
			secapiPayRefund.setOut_refund_no(payLog.getOrderNo());
			secapiPayRefund.setOut_trade_no(payLog.getOrderNo());
			secapiPayRefund.setRefund_fee(MathUtil.mul(payLog.getAmount(), new BigDecimal(100),0).intValue());
			secapiPayRefund.setTotal_fee(MathUtil.mul(payLog.getAmount(), new BigDecimal(100),0).intValue());
			String apikey = GetSystemConfig.getValueByKey("weixin_mch_api_key");
//		    URL url = new URL(GetSystemConfig.getValueByKey("weixin_mch_cert_url"));
//		    LocalHttpClient.initMchKeyStore(secapiPayRefund.getMch_id(), new BufferedInputStream(url.openStream()));
		    LocalHttpClient.initMchKeyStore(secapiPayRefund.getMch_id(),GetSystemConfig.getValueByKey("weixin_pay_cert_url"));
		    result = PayMchAPI.secapiPayRefund(secapiPayRefund, apikey);
			if("SUCCESS".equals(result.getReturn_code())) {
				logger.info("微信处理退款成功:"+result.getReturn_msg());
			} else {
				logger.info("微信处理退款失败:"+result.getReturn_msg());
			}
		} catch (Exception e) {
			logger.info("微信处理退款失败:"+result.getReturn_msg());
			throw e;
		}
		return result;
		
	}
	
	@Override
	public UnifiedorderResult getWxPayOrderInfo(String ipAddr, String tradeNo,String payFee,String orderType) throws Exception {
		Double fee = new Double(payFee);
		if(fee <= 0) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10029);
		}
		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(GetSystemConfig.getValueByKey("weixin_pay_appid"));
		unifiedorder.setMch_id(GetSystemConfig.getValueByKey("weixin_mch_id"));
		unifiedorder.setNotify_url(GetSystemConfig.getValueByKey("weixin_pay_notify_url"));
		unifiedorder.setNonce_str(UUID.randomUUID().toString().replaceAll("-", ""));
		String key = GetSystemConfig.getValueByKey("weixin_mch_api_key");
		unifiedorder.setBody("停车费");
		unifiedorder.setAttach(orderType);
		unifiedorder.setOut_trade_no(tradeNo);
		unifiedorder.setTotal_fee(MathUtil.mul(new BigDecimal(payFee), new BigDecimal(100),0).intValue()+"");
		unifiedorder.setSpbill_create_ip(ipAddr);
		unifiedorder.setTrade_type("APP");
		UnifiedorderResult result = PayMchAPI.payUnifiedorder(unifiedorder, key);
		return result;
	}
	
	@Override
	public void handleWxPayNotify(HttpServletRequest request, HttpServletResponse response, String source) {
		try {
			//获取请求数据
			String xmlData = StreamUtils.copyToString(request.getInputStream(), Charset.forName("utf-8"));
			logger.info(xmlData);
			//将XML转为MAP,确保所有字段都参与签名验证
			Map<String,String> mapData = XMLConverUtil.convertToMap(xmlData);
			//转换数据对象
			MchPayNotify payNotify = XMLConverUtil.convertToObject(MchPayNotify.class,xmlData);
			//已处理 去重
			if(expireKey.exists(payNotify.getTransaction_id())){
	                        MchBaseResult baseResult = new MchBaseResult();
				baseResult.setReturn_code("SUCCESS");
				baseResult.setReturn_msg("OK");
				logger.info("处理重复通知");
				response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
				return;
			}
			//签名验证
			if(SignatureUtil.validateSign(mapData,GetSystemConfig.getValueByKey("weixin_mch_api_key"))){
				//处理相关业务,并保存日志
				saveWxPayCallbackLog(payNotify);
				
			    payNotify.buildDynamicField(mapData);
				expireKey.add(payNotify.getTransaction_id());
				MchBaseResult baseResult = new MchBaseResult();
				baseResult.setReturn_code("SUCCESS");
				baseResult.setReturn_msg("OK");
				logger.info("签名验证SUCCESS");
				response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
			}else{
				MchBaseResult baseResult = new MchBaseResult();
				baseResult.setReturn_code("FAIL");
				logger.info("签名验证FAIL");
				baseResult.setReturn_msg("ERROR");
				response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveWxPayCallbackLog(MchPayNotify payNotify) throws Exception {
		WxPayCallbackLog wxPayCallbackLog = new WxPayCallbackLog();
		wxPayCallbackLog.setAppid(payNotify.getAppid());
		wxPayCallbackLog.setResult_code(payNotify.getResult_code());
		wxPayCallbackLog.setReturn_code(payNotify.getReturn_code());
		wxPayCallbackLog.setReturn_msg(payNotify.getReturn_msg());
		wxPayCallbackLog.setMch_id(payNotify.getMch_id());
		wxPayCallbackLog.setDevice_info(payNotify.getDevice_info());
		wxPayCallbackLog.setAttach(payNotify.getAttach());
		wxPayCallbackLog.setNonce_str(payNotify.getNonce_str());
		wxPayCallbackLog.setSign(payNotify.getSign());
		wxPayCallbackLog.setErr_code(payNotify.getErr_code());
		wxPayCallbackLog.setErr_code_des(payNotify.getErr_code_des());
		wxPayCallbackLog.setOpenid(payNotify.getOpenid());
		wxPayCallbackLog.setIs_subscribe(payNotify.getIs_subscribe());
		wxPayCallbackLog.setTrade_type(payNotify.getTrade_type());
		wxPayCallbackLog.setBank_type(payNotify.getBank_type());
		wxPayCallbackLog.setTime_end(payNotify.getTime_end());
		wxPayCallbackLog.setTotal_fee(payNotify.getTotal_fee());
		wxPayCallbackLog.setTransaction_id(payNotify.getTransaction_id());
		wxPayCallbackLog.setFee_type(payNotify.getFee_type());
		wxPayCallbackLog.setCash_fee(payNotify.getCash_fee());
		wxPayCallbackLog.setCash_fee_type(payNotify.getCash_fee_type());
		wxPayCallbackLog.setCoupon_count(payNotify.getCoupon_count());
		wxPayCallbackLog.setCoupon_fee(payNotify.getCoupon_fee());
		wxPayCallbackLog.setOut_trade_no(payNotify.getOut_trade_no());
		wxPayCallbackLog.setCreatetime(DateUtil.getDateTime());
		
		WxPayCallbackLog log = wxPayCallbackLogDao.getWxPayLog(wxPayCallbackLog);
		if(log == null) {
			PayRecordReqDto payLog = new PayRecordReqDto();
			payLog.setPayWay(CommonEnum.PayWay.WxPay.code);
			payLog.setTradeStatus(payNotify.getResult_code());
			payLog.setAmount(MathUtil.div(new BigDecimal(payNotify.getTotal_fee()), new BigDecimal(100)));
			payLog.setOrderNo(payNotify.getOut_trade_no());
			payLog.setType(payNotify.getAttach());
			payLog.setTradeStatus(payNotify.getResult_code());
			payLog.setPayStatus(CommonConstants.WXPAY_SUCCESS_RESULT_CODE.equals(payNotify.getResult_code()) ? 
					CommonEnum.PayStatus.Payed.code : CommonEnum.PayStatus.UnPay.code);
			payLogService.payFeedback(payLog);
		}
		
		wxPayCallbackLogDao.insert(wxPayCallbackLog);
	}

}
