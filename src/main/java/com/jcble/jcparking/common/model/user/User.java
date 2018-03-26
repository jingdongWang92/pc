package com.jcble.jcparking.common.model.user;

import java.math.BigDecimal;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

/**
 * 用户信息实体
 * 
 * @author Jingdong Wang
 * 
 */
@TableMapperAnnotation(tableName = "t_user", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class User extends BaseDto {

	private static final long serialVersionUID = 5161633269899565640L;

	/**
	 * 主键id
	 */
	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.VARCHAR)
	private String id;
	/**
	 * 注册时间
	 */
	@FieldMapperAnnotation(dbFieldName = "registerTime", jdbcType = JdbcType.VARCHAR)
	private String registerTime;
	/**
	 * 最近登录时间
	 */
	@FieldMapperAnnotation(dbFieldName = "recentLoginTime", jdbcType = JdbcType.VARCHAR)
	private String recentLoginTime;

	/**
	 * 推送id
	 */
	@FieldMapperAnnotation(dbFieldName = "jpushId", jdbcType = JdbcType.VARCHAR)
	private String jpushId;
	/**
	 * 余额
	 */
	@FieldMapperAnnotation(dbFieldName = "balance", jdbcType = JdbcType.DECIMAL)
	private BigDecimal balance;
	/**
	 * 手机号码
	 */
	@FieldMapperAnnotation(dbFieldName = "phoneNumber", jdbcType = JdbcType.VARCHAR)
	private String phoneNumber;

	private UserWechat wechat;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getRecentLoginTime() {
		return recentLoginTime;
	}

	public void setRecentLoginTime(String recentLoginTime) {
		this.recentLoginTime = recentLoginTime;
	}

	public String getJpushId() {
		return jpushId;
	}

	public void setJpushId(String jpushId) {
		this.jpushId = jpushId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public UserWechat getWechat() {
		return wechat;
	}

	public void setWechat(UserWechat wechat) {
		this.wechat = wechat;
	}

}
