package com.jcble.jcparking.common.dto.response;

public class DeviceDto {

	// 车位id
	private Integer parkingId;

	// 设备串号
	private String serialNumber;

	// 失败消息
	private String errMsg;

	public Integer getParkingId() {
		return parkingId;
	}

	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
