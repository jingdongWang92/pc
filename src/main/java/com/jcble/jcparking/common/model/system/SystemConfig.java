package com.jcble.jcparking.common.model.system;

import org.apache.ibatis.type.JdbcType;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

/**
 * 系统参数配置实体
 * 
 * @author Jingdong Wang
 * @date 2017年3月1日 下午3:39:16
 *
 */
@TableMapperAnnotation(tableName = "t_system_config", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class SystemConfig {

	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.INTEGER)
	private Integer id;

	/** 参数名. */
	@FieldMapperAnnotation(dbFieldName = "paramName", jdbcType = JdbcType.VARCHAR)
	private String paramName;

	/** 参数代码 */
	@FieldMapperAnnotation(dbFieldName = "paramKey", jdbcType = JdbcType.VARCHAR)
	private String paramKey;

	/** 参数类型. */
	@FieldMapperAnnotation(dbFieldName = "paramType", jdbcType = JdbcType.INTEGER)
	private String paramType;

	/** 参数值. */
	@FieldMapperAnnotation(dbFieldName = "paramValue", jdbcType = JdbcType.VARCHAR)
	private String paramValue;

	/** 参数描述. */
	@FieldMapperAnnotation(dbFieldName = "paramDesc", jdbcType = JdbcType.VARCHAR)
	private String paramDesc;

	/** 参数标志. */
	@FieldMapperAnnotation(dbFieldName = "paramFlag", jdbcType = JdbcType.INTEGER)
	private Integer paramFlag;

	/** 参数默认值. */
	@FieldMapperAnnotation(dbFieldName = "defaultValue", jdbcType = JdbcType.VARCHAR)
	private String defaultValue;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamDesc() {
		return paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	public Integer getParamFlag() {
		return paramFlag;
	}

	public void setParamFlag(Integer paramFlag) {
		this.paramFlag = paramFlag;
	}

}
