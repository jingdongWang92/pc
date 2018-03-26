package com.jcble.jcparking.common.model.parking;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;

/**
 * 室内地图
 * 
 * @author Jingdong Wang
 * @date 2017年4月11日 上午9:32:03
 *
 */
public class IndoorMap extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 主键id */
	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.INTEGER)
	private Integer id;
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
	/** Android下载链接 */
	@FieldMapperAnnotation(dbFieldName = "androidDownloadUrl", jdbcType = JdbcType.VARCHAR)
	private String androidDownloadUrl;
	/** ios下载链接 */
	@FieldMapperAnnotation(dbFieldName = "iosDownloadUrl", jdbcType = JdbcType.VARCHAR)
	private String iosDownloadUrl;
	/** 地图名称 */
	@FieldMapperAnnotation(dbFieldName = "mapName", jdbcType = JdbcType.VARCHAR)
	private String mapName;
	/** 版本号 */
	@FieldMapperAnnotation(dbFieldName = "version", jdbcType = JdbcType.VARCHAR)
	private String version;

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

	public String getAndroidDownloadUrl() {
		return androidDownloadUrl;
	}

	public void setAndroidDownloadUrl(String androidDownloadUrl) {
		this.androidDownloadUrl = androidDownloadUrl;
	}

	public String getIosDownloadUrl() {
		return iosDownloadUrl;
	}

	public void setIosDownloadUrl(String iosDownloadUrl) {
		this.iosDownloadUrl = iosDownloadUrl;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
