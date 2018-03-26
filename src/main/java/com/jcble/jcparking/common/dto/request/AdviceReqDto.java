package com.jcble.jcparking.common.dto.request;

public class AdviceReqDto  extends BaseReqDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 用户id */
	private String userId;
	/** 类型 */
	private String type;
	/** 主内容 */
	private String content;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}
