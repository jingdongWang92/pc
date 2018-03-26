package com.jcble.jcparking.common.service.pay;

import javax.servlet.http.HttpServletRequest;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradeRefundResponse;

public interface AliPayService {

	void saveAliPayCallbackLog(HttpServletRequest request) throws Exception;

	/**
	 * 处理支付宝支付通知
	 * 
	 * @param request
	 * @return
	 */
	String handleAliPayNotify(HttpServletRequest request);

	/**
	 * 获取支付宝支付信息
	 * 
	 * @param tradeNo
	 * @param totalAmount
	 * @param orderType
	 * @return
	 * @throws AlipayApiException 
	 */
	String getAlipayOrderInfo(String tradeNo, String totalAmount,String orderType) throws AlipayApiException;

	/**
	 * 支付宝退款
	 * 
	 * @param orderNo
	 *            预约订单号
	 * @return
	 * @throws Exception
	 */
	AlipayTradeRefundResponse refund(String orderNo) throws Exception;

}
