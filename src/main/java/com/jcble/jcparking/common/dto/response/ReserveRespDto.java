package com.jcble.jcparking.common.dto.response;

import weixin.popular.bean.paymch.UnifiedorderResult;

public class ReserveRespDto {
	
	private String orderNo;

	private String parkingNo;

	private UnifiedorderResult weixinPayInfo;

	private String alipayInfo;

	public String getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getParkingNo() {
		return parkingNo;
	}

	public void setParkingNo(String parkingNo) {
		this.parkingNo = parkingNo;
	}

	public UnifiedorderResult getWeixinPayInfo() {
		return weixinPayInfo;
	}

	public void setWeixinPayInfo(UnifiedorderResult weixinPayInfo) {
		this.weixinPayInfo = weixinPayInfo;
	}

	public String getAlipayInfo() {
		return alipayInfo;
	}

	public void setAlipayInfo(String alipayInfo) {
		this.alipayInfo = alipayInfo;
	}

}
