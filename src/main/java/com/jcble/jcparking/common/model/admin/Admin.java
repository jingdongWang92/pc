package com.jcble.jcparking.common.model.admin;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "t_administrator", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Admin extends BaseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8085062924333569131L;

	/** 主键id */
	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.INTEGER)
	private Integer id;

	/** 创建者 */
	@FieldMapperAnnotation(dbFieldName = "creator", jdbcType = JdbcType.INTEGER)
	private Integer creator;

	/** 创建时间 */
	@FieldMapperAnnotation(dbFieldName = "createTime", jdbcType = JdbcType.VARCHAR)
	private String createTime;

	/** 修改者 */
	@FieldMapperAnnotation(dbFieldName = "modifier", jdbcType = JdbcType.INTEGER)
	private Integer modifier;

	/** 修改时间 */
	@FieldMapperAnnotation(dbFieldName = "modifyTime", jdbcType = JdbcType.VARCHAR)
	private String modifyTime;

	/** 管理员姓名 */
	@FieldMapperAnnotation(dbFieldName = "name", jdbcType = JdbcType.VARCHAR)
	private String name;

	/** 管理员账号 */
	@FieldMapperAnnotation(dbFieldName = "account", jdbcType = JdbcType.VARCHAR)
	private String account;

	/** 登录密码 */
	@FieldMapperAnnotation(dbFieldName = "password", jdbcType = JdbcType.VARCHAR)
	private String password;

	/** 电话号码 */
	@FieldMapperAnnotation(dbFieldName = "phoneNumber", jdbcType = JdbcType.VARCHAR)
	private String phoneNumber;

	/** 账号状态 */
	@FieldMapperAnnotation(dbFieldName = "status", jdbcType = JdbcType.VARCHAR)
	private String status;

	/** 备注 */
	@FieldMapperAnnotation(dbFieldName = "remark", jdbcType = JdbcType.VARCHAR)
	private String remark;

	/** 最近登录时间 */
	@FieldMapperAnnotation(dbFieldName = "recentLoginTime", jdbcType = JdbcType.VARCHAR)
	private String recentLoginTime;

	/** 管理员角色类型 */
	private String type;

	/** 极光推送id */
	@FieldMapperAnnotation(dbFieldName = "jpushId", jdbcType = JdbcType.VARCHAR)
	private String jpushId;

	private String vcode;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRecentLoginTime() {
		return recentLoginTime;
	}

	public void setRecentLoginTime(String recentLoginTime) {
		this.recentLoginTime = recentLoginTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getJpushId() {
		return jpushId;
	}

	public void setJpushId(String jpushId) {
		this.jpushId = jpushId;
	}

}
