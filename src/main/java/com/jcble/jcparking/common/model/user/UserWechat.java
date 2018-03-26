package com.jcble.jcparking.common.model.user;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

/**
 * 用户微信账号实体
 * 
 * @author Jingdong Wang
 * @date 2017年3月13日 下午3:43:06
 *
 */
@TableMapperAnnotation(tableName = "t_user_wechat", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class UserWechat extends BaseDto {

	private static final long serialVersionUID = 5161633269899565640L;

	/** 主键id */
	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.INTEGER)
	private Integer id;
	/** 用户id */
	@FieldMapperAnnotation(dbFieldName = "userId", jdbcType = JdbcType.VARCHAR)
	private String userId;
	/** 微信账号 */
	@FieldMapperAnnotation(dbFieldName = "wechatAcc", jdbcType = JdbcType.VARCHAR)
	private String wechatAcc;
	/** 微信昵称 */
	@FieldMapperAnnotation(dbFieldName = "nickname", jdbcType = JdbcType.VARCHAR)
	private String nickname;

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

	public String getWechatAcc() {
		return wechatAcc;
	}

	public void setWechatAcc(String wechatAcc) {
		this.wechatAcc = wechatAcc;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
