package com.jcble.jcparking.common.model.admin;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "t_parking_lot_area", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class ParkingArea extends BaseDto {
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
	@FieldMapperAnnotation(dbFieldName = "createdate", jdbcType = JdbcType.VARCHAR)
	private String createdate;
	// 修改者
	@FieldMapperAnnotation(dbFieldName = "modifier", jdbcType = JdbcType.INTEGER)
	private Integer modifier;
	// 修改时间
	@FieldMapperAnnotation(dbFieldName = "modifyDate", jdbcType = JdbcType.VARCHAR)
	private String modifyDate;
	// 区域名称
	@FieldMapperAnnotation(dbFieldName = "areaName", jdbcType = JdbcType.VARCHAR)
	private String areaName;
	// 停车场
	@FieldMapperAnnotation(dbFieldName = "parkingLotId", jdbcType = JdbcType.INTEGER)
	private Integer parkingLotId;

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

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getParkingLotId() {
		return parkingLotId;
	}

	public void setParkingLotId(Integer parkingLotId) {
		this.parkingLotId = parkingLotId;
	}

}
