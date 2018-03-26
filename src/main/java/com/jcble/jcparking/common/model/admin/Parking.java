package com.jcble.jcparking.common.model.admin;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;
import com.jcble.jcparking.common.model.devices.Geomagnetism;
import com.jcble.jcparking.common.model.devices.ParkingLock;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "t_parking", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Parking extends BaseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7006435513040598136L;
	// 主键ID
	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.INTEGER)
	private Integer id;
	// 车位编号
	@FieldMapperAnnotation(dbFieldName = "parkingNo", jdbcType = JdbcType.VARCHAR)
	private String parkingNo;
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
	// 创建方式
	@FieldMapperAnnotation(dbFieldName = "createWay", jdbcType = JdbcType.VARCHAR)
	private String createWay;
	// 所属停车场
	@FieldMapperAnnotation(dbFieldName = "parkinglotId", jdbcType = JdbcType.INTEGER)
	private Integer parkinglotId;
	// 区域
	@FieldMapperAnnotation(dbFieldName = "areaId", jdbcType = JdbcType.VARCHAR)
	private Integer areaId;
	// X坐标
	@FieldMapperAnnotation(dbFieldName = "coordinateX", jdbcType = JdbcType.DOUBLE)
	private Double coordinateX;
	// Y坐标
	@FieldMapperAnnotation(dbFieldName = "coordinateY", jdbcType = JdbcType.DOUBLE)
	private Double coordinateY;
	// 状态
	@FieldMapperAnnotation(dbFieldName = "status", jdbcType = JdbcType.VARCHAR)
	private String status;
	// 是否可预约
	@FieldMapperAnnotation(dbFieldName = "isRevatation", jdbcType = JdbcType.VARCHAR)
	private String isRevatation;
	// 车位锁串号
	@FieldMapperAnnotation(dbFieldName = "lockSerialNumber", jdbcType = JdbcType.VARCHAR)
	private String lockSerialNumber;
	// 地磁串号
	@FieldMapperAnnotation(dbFieldName = "geoSerialNumber", jdbcType = JdbcType.VARCHAR)
	private String geoSerialNumber;
	// 地图id
	@FieldMapperAnnotation(dbFieldName = "indoorMapId", jdbcType = JdbcType.INTEGER)
	private Integer indoorMapId;
	// 管理员id
	private Integer operatorId;
	// 车位锁状态
	private String lockStatus;
	private ParkingLock parkingLock;
	private Geomagnetism geomagnetism;
	private String devBindStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParkingNo() {
		return parkingNo;
	}

	public void setParkingNo(String parkingNo) {
		this.parkingNo = parkingNo;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getCreateWay() {
		return createWay;
	}

	public void setCreateWay(String createWay) {
		this.createWay = createWay;
	}

	public Integer getParkinglotId() {
		return parkinglotId;
	}

	public void setParkinglotId(Integer parkinglotId) {
		this.parkinglotId = parkinglotId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	public ParkingLock getParkingLock() {
		return parkingLock;
	}

	public void setParkingLock(ParkingLock parkingLock) {
		this.parkingLock = parkingLock;
	}

	public Geomagnetism getGeomagnetism() {
		return geomagnetism;
	}

	public void setGeomagnetism(Geomagnetism geomagnetism) {
		this.geomagnetism = geomagnetism;
	}

	public String getLockSerialNumber() {
		return lockSerialNumber;
	}

	public void setLockSerialNumber(String lockSerialNumber) {
		this.lockSerialNumber = lockSerialNumber;
	}

	public String getGeoSerialNumber() {
		return geoSerialNumber;
	}

	public void setGeoSerialNumber(String geoSerialNumber) {
		this.geoSerialNumber = geoSerialNumber;
	}

	public String getDevBindStatus() {
		return devBindStatus;
	}

	public void setDevBindStatus(String devBindStatus) {
		this.devBindStatus = devBindStatus;
	}

	public String getIsRevatation() {
		return isRevatation;
	}

	public void setIsRevatation(String isRevatation) {
		this.isRevatation = isRevatation;
	}

	public Integer getIndoorMapId() {
		return indoorMapId;
	}

	public void setIndoorMapId(Integer indoorMapId) {
		this.indoorMapId = indoorMapId;
	}

}
