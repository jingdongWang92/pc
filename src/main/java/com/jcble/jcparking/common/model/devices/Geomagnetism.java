package com.jcble.jcparking.common.model.devices;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "t_geomagnetism", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Geomagnetism extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6045851555460773838L;
	// 主键ID
	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.INTEGER)
	private Integer id;
	// 创建者
	@FieldMapperAnnotation(dbFieldName = "creator", jdbcType = JdbcType.INTEGER)
	private Integer creator;
	// 创建时间
	@FieldMapperAnnotation(dbFieldName = "createTime", jdbcType = JdbcType.VARCHAR)
	private String createTime;
	// 修改者
	@FieldMapperAnnotation(dbFieldName = "modifier", jdbcType = JdbcType.INTEGER)
	private Integer modifier;
	// 修改时间
	@FieldMapperAnnotation(dbFieldName = "modifyTime", jdbcType = JdbcType.VARCHAR)
	private String modifyTime;
	// 地磁串号
	@FieldMapperAnnotation(dbFieldName = "serialNumber", jdbcType = JdbcType.VARCHAR)
	private String serialNumber;
	// 地磁电量
	@FieldMapperAnnotation(dbFieldName = "power", jdbcType = JdbcType.VARCHAR)
	private String power;
	// 地磁状态
	@FieldMapperAnnotation(dbFieldName = "status", jdbcType = JdbcType.VARCHAR)
	private String status;
	// 强磁干扰状态
	@FieldMapperAnnotation(dbFieldName = "smiStatus", jdbcType = JdbcType.VARCHAR)
	private String smiStatus;
	// 车辆是否停稳
	@FieldMapperAnnotation(dbFieldName = "carStatus", jdbcType = JdbcType.VARCHAR)
	private String carStatus;
	// 数据上报时间
	@FieldMapperAnnotation(dbFieldName = "receiveDataTime", jdbcType = JdbcType.VARCHAR)
	private String receiveDataTime;
	// 所属车位ID
	@FieldMapperAnnotation(dbFieldName = "parkingId", jdbcType = JdbcType.INTEGER)
	private Integer parkingId;
	// 地磁位置
	@FieldMapperAnnotation(dbFieldName = "location", jdbcType = JdbcType.VARCHAR)
	private String location;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSmiStatus() {
		return smiStatus;
	}

	public void setSmiStatus(String smiStatus) {
		this.smiStatus = smiStatus;
	}

	public String getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}

	public String getReceiveDataTime() {
		return receiveDataTime;
	}

	public void setReceiveDataTime(String receiveDataTime) {
		this.receiveDataTime = receiveDataTime;
	}

	public Integer getParkingId() {
		return parkingId;
	}

	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
