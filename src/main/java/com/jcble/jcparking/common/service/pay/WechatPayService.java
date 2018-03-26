package com.jcble.jcparking.common.service.pay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weixin.popular.bean.paymch.SecapiPayRefundResult;
import weixin.popular.bean.paymch.UnifiedorderResult;

/**
 * 微信支付
 * 
 * @author Jingdong Wang
 * @date 2017年3月27日 下午3:54:22
 *
 */
public interface WechatPayService {

	/**
	 * 处理微信支付通知
	 * 
	 * @param request
	 * @param response
	 * @param source
	 */
	void handleWxPayNotify(HttpServletRequest request, HttpServletResponse response, String source);

	/**
	 * 生成微信支付订单
	 * 
	 * @param ip 请求ip地址
	 * @param tradeNo 交易订单号 
	 * @param orderType 订单类型
	 * @return
	 * @throws Exception
	 */
	UnifiedorderResult getWxPayOrderInfo(String ip, String tradeNo,String payFee,String orderType) throws Exception;

	/**
	 * 微信退款
	 * 
	 * @param orderNo
	 * @throws Exception 
	 */
	SecapiPayRefundResult refund(String orderNo) throws Exception;

}
