package com.jcble.jcparking.common.model.devices;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "t_lock_data_log", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class LockDataLog extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6045851555460773838L;
	// 主键ID
	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.INTEGER)
	private Integer id;
	// 车位锁串号
	@FieldMapperAnnotation(dbFieldName = "serialNumber", jdbcType = JdbcType.VARCHAR)
	private String serialNumber;
	// 电量
	@FieldMapperAnnotation(dbFieldName = "power", jdbcType = JdbcType.INTEGER)
	private String power;
	// 状态
	@FieldMapperAnnotation(dbFieldName = "status", jdbcType = JdbcType.VARCHAR)
	private String status;
	// 数据上报时间
	@FieldMapperAnnotation(dbFieldName = "receiveDataTime", jdbcType = JdbcType.VARCHAR)
	private String receiveDataTime;
	// 数据
	@FieldMapperAnnotation(dbFieldName = "data", jdbcType = JdbcType.VARCHAR)
	private String data;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getReceiveDataTime() {
		return receiveDataTime;
	}

	public void setReceiveDataTime(String receiveDataTime) {
		this.receiveDataTime = receiveDataTime;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
