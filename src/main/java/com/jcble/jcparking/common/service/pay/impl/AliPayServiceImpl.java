package com.jcble.jcparking.common.service.pay.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.jcble.jcparking.common.CommonConstants;
import com.jcble.jcparking.common.CommonEnum;
import com.jcble.jcparking.common.dao.pay.AliPayCallbackLogDao;
import com.jcble.jcparking.common.dao.pay.PayLogDao;
import com.jcble.jcparking.common.dto.request.PayRecordReqDto;
import com.jcble.jcparking.common.exception.ParkingServiceException;
import com.jcble.jcparking.common.model.pay.AlipayCallbackLog;
import com.jcble.jcparking.common.model.pay.PayLog;
import com.jcble.jcparking.common.service.pay.AliPayService;
import com.jcble.jcparking.common.service.pay.PayLogService;
import com.jcble.jcparking.common.utils.GetSystemConfig;

import baseproj.component.log.LoggerAdapter;
import baseproj.component.log.LoggerService;

@Service
@Transactional(rollbackFor = Exception.class)
public class AliPayServiceImpl implements AliPayService {

	private final LoggerService logger = LoggerAdapter.getLoggerService(getClass());

	private static final String CHARSET = "UTF-8";

	@Autowired
	private AliPayCallbackLogDao aliPayCallbackLogDao;
	@Autowired
	private PayLogDao payLogDao;
	@Autowired
	private PayLogService payLogService;

	private static final String ALIPAY_GATEWAY_URL = "https://openapi.alipay.com/gateway.do";

