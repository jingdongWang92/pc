package com.jcble.jcparking.common.dto.request;

import java.math.BigDecimal;

public class PayReqDto extends BaseReqDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal payFee;

	private String orderNo;

	private String orderType;

	public BigDecimal getPayFee() {
		return payFee;
	}

	public void setPayFee(BigDecimal payFee) {
		this.payFee = payFee;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

}
