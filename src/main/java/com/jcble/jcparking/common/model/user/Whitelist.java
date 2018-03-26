package com.jcble.jcparking.common.model.user;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

/**
 * 固定车实体类
 * 
 * @author Jingdong Wang
 * @date 2017年3月17日 上午11:57:12
 *
 */
@TableMapperAnnotation(tableName = "t_whitelist", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Whitelist extends BaseDto {

	private static final long serialVersionUID = 5161633269899565640L;

	/** 主键id */
	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.VARCHAR)
	private String id;
	/** 创建者 */
	@FieldMapperAnnotation(dbFieldName = "creator", jdbcType = JdbcType.VARCHAR)
	private Integer creator;
	/** 创建时间 */
	@FieldMapperAnnotation(dbFieldName = "createTime", jdbcType = JdbcType.VARCHAR)
	private String createTime;
	/** 修改者 */
	@FieldMapperAnnotation(dbFieldName = "modifier", jdbcType = JdbcType.VARCHAR)
	private Integer modifier;
	/** 修改时间 */
	@FieldMapperAnnotation(dbFieldName = "modifyTime", jdbcType = JdbcType.VARCHAR)
	private String modifyTime;
	/** 车牌号 */
	@FieldMapperAnnotation(dbFieldName = "carNumber", jdbcType = JdbcType.VARCHAR)
	private String carNumber;
	/** 所属车位 */
	@FieldMapperAnnotation(dbFieldName = "parkingId", jdbcType = JdbcType.VARCHAR)
	private Integer parkingId;
	/** 所属停车场 */
	@FieldMapperAnnotation(dbFieldName = "parkinglotId", jdbcType = JdbcType.VARCHAR)
	private Integer parkinglotId;
	/** 有效期 -开始 */
	@FieldMapperAnnotation(dbFieldName = "startDate", jdbcType = JdbcType.VARCHAR)
	private String startDate;
	/** 有效期 -结束 */
	@FieldMapperAnnotation(dbFieldName = "endDate", jdbcType = JdbcType.VARCHAR)
	private String endDate;
	/** 包期类型 */
	@FieldMapperAnnotation(dbFieldName = "endDate", jdbcType = JdbcType.VARCHAR)
	private String type;
	/** 包期费用 */
	@FieldMapperAnnotation(dbFieldName = "fee", jdbcType = JdbcType.VARCHAR)
	private String fee;
	/** 包期时长 */
	@FieldMapperAnnotation(dbFieldName = "duration", jdbcType = JdbcType.VARCHAR)
	private String duration;
	/** 状态 */
	@FieldMapperAnnotation(dbFieldName = "endDate", jdbcType = JdbcType.VARCHAR)
	private String status;
	/** 车主姓名 */
	@FieldMapperAnnotation(dbFieldName = "ownerName", jdbcType = JdbcType.VARCHAR)
	private String ownerName;
	/** 车主电话 */
	@FieldMapperAnnotation(dbFieldName = "phoneNumber", jdbcType = JdbcType.VARCHAR)
	private String phoneNumber;

	private String parkinglotName;
	private Double coordinateX;
	private Double coordinateY;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Integer getParkingId() {
		return parkingId;
	}

	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}

	public Integer getParkinglotId() {
		return parkinglotId;
	}

	public void setParkinglotId(Integer parkinglotId) {
		this.parkinglotId = parkinglotId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getParkinglotName() {
		return parkinglotName;
	}

	public void setParkinglotName(String parkinglotName) {
		this.parkinglotName = parkinglotName;
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

}
