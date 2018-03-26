package com.jcble.jcparking.common.dto.request;

public class OrderReqDto extends BaseReqDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String carNumber;

	private Integer parkingId;

	private Integer parkinglotCarId;

	private String carType;

	private Integer operatorId;

	private Integer orderId;

	private String reason;
	
	private boolean isCar;

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public Integer getParkingId() {
		return parkingId;
	}

	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getParkinglotCarId() {
		return parkinglotCarId;
	}

	public void setParkinglotCarId(Integer parkinglotCarId) {
		this.parkinglotCarId = parkinglotCarId;
	}

	public boolean getIsCar() {
		return isCar;
	}

	public void setIsCar(boolean isCar) {
		this.isCar = isCar;
	}
	

}
