package com.jcble.jcparking.common.dto.request;

public class UserWechatReqDto extends BaseReqDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2731949420714365817L;

	private String wechatAcc;

	private String nickname;
	
	private String userId;

	public String getWechatAcc() {
		return wechatAcc;
	}

	public void setWechatAcc(String wechatAcc) {
		this.wechatAcc = wechatAcc;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
