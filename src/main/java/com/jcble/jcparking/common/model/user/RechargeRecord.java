package com.jcble.jcparking.common.model.user;

import java.math.BigDecimal;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

/**
 * 充值记录
 * 
 * @author Jingdong Wang
 * 
 */
@TableMapperAnnotation(tableName = "t_rechatge_record", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class RechargeRecord extends BaseDto {

	private static final long serialVersionUID = 5161633269899565640L;

	/** 主键id */
	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.INTEGER)
	private Integer id;
	/** 用户id */
	@FieldMapperAnnotation(dbFieldName = "userId", jdbcType = JdbcType.VARCHAR)
	private String userId;
	/** 支付方式 */
	@FieldMapperAnnotation(dbFieldName = "payWay", jdbcType = JdbcType.VARCHAR)
	private String payWay;
	/** 支付时间 */
	@FieldMapperAnnotation(dbFieldName = "payTime", jdbcType = JdbcType.VARCHAR)
	private String payTime;
	/** 充值金额 */
	@FieldMapperAnnotation(dbFieldName = "chargeAmount", jdbcType = JdbcType.DECIMAL)
	private BigDecimal chargeAmount;
	/** 实际到账金额 */
	@FieldMapperAnnotation(dbFieldName = "realAmount", jdbcType = JdbcType.DECIMAL)
	private BigDecimal realAmount;
	/** 订单号 */
	@FieldMapperAnnotation(dbFieldName = "orderNo", jdbcType = JdbcType.VARCHAR)
	private String orderNo;
	/** 是否优惠 */
	@FieldMapperAnnotation(dbFieldName = "isDiscount", jdbcType = JdbcType.VARCHAR)
	private String isDiscount;

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

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(String isDiscount) {
		this.isDiscount = isDiscount;
	}
	
	

}
