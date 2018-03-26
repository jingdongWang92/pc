package com.jcble.jcparking.common.model.user;

import java.math.BigDecimal;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;
import com.jcble.jcparking.common.model.parking.IndoorMap;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "t_reservation", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Reservation extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8085062924333569131L;

	/** 主键id */
	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.INTEGER)
	private Integer id;
	/** 订单号 */
	@FieldMapperAnnotation(dbFieldName = "orderNo", jdbcType = JdbcType.VARCHAR)
	private String orderNo;
	/** 用户id */
	@FieldMapperAnnotation(dbFieldName = "userId", jdbcType = JdbcType.VARCHAR)
	private String userId;
	/** 车位id */
	@FieldMapperAnnotation(dbFieldName = "parkingId", jdbcType = JdbcType.INTEGER)
	private Integer parkingId;
	/** 预约开始时间 */
	@FieldMapperAnnotation(dbFieldName = "startTime", jdbcType = JdbcType.VARCHAR)
	private String startTime;
	/** 预约到期时间 */
	@FieldMapperAnnotation(dbFieldName = "expireTime", jdbcType = JdbcType.VARCHAR)
	private String expireTime;
	/** 车牌号 */
	@FieldMapperAnnotation(dbFieldName = "carNumber", jdbcType = JdbcType.VARCHAR)
	private String carNumber;
	/** 支付状态 */
	@FieldMapperAnnotation(dbFieldName = "payStatus", jdbcType = JdbcType.VARCHAR)
	private String payStatus;
	/** 预约时长 */
	@FieldMapperAnnotation(dbFieldName = "duration", jdbcType = JdbcType.INTEGER)
	private Integer duration;
	/** 支付方式 */
	@FieldMapperAnnotation(dbFieldName = "payWay", jdbcType = JdbcType.VARCHAR)
	private String payWay;
	/** 支付金额 */
	@FieldMapperAnnotation(dbFieldName = "payedFee", jdbcType = JdbcType.DECIMAL)
	private BigDecimal payedFee;
	/** 支付时间 */
	@FieldMapperAnnotation(dbFieldName = "payTime", jdbcType = JdbcType.VARCHAR)
	private String payTime;
	/** 预约订单状态 */
	@FieldMapperAnnotation(dbFieldName = "status", jdbcType = JdbcType.VARCHAR)
	private String status;

	private Integer parkinglotId;
	private String parkinglotName;
	private String parkingNo;
	private Double coordinateX;
	private Double coordinateY;
	private Integer distance;
	private IndoorMap indoorMap;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getParkingId() {
		return parkingId;
	}

	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public BigDecimal getPayedFee() {
		return payedFee;
	}

	public void setPayedFee(BigDecimal payedFee) {
		this.payedFee = payedFee;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public Integer getParkinglotId() {
		return parkinglotId;
	}

	public void setParkinglotId(Integer parkinglotId) {
		this.parkinglotId = parkinglotId;
	}

	public String getParkinglotName() {
		return parkinglotName;
	}

	public void setParkinglotName(String parkinglotName) {
		this.parkinglotName = parkinglotName;
	}

	public String getParkingNo() {
		return parkingNo;
	}

	public void setParkingNo(String parkingNo) {
		this.parkingNo = parkingNo;
	}

	public Double getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(Double coordinateX) {
		this.coordinateX = coordinateX;
	}

	public Double getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(Double coordinateY) {
		this.coordinateY = coordinateY;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public IndoorMap getIndoorMap() {
		return indoorMap;
	}

	public void setIndoorMap(IndoorMap indoorMap) {
		this.indoorMap = indoorMap;
	}

}
