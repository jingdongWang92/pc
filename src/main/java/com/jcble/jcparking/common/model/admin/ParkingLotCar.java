package com.jcble.jcparking.common.model.admin;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "t_parkinglot_car", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class ParkingLotCar extends BaseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7006435513040598136L;
	// 主键ID
	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.INTEGER)
	private Integer id;
	// 车牌号
	@FieldMapperAnnotation(dbFieldName = "carNumber", jdbcType = JdbcType.VARCHAR)
	private String carNumber;
	// 车辆入场时间
	@FieldMapperAnnotation(dbFieldName = "inTime", jdbcType = JdbcType.VARCHAR)
	private String inTime;
	// 车辆离场时间
	@FieldMapperAnnotation(dbFieldName = "outTime", jdbcType = JdbcType.VARCHAR)
	private String outTime;
	// 车牌图片访问路径
	@FieldMapperAnnotation(dbFieldName = "carNumberPhotoUrl", jdbcType = JdbcType.VARCHAR)
	private String carNumberPhotoUrl;
	// 车辆类型
	@FieldMapperAnnotation(dbFieldName = "carType", jdbcType = JdbcType.VARCHAR)
	private String carType;
	// 停车类型
	@FieldMapperAnnotation(dbFieldName = "pType", jdbcType = JdbcType.VARCHAR)
	private String pType;
	// 停车场
	@FieldMapperAnnotation(dbFieldName = "parkinglotId", jdbcType = JdbcType.INTEGER)
	private Integer parkinglotId;
	// 占用车位
	@FieldMapperAnnotation(dbFieldName = "parkingId", jdbcType = JdbcType.INTEGER)
	private Integer parkingId;
	// 停车订单号
	@FieldMapperAnnotation(dbFieldName = "orderNo", jdbcType = JdbcType.VARCHAR)
	private String orderNo;

	private String parkingNo;

	private Integer operatorId;

	private String parkinglotName;

	private ParkingOrder order;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getCarNumberPhotoUrl() {
		return carNumberPhotoUrl;
	}

	public void setCarNumberPhotoUrl(String carNumberPhotoUrl) {
		this.carNumberPhotoUrl = carNumberPhotoUrl;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getpType() {
		return pType;
	}

	public void setpType(String pType) {
		this.pType = pType;
	}

	public Integer getParkingId() {
		return parkingId;
	}

	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}

	public String getParkinglotName() {
		return parkinglotName;
	}

	public void setParkinglotName(String parkinglotName) {
		this.parkinglotName = parkinglotName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public ParkingOrder getOrder() {
		return order;
	}

	public void setOrder(ParkingOrder order) {
		this.order = order;
	}

	public String getParkingNo() {
		return parkingNo;
	}

	public void setParkingNo(String parkingNo) {
		this.parkingNo = parkingNo;
	}

	public Integer getParkinglotId() {
		return parkinglotId;
	}

	public void setParkinglotId(Integer parkinglotId) {
		this.parkinglotId = parkinglotId;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

}
