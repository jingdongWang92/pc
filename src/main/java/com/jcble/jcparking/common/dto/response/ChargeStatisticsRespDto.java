package com.jcble.jcparking.common.dto.response;

import java.math.BigDecimal;

/**
 * 收费统计响应实体
 * 
 * @author Jingdong Wang
 *
 */
public class ChargeStatisticsRespDto {

	/** 实收金额 */
	private BigDecimal receive;
	/** 应收金额 */
	private BigDecimal total;
	/** 网络支付金额 */
	private BigDecimal onlinePay;
	/** 现金支付金额 */
	private BigDecimal cashPay;

	public BigDecimal getReceive() {
		return receive;
	}

	public void setReceive(BigDecimal receive) {
		this.receive = receive;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getOnlinePay() {
		return onlinePay;
	}

	public void setOnlinePay(BigDecimal onlinePay) {
		this.onlinePay = onlinePay;
	}

	public BigDecimal getCashPay() {
		return cashPay;
	}

	public void setCashPay(BigDecimal cashPay) {
		this.cashPay = cashPay;
	}

}
