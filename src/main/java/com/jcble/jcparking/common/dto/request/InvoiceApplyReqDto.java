package com.jcble.jcparking.common.dto.request;

import java.math.BigDecimal;
import java.util.List;

public class InvoiceApplyReqDto extends BaseReqDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 740665517224682698L;

	private String userId;
	private BigDecimal amount;
	private String invoiceHead;
	private String recevier;
	private String linkPhone;
	private String address;
	private List<OrderReqDto> order;

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

	public String getInvoiceHead() {
		return invoiceHead;
	}

	public void setInvoiceHead(String invoiceHead) {
		this.invoiceHead = invoiceHead;
	}

	public String getRecevier() {
		return recevier;
	}

	public void setRecevier(String recevier) {
		this.recevier = recevier;
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

	public List<OrderReqDto> getOrder() {
		return order;
	}

	public void setOrder(List<OrderReqDto> order) {
		this.order = order;
	}

}
