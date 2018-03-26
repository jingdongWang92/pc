package com.jcble.jcparking.common.dto.request;

import java.math.BigDecimal;

public class PayRecordReqDto extends BaseReqDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2731949420714365817L;

	// 订单号
	private String orderNo;
	//交易订单号
	private String tradeNo;
	// 费用类型
	private String type;
	// 支付状态
	private String payStatus;
	// 支付方式
	private String payWay;
	// 支付金额
	private BigDecimal amount;

	private String tradeStatus;

	// 用户id
	private String userId;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	
	

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	@Override
	public String toString() {
		return "PayRecordReqDto [orderNo=" + orderNo + ", type=" + type + ", payStatus=" + payStatus + ", payWay="
				+ payWay + ", amount=" + amount + ", tradeStatus=" + tradeStatus + ", userId=" + userId + "]";
	}

}
