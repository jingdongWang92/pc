package com.jcble.jcparking.common.dto.request;

import com.jcble.jcparking.common.dto.request.BaseReqDto;

public class VerifyCodeDto extends BaseReqDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mobile;

	private String verifyCode;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
