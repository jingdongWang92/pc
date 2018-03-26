package com.jcble.jcparking.common.model.devices;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "t_parking_lock_oplog", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class ParkingLockOpLog extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6045851555460773838L;
	// 主键ID
	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.INTEGER)
	private Integer id;
	// 设备串号
	@FieldMapperAnnotation(dbFieldName = "serialNumber", jdbcType = JdbcType.VARCHAR)
	private String serialNumber;
	// 操作者
	@FieldMapperAnnotation(dbFieldName = "operatorId", jdbcType = JdbcType.INTEGER)
	private Integer operatorId;
	// 操作时间
	@FieldMapperAnnotation(dbFieldName = "opTime", jdbcType = JdbcType.VARCHAR)
	private String createTime;
	// 操作方式 0：后台 1：JURA
	@FieldMapperAnnotation(dbFieldName = "opWay", jdbcType = JdbcType.VARCHAR)
	private String opWay;
	// 操作类型 0：升起 1：降下
	@FieldMapperAnnotation(dbFieldName = "opType", jdbcType = JdbcType.VARCHAR)
	private String opType;
	// 备注
	@FieldMapperAnnotation(dbFieldName = "remark", jdbcType = JdbcType.VARCHAR)
	private String remark;

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

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOpWay() {
		return opWay;
	}

	public void setOpWay(String opWay) {
		this.opWay = opWay;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
