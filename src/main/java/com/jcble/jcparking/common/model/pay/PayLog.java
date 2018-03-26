package com.jcble.jcparking.common.model.pay;

import java.math.BigDecimal;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

/**
 * 支付日志实体类
 * 
 * @author Jingdong Wang
 * @date 2017年3月21日 下午2:54:19
 *
 */
@TableMapperAnnotation(tableName = "t_pay_log", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class PayLog extends BaseDto {

	private static final long serialVersionUID = 5161633269899565640L;

	/** 主键id */
	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.INTEGER)
	private String id;
	/** 用户id */
	@FieldMapperAnnotation(dbFieldName = "userId", jdbcType = JdbcType.VARCHAR)
	private String userId;
	/** 车位 */
	@FieldMapperAnnotation(dbFieldName = "parkingId", jdbcType = JdbcType.INTEGER)
	private Integer parkingId;
	/** 交易时间 */
	@FieldMapperAnnotation(dbFieldName = "tradeTime", jdbcType = JdbcType.VARCHAR)
	private String tradeTime;
	/** 支付方式 */
	@FieldMapperAnnotation(dbFieldName = "payWay", jdbcType = JdbcType.VARCHAR)
	private String payWay;
	/** 费用类型 */
	@FieldMapperAnnotation(dbFieldName = "type", jdbcType = JdbcType.VARCHAR)
	private String type;
	/** 金额 */
	@FieldMapperAnnotation(dbFieldName = "amount", jdbcType = JdbcType.DECIMAL)
	private BigDecimal amount;
	/** 订单号 */
	@FieldMapperAnnotation(dbFieldName = "orderNo", jdbcType = JdbcType.VARCHAR)
	private String orderNo;
	/** 支付状态 */
	@FieldMapperAnnotation(dbFieldName = "payStatus", jdbcType = JdbcType.CHAR)
	private String payStatus;
	/** 交易状态 */
	@FieldMapperAnnotation(dbFieldName = "tradeStatus", jdbcType = JdbcType.VARCHAR)
	private String tradeStatus;

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

	public Integer getParkingId() {
		return parkingId;
	}

	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
}