	@Override
	public String getAlipayOrderInfo(String tradeNo, String totalAmount,String orderType) throws AlipayApiException {
		Double fee = new Double(totalAmount);
		if(fee <= 0) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10029);
		}
		String orderInfo = "";
		// 实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_GATEWAY_URL,
				GetSystemConfig.getValueByKey("alipay_app_id"), GetSystemConfig.getValueByKey("alipay_app_private_key"),
				"json", CHARSET, GetSystemConfig.getValueByKey("alipay_public_key"), "RSA2");
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody(orderType);
		model.setSubject("停车费");
		model.setOutTradeNo(tradeNo);
		model.setTimeoutExpress("30m");
		model.setTotalAmount(totalAmount);
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(GetSystemConfig.getValueByKey("alipay_app_notify_url"));
		// 这里和普通的接口调用不同，使用的是sdkExecute
		AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
		// 就是orderString,直接给客户端请求，无需再做处理。
		orderInfo = response.getBody();
		return orderInfo;
	}

	@Override
	public String handleAliPayNotify(HttpServletRequest request) {
		String respStr = "failed";
		try {
			// 订单号
			String orderNo = request.getParameter("out_trade_no");
			String trade_status = request.getParameter("trade_status");
			
			// 验证消息是否是支付宝发出的合法消息
			boolean isVerify = verify(request);
			logger.info("alipay isVerify==>" + isVerify);
			if (isVerify) {
				// 保存支付回调记录
				saveAliPayCallbackLog(request);
				if (CommonConstants.ALIPAY_TRADE_SUCCESS.equals(trade_status) || CommonConstants.ALIPAY_TRADE_CLOSE.equals(trade_status)) {
					respStr = "success";
					logger.info("Handle alipay info success,payno=" + orderNo);
				} else {
					logger.error("alipay callback===>trade_status=" + trade_status);
				}
			} else {
				// 消息不合法,放弃这次响应
				logger.error("非法的支付回调消息!订单号=" + orderNo);
			}
		} catch (Exception e) {
			logger.error("接收支付宝的回调出现异常!", e);
		}
		return respStr;
	}

	/**
	 * 保存支付宝回调日志
	 * 
	 * @param aliPayLog
	 * @throws Exception
	 */
	public void saveAliPayCallbackLog(HttpServletRequest request) throws Exception {
		try {
			String orderType = request.getParameter("body");// 订单描述
			String buyer_account = request.getParameter("buyer_logon_id");// 付款人账号
			String buyer_id = request.getParameter("buyer_id");// 付款人ID
			String notify_id = request.getParameter("notify_id");// 返回通知ID
			String notify_time = request.getParameter("notify_time");// 返回通知时间
			String notify_type = request.getParameter("notify_type");// 返回通知类型
			String orderNo = request.getParameter("out_trade_no");// 订单号
			String seller_account = request.getParameter("seller_email");// 商家支付账号
			String seller_id = request.getParameter("seller_id");// 商家支付ID
			String order_name = request.getParameter("subject");// 订单名称
			String total_amount = request.getParameter("total_amount");// 支付费用
			String refund_fee = request.getParameter("refund_fee");// 支付费用
			String trade_no = request.getParameter("trade_no");// 交易流水号
			String trade_status = request.getParameter("trade_status");// 交易状态
			
			AlipayCallbackLog log = aliPayCallbackLogDao.getLogByNotifyId(notify_id);
			if(log == null) {
				PayRecordReqDto payLog = new PayRecordReqDto();
				payLog.setPayWay(CommonEnum.PayWay.Alipay.code);
				payLog.setTradeStatus(trade_status);
				if(CommonConstants.ALIPAY_TRADE_SUCCESS.equals(trade_status)) {
					payLog.setPayStatus(CommonEnum.PayStatus.Payed.code);
				} else if(CommonConstants.ALIPAY_TRADE_CLOSE.equals(trade_status)) {
					payLog.setPayStatus(CommonEnum.PayStatus.Refund.code);
				} else if(CommonConstants.ALIPAY_TRADE_FAIL.equals(trade_status)) {
					payLog.setPayStatus(CommonEnum.PayStatus.UnPay.code);
				}
				payLog.setAmount(new BigDecimal(total_amount));
				payLog.setOrderNo(orderNo);
				payLog.setType(orderType);
				payLogService.payFeedback(payLog);
			}

			// 存储支付宝返回数据
			AlipayCallbackLog aliPayLog = new AlipayCallbackLog();
			aliPayLog.setAliTradeNo(trade_no);
			aliPayLog.setBuyerAccount(buyer_account);
			aliPayLog.setBuyerId(buyer_id);
			aliPayLog.setOrderNo(orderNo);
			aliPayLog.setNotifyId(notify_id);
			aliPayLog.setNotifyType(notify_type);
			aliPayLog.setOrderName(order_name);
			aliPayLog.setOrderType(orderType);
			aliPayLog.setTotalAmount(total_amount);
			aliPayLog.setRefundFee(refund_fee);
			aliPayLog.setSellerAccount(seller_account);
			aliPayLog.setSellerId(seller_id);
			aliPayLog.setTradeStatus(trade_status);
			aliPayLog.setNotifyTime(notify_time);
			aliPayCallbackLogDao.insert(aliPayLog);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * 验证支付宝异步通知信息
	 * 
	 * @param request
	 * @return
	 * @throws AlipayApiException
	 */
	private boolean verify(HttpServletRequest request) throws AlipayApiException {
		// 获取支付宝POST过来反馈信息
		Map<String, String> requestParamMap = new HashMap<String, String>();
		Set<String> keySet = request.getParameterMap().keySet();
		for (Object keyObject : keySet.toArray()) {
			String[] valueArr = (String[]) request.getParameterMap().get(keyObject);
			requestParamMap.put((String) keyObject, valueArr[0]);
		}
		String publicKey = GetSystemConfig.getValueByKey("alipay_public_key");
		// 切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
		return AlipaySignature.rsaCheckV1(requestParamMap, publicKey, CHARSET, "RSA2");
	}

	@Override
	public AlipayTradeRefundResponse refund(String orderNo) throws Exception {
		AlipayTradeRefundResponse response = new AlipayTradeRefundResponse();
		//根据停车订单号获取交易支付记录
		PayLog payLog = payLogDao.getLatestPayRecordByOrderNo(orderNo);
		if (payLog == null) {
			return response;
		}
		AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_GATEWAY_URL,
				GetSystemConfig.getValueByKey("alipay_app_id"), GetSystemConfig.getValueByKey("alipay_app_private_key"),
				"json", CHARSET, GetSystemConfig.getValueByKey("alipay_public_key"), "RSA2");
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();// 创建API对应的request类
		AlipayTradeRefundModel refundModel = new AlipayTradeRefundModel();
		refundModel.setOutTradeNo(payLog.getOrderNo());
		refundModel.setRefundAmount(payLog.getAmount().toString());
		request.setBizModel(refundModel);// 设置业务参数
		try {
			// 通过alipayClient调用API，获得对应的response类
			response = alipayClient.execute(request);
			if (response.isSuccess()) {
				logger.info(">>>>>>>>>>>>>alipay refund success:" + response.getBody());
			} else {
				logger.info(">>>>>>>>>>>>>alipay refund fail:" + response.getBody());
			}
			// 根据response中的结果继续业务逻辑处理
		} catch (AlipayApiException e) {
			logger.error(">>>>>>>>>>>>>alipay refund fail:" + response.getBody());
		}
		return response;
	}
}
