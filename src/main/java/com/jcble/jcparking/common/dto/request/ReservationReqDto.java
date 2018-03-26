package com.jcble.jcparking.common.dto.request;

import java.math.BigDecimal;

public class ReservationReqDto extends BaseReqDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 车牌号
	private String carNumber;

	// 用户
	private String userId;

	// 预约时长(分钟)
	private Integer duration;

	// 支付金额
	private BigDecimal payFee;

	// 停车场
	private Integer parkinglotId;

	// 支付方式
	private String payWay;

	// ip地址
	private String ipAddr;

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public BigDecimal getPayFee() {
		return payFee;
	}

	public void setPayFee(BigDecimal payFee) {
		this.payFee = payFee;
	}

	public Integer getParkinglotId() {
		return parkinglotId;
	}

	public void setParkinglotId(Integer parkinglotId) {
		this.parkinglotId = parkinglotId;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

}
