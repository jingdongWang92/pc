package com.jcble.jcparking.common.dto.request;

public class AdminReqDto extends BaseReqDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2731949420714365817L;

	private String account;

	private String password;

	private String vcode;

	private String jpushId;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getJpushId() {
		return jpushId;
	}

	public void setJpushId(String jpushId) {
		this.jpushId = jpushId;
	}

}
