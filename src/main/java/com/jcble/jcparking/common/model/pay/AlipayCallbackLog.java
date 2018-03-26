package com.jcble.jcparking.common.model.pay;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "t_alipay_callback_log", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class AlipayCallbackLog extends BaseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.INTEGER)
	private Integer id; // 主键ID
	@FieldMapperAnnotation(dbFieldName = "orderNo", jdbcType = JdbcType.VARCHAR)
	private String orderNo; // 平台订单号
	@FieldMapperAnnotation(dbFieldName = "aliTradeNo", jdbcType = JdbcType.VARCHAR)
	private String aliTradeNo; // 支付宝交易流水号
	@FieldMapperAnnotation(dbFieldName = "orderType", jdbcType = JdbcType.VARCHAR)
	private String orderType; // 订单类型
	@FieldMapperAnnotation(dbFieldName = "buyerAccount", jdbcType = JdbcType.VARCHAR)
	private String buyerAccount; // 购买人支付宝账号
	@FieldMapperAnnotation(dbFieldName = "buyerId", jdbcType = JdbcType.VARCHAR)
	private String buyerId; // 购买人支付宝ID
	@FieldMapperAnnotation(dbFieldName = "sellerAccount", jdbcType = JdbcType.VARCHAR)
	private String sellerAccount; // 卖家支付宝账号
	@FieldMapperAnnotation(dbFieldName = "sellerId", jdbcType = JdbcType.VARCHAR)
	private String sellerId; // 卖家支付宝ID
	@FieldMapperAnnotation(dbFieldName = "notifyId", jdbcType = JdbcType.VARCHAR)
	private String notifyId; // 返回通知ID
	@FieldMapperAnnotation(dbFieldName = "notifyType", jdbcType = JdbcType.VARCHAR)
	private String notifyType; // 返回通知类型
	@FieldMapperAnnotation(dbFieldName = "notifyTime", jdbcType = JdbcType.VARCHAR)
	private String notifyTime; // 返回通知时间
	@FieldMapperAnnotation(dbFieldName = "orderName", jdbcType = JdbcType.VARCHAR)
	private String orderName; // 订单名称
	@FieldMapperAnnotation(dbFieldName = "total_amount", jdbcType = JdbcType.VARCHAR)
	private String totalAmount; // 支付费用
	@FieldMapperAnnotation(dbFieldName = "refund_fee", jdbcType = JdbcType.VARCHAR)
	private String refundFee; // 退款金额
	@FieldMapperAnnotation(dbFieldName = "tradeStatus", jdbcType = JdbcType.VARCHAR)
	private String tradeStatus; // 支付宝交易状态

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAliTradeNo() {
		return aliTradeNo;
	}

	public void setAliTradeNo(String aliTradeNo) {
		this.aliTradeNo = aliTradeNo;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getBuyerAccount() {
		return buyerAccount;
	}

	public void setBuyerAccount(String buyerAccount) {
		this.buyerAccount = buyerAccount;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getSellerAccount() {
		return sellerAccount;
	}

	public void setSellerAccount(String sellerAccount) {
		this.sellerAccount = sellerAccount;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(String refundFee) {
		this.refundFee = refundFee;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

}
