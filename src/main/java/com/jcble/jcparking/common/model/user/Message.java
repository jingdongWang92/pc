package com.jcble.jcparking.common.model.user;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

/**
 * 推送消息
 * 
 * @author Jingdong Wang
 * @date 2017年3月21日 上午10:33:19
 *
 */
@TableMapperAnnotation(tableName = "t_user_message", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Message extends BaseDto {

	private static final long serialVersionUID = 5161633269899565640L;

	/** 主键id */
	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.INTEGER)
	private Integer id;
	/** 用户id */
	@FieldMapperAnnotation(dbFieldName = "userId", jdbcType = JdbcType.VARCHAR)
	private String userId;
	/** 推送消息id */
	@FieldMapperAnnotation(dbFieldName = "pushMessageId", jdbcType = JdbcType.INTEGER)
	private Integer pushMessageId;
	/** 创建时间 */
	private String createTime;
	/** 状态 */
	@FieldMapperAnnotation(dbFieldName = "readStatus", jdbcType = JdbcType.VARCHAR)
	private String readStatus;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getPushMessageId() {
		return pushMessageId;
	}

	public void setPushMessageId(Integer pushMessageId) {
		this.pushMessageId = pushMessageId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
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

}
