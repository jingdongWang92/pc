package com.jcble.jcparking.common.model.admin;

import java.math.BigDecimal;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "t_parking_lot", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class ParkingLot extends BaseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7006435513040598136L;
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
	// 停车场名称
	@FieldMapperAnnotation(dbFieldName = "parkinglotName", jdbcType = JdbcType.VARCHAR)
	private String parkinglotName;
	// 离场超时时长
	@FieldMapperAnnotation(dbFieldName = "leaveOutTime", jdbcType = JdbcType.INTEGER)
	private Integer leaveOutTime;
	// 是否可预约
	@FieldMapperAnnotation(dbFieldName = "isRevatation", jdbcType = JdbcType.BOOLEAN)
	private Boolean isRevatation;
	// 免费时长
	@FieldMapperAnnotation(dbFieldName = "freeTime", jdbcType = JdbcType.INTEGER)
	private Integer freeTime;
	// 免费预约时长(分钟)
	@FieldMapperAnnotation(dbFieldName = "revatationTime", jdbcType = JdbcType.INTEGER)
	private Integer revatationTime;
	// 是否有室内地图
	@FieldMapperAnnotation(dbFieldName = "haveMap", jdbcType = JdbcType.BOOLEAN)
	private Boolean haveMap;
	// 是否有车位锁
	@FieldMapperAnnotation(dbFieldName = "haveLock", jdbcType = JdbcType.BOOLEAN)
	private Boolean haveLock;
	// 位置
	@FieldMapperAnnotation(dbFieldName = "location", jdbcType = JdbcType.VARCHAR)
	private String location;
	// X坐标
	@FieldMapperAnnotation(dbFieldName = "coordinateX", jdbcType = JdbcType.DOUBLE)
	private Double coordinateX;
	// Y坐标
	@FieldMapperAnnotation(dbFieldName = "coordinateY", jdbcType = JdbcType.DOUBLE)
	private Double coordinateY;
	// 车位总量
	private Integer totalCount;
	// 使用中车位数量
	private Integer inUseCount;
	// 空闲数量
	private Integer freeCount;
	// 可预约车位数量
	private Integer revatationCount;
	// 距离
	private Integer distance;
	// 停车单价(每小时/元)
	private BigDecimal parkingPrice;

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

	public String getParkinglotName() {
		return parkinglotName;
	}

	public void setParkinglotName(String parkinglotName) {
		this.parkinglotName = parkinglotName;
	}

	public Integer getLeaveOutTime() {
		return leaveOutTime;
	}

	public void setLeaveOutTime(Integer leaveOutTime) {
		this.leaveOutTime = leaveOutTime;
	}

	public Boolean getIsRevatation() {
		return isRevatation;
	}

	public void setIsRevatation(Boolean isRevatation) {
		this.isRevatation = isRevatation;
	}

	public Integer getFreeTime() {
		return freeTime;
	}

	public void setFreeTime(Integer freeTime) {
		this.freeTime = freeTime;
	}

	public Integer getRevatationTime() {
		return revatationTime;
	}

	public void setRevatationTime(Integer revatationTime) {
		this.revatationTime = revatationTime;
	}

	public Boolean getHaveMap() {
		return haveMap;
	}

	public void setHaveMap(Boolean haveMap) {
		this.haveMap = haveMap;
	}

	public Boolean getHaveLock() {
		return haveLock;
	}

	public void setHaveLock(Boolean haveLock) {
		this.haveLock = haveLock;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getInUseCount() {
		return inUseCount;
	}

	public void setInUseCount(Integer inUseCount) {
		this.inUseCount = inUseCount;
	}

	public Integer getFreeCount() {
		return freeCount;
	}

	public void setFreeCount(Integer freeCount) {
		this.freeCount = freeCount;
	}

	public Integer getRevatationCount() {
		return revatationCount;
	}

	public void setRevatationCount(Integer revatationCount) {
		this.revatationCount = revatationCount;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public BigDecimal getParkingPrice() {
		return parkingPrice;
	}

	public void setParkingPrice(BigDecimal parkingPrice) {
		this.parkingPrice = parkingPrice;
	}

}
