package com.jcble.jcparking.common.model.user;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

/**
 * 建议反馈记录表
 * 
 * @author Jingdong Wang
 * 
 */
@TableMapperAnnotation(tableName = "t_advice", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Advice extends BaseDto {

	private static final long serialVersionUID = 5161633269899565640L;

	/** 主键id */
	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.VARCHAR)
	private String id;
	/** 用户id */
	@FieldMapperAnnotation(dbFieldName = "userId", jdbcType = JdbcType.VARCHAR)
	private String userId;
	/** 创建时间 */
	@FieldMapperAnnotation(dbFieldName = "createTime", jdbcType = JdbcType.VARCHAR)
	private String createTime;
	/** 类型 */
	@FieldMapperAnnotation(dbFieldName = "type", jdbcType = JdbcType.VARCHAR)
	private String type;
	/** 主内容 */
	@FieldMapperAnnotation(dbFieldName = "content", jdbcType = JdbcType.VARCHAR)
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
