package com.jcble.jcparking.common.model.jpush;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

/**
 * 极光推送消息
 * 
 * @author Jingdong Wang
 * @date 2017年3月21日 上午10:33:19
 *
 */
@TableMapperAnnotation(tableName = "t_push_message", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class JPushMessage extends BaseDto {

	private static final long serialVersionUID = 5161633269899565640L;

	/** 主键id */
	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.INTEGER)
	private Integer id;
	/** 操作者 */
	@FieldMapperAnnotation(dbFieldName = "operator", jdbcType = JdbcType.INTEGER)
	private Integer operator;
	/** 创建时间 */
	@FieldMapperAnnotation(dbFieldName = "createTime", jdbcType = JdbcType.VARCHAR)
	private String createTime;
	/** 用户id */
	@FieldMapperAnnotation(dbFieldName = "title", jdbcType = JdbcType.VARCHAR)
	private String title;
	/** 推送消息id */
	@FieldMapperAnnotation(dbFieldName = "content", jdbcType = JdbcType.VARCHAR)
	private String content;
	/** 阅读人数 */
	@FieldMapperAnnotation(dbFieldName = "readCount", jdbcType = JdbcType.INTEGER)
	private int readCount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

}
