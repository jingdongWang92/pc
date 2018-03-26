package com.jcble.jcparking.common.model.admin;

import java.math.BigDecimal;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "t_charge_rules", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class ChargeRule extends BaseDto {
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
	// 规则版本号
	@FieldMapperAnnotation(dbFieldName = "verno", jdbcType = JdbcType.INTEGER)
	private Integer verno;
	// 规则名称
	@FieldMapperAnnotation(dbFieldName = "ruleName", jdbcType = JdbcType.VARCHAR)
	private String ruleName;
	// 是否启用
	@FieldMapperAnnotation(dbFieldName = "inUse", jdbcType = JdbcType.VARCHAR)
	private String inUse;
	// 车辆类型
	@FieldMapperAnnotation(dbFieldName = "carType", jdbcType = JdbcType.VARCHAR)
	private String carType;
	// 规则类型
	@FieldMapperAnnotation(dbFieldName = "ruleType", jdbcType = JdbcType.VARCHAR)
	private String ruleType;
	// 单价(每小时/元)
	@FieldMapperAnnotation(dbFieldName = "price", jdbcType = JdbcType.DECIMAL)
	private BigDecimal price;
	// 24小时封顶金额
	@FieldMapperAnnotation(dbFieldName = "upLimitFee24", jdbcType = JdbcType.DECIMAL)
	private BigDecimal upLimitFee24;
	// 12小时封顶金额
	@FieldMapperAnnotation(dbFieldName = "upLimitFee12", jdbcType = JdbcType.DECIMAL)
	private BigDecimal upLimitFee12;
	// 规则描述
	@FieldMapperAnnotation(dbFieldName = "description", jdbcType = JdbcType.VARCHAR)
	private String description;

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

	public Integer getVerno() {
		return verno;
	}

	public void setVerno(Integer verno) {
		this.verno = verno;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getInUse() {
		return inUse;
	}

	public void setInUse(String inUse) {
		this.inUse = inUse;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getUpLimitFee24() {
		return upLimitFee24;
	}

	public void setUpLimitFee24(BigDecimal upLimitFee24) {
		this.upLimitFee24 = upLimitFee24;
	}

	public BigDecimal getUpLimitFee12() {
		return upLimitFee12;
	}

	public void setUpLimitFee12(BigDecimal upLimitFee12) {
		this.upLimitFee12 = upLimitFee12;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
