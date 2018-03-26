package com.jcble.jcparking.common.dto.request;

public class DevicesOpReqDto extends BaseReqDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2219375265801375454L;
	private String serialNumber;
	private Integer operatorId;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

}
