package com.jcble.jcparking.common.dto.response;

import weixin.popular.bean.paymch.UnifiedorderResult;

public class RechargeRespDto {
	
	private UnifiedorderResult weixinPayInfo;

	private String alipayInfo;

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
