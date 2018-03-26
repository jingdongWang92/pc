package com.jcble.jcparking.common.dto.response;

import java.math.BigDecimal;

public class ChargeRuleRespDto {

	/** 收费规则名称 */
	private String ruleName;
	/** 收费规则名称 */
	private Integer freeTime;
	/** 收费规则名称 */
	private BigDecimal capping;
	/** 小型车辆价格 */
	private BigDecimal smallCarPrice;
	/** 中型车辆价格 */
	private BigDecimal midCarPrice;
	/** 大型车辆价格 */
	private BigDecimal bigCarPrice;
	/** 其他车辆价格 */
	private BigDecimal otherCarPrice;

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public Integer getFreeTime() {
		return freeTime;
	}

	public void setFreeTime(Integer freeTime) {
		this.freeTime = freeTime;
	}

	public BigDecimal getCapping() {
		return capping;
	}

	public void setCapping(BigDecimal capping) {
		this.capping = capping;
	}

	public BigDecimal getSmallCarPrice() {
		return smallCarPrice;
	}

	public void setSmallCarPrice(BigDecimal smallCarPrice) {
		this.smallCarPrice = smallCarPrice;
	}

	public BigDecimal getMidCarPrice() {
		return midCarPrice;
	}

	public void setMidCarPrice(BigDecimal midCarPrice) {
		this.midCarPrice = midCarPrice;
	}

	public BigDecimal getBigCarPrice() {
		return bigCarPrice;
	}

	public void setBigCarPrice(BigDecimal bigCarPrice) {
		this.bigCarPrice = bigCarPrice;
	}

	public BigDecimal getOtherCarPrice() {
		return otherCarPrice;
	}

	public void setOtherCarPrice(BigDecimal otherCarPrice) {
		this.otherCarPrice = otherCarPrice;
	}

}
