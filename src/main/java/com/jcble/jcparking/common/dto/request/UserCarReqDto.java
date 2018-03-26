package com.jcble.jcparking.common.dto.request;

public class UserCarReqDto extends BaseReqDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2731949420714365817L;

	private Integer carId;

	private String carNumber;

	private String userId;

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
