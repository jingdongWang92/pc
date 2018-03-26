package com.jcble.jcparking.common.model.user;

import java.math.BigDecimal;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

/**
 * 发票记录实体
 * 
 * @author Jingdong Wang
 * @date 2017年3月14日 下午5:54:20
 *
 */
@TableMapperAnnotation(tableName = "t_invoice", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Invoice extends BaseDto {

	private static final long serialVersionUID = 5161633269899565640L;

	/** 主键id */
	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.INTEGER)
	private Integer id;
	/** 发票编号 */
	@FieldMapperAnnotation(dbFieldName = "invoiceNo", jdbcType = JdbcType.VARCHAR)
	private String invoiceNo;
	/** 用户id */
	@FieldMapperAnnotation(dbFieldName = "userId", jdbcType = JdbcType.VARCHAR)
	private String userId;
	/** 申请时间 */
	@FieldMapperAnnotation(dbFieldName = "applyDate", jdbcType = JdbcType.VARCHAR)
	private String applyDate;
	/** 发票金额 */
	@FieldMapperAnnotation(dbFieldName = "amount", jdbcType = JdbcType.DECIMAL)
	private BigDecimal amount;
	/** 发票抬头 */
	@FieldMapperAnnotation(dbFieldName = "invoiceHead", jdbcType = JdbcType.VARCHAR)
	private String invoiceHead;
	/** 发票状态 */
	@FieldMapperAnnotation(dbFieldName = "status", jdbcType = JdbcType.VARCHAR)
	private String status;
	/** 收件人 */
	@FieldMapperAnnotation(dbFieldName = "receiver", jdbcType = JdbcType.VARCHAR)
	private String receiver;
	/** 收件人联系电话 */
	@FieldMapperAnnotation(dbFieldName = "linkPhone", jdbcType = JdbcType.VARCHAR)
	private String linkPhone;
	/** 收件地址 */
	@FieldMapperAnnotation(dbFieldName = "address", jdbcType = JdbcType.VARCHAR)
	private String address;
	/** 邮政编码 */
	@FieldMapperAnnotation(dbFieldName = "postcode", jdbcType = JdbcType.VARCHAR)
	private String postcode;
	/** 快递运费 */
	@FieldMapperAnnotation(dbFieldName = "deliveryFee", jdbcType = JdbcType.DECIMAL)
	private String deliveryFee;
	/** 快递公司 */
	@FieldMapperAnnotation(dbFieldName = "deliveryCompany", jdbcType = JdbcType.VARCHAR)
	private String deliveryCompany;
	/** 快递单号 */
	@FieldMapperAnnotation(dbFieldName = "deliveryNo", jdbcType = JdbcType.VARCHAR)
	private String deliveryNo;
	/** 开票时间 */
	@FieldMapperAnnotation(dbFieldName = "billingDate", jdbcType = JdbcType.VARCHAR)
	private String billingDate;
	/** 开票时间 */
	@FieldMapperAnnotation(dbFieldName = "isFreeDelivery", jdbcType = JdbcType.VARCHAR)
	private String isFreeDelivery;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(String deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public String getInvoiceHead() {
		return invoiceHead;
	}

	public void setInvoiceHead(String invoiceHead) {
		this.invoiceHead = invoiceHead;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getDeliveryCompany() {
		return deliveryCompany;
	}

	public void setDeliveryCompany(String deliveryCompany) {
		this.deliveryCompany = deliveryCompany;
	}

	public String getDeliveryNo() {
		return deliveryNo;
	}

	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(String billingDate) {
		this.billingDate = billingDate;
	}

	public String getIsFreeDelivery() {
		return isFreeDelivery;
	}

	public void setIsFreeDelivery(String isFreeDelivery) {
		this.isFreeDelivery = isFreeDelivery;
	}

}
