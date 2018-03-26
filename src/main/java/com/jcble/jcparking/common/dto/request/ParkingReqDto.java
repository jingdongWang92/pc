package com.jcble.jcparking.common.dto.request;

public class ParkingReqDto extends BaseReqDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2731949420714365817L;

	private String carNumber;

	private Integer parkinglotId;

	private String inTime;

	private String inOrOut;

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public Integer getParkinglotId() {
		return parkinglotId;
	}

	public void setParkinglotId(Integer parkinglotId) {
		this.parkinglotId = parkinglotId;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getInOrOut() {
		return inOrOut;
	}

	public void setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut;
	}

}
