package com.jcble.jcparking.common.model.admin;

import java.math.BigDecimal;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;
import com.jcble.jcparking.common.model.devices.ParkingLock;
import com.jcble.jcparking.common.model.parking.IndoorMap;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "t_parking_order", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class ParkingOrder extends BaseDto {
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
	@FieldMapperAnnotation(dbFieldName = "createTime", jdbcType = JdbcType.VARCHAR)
	private String createTime;
	// 修改者
	@FieldMapperAnnotation(dbFieldName = "modifier", jdbcType = JdbcType.INTEGER)
	private Integer modifier;
	// 修改时间
	@FieldMapperAnnotation(dbFieldName = "modifyTime", jdbcType = JdbcType.VARCHAR)
	private String modifyTime;
	// 订单号
	@FieldMapperAnnotation(dbFieldName = "orderNo", jdbcType = JdbcType.VARCHAR)
	private String orderNo;
	// 用户
	@FieldMapperAnnotation(dbFieldName = "userId", jdbcType = JdbcType.VARCHAR)
	private String userId;
	// 车位
	@FieldMapperAnnotation(dbFieldName = "parkingId", jdbcType = JdbcType.INTEGER)
	private Integer parkingId;
	// 车牌号码
	@FieldMapperAnnotation(dbFieldName = "carNumber", jdbcType = JdbcType.VARCHAR)
	private String carNumber;
	// 车辆类型
	@FieldMapperAnnotation(dbFieldName = "carType", jdbcType = JdbcType.VARCHAR)
	private String carType;
	// 车辆入场时间
	@FieldMapperAnnotation(dbFieldName = "inTime", jdbcType = JdbcType.VARCHAR)
	private String inTime;
	// 车辆实际出场时间
	@FieldMapperAnnotation(dbFieldName = "outTime", jdbcType = JdbcType.VARCHAR)
	private String outTime;
	// 订单状态
	@FieldMapperAnnotation(dbFieldName = "orderStatus", jdbcType = JdbcType.VARCHAR)
	private String orderStatus;
	// 是否为免费订单
	@FieldMapperAnnotation(dbFieldName = "isFree", jdbcType = JdbcType.VARCHAR)
	private String isFree;
	// 免费原因
	@FieldMapperAnnotation(dbFieldName = "reason", jdbcType = JdbcType.VARCHAR)
	private String reason;
	// 总金额
	@FieldMapperAnnotation(dbFieldName = "totalFee", jdbcType = JdbcType.DECIMAL)
	private BigDecimal totalFee;
	// 折扣金额
	@FieldMapperAnnotation(dbFieldName = "discountFee", jdbcType = JdbcType.DECIMAL)
	private BigDecimal discountFee;
	// 已支付金额
	@FieldMapperAnnotation(dbFieldName = "payedFee", jdbcType = JdbcType.DECIMAL)
	private BigDecimal payedFee;
	// 未支付金额
	@FieldMapperAnnotation(dbFieldName = "unPayFee", jdbcType = JdbcType.DECIMAL)
	private BigDecimal unPayFee;
	// 支付状态
	@FieldMapperAnnotation(dbFieldName = "payStatus", jdbcType = JdbcType.CHAR)
	private String payStatus;
	// 支付方式
	@FieldMapperAnnotation(dbFieldName = "payWay", jdbcType = JdbcType.VARCHAR)
	private String payWay;
	// 支付时间
	@FieldMapperAnnotation(dbFieldName = "payTime", jdbcType = JdbcType.VARCHAR)
	private String payTime;
	// 订单来源
	@FieldMapperAnnotation(dbFieldName = "orderSource", jdbcType = JdbcType.VARCHAR)
	private String orderSource;
	// 操作员
	@FieldMapperAnnotation(dbFieldName = "operatorId", jdbcType = JdbcType.INTEGER)
	private Integer operatorId;
	// 图片url
	@FieldMapperAnnotation(dbFieldName = "fileUrl", jdbcType = JdbcType.VARCHAR)
	private String fileUrl;
	// 开票状态(发票) 0：不具备 1：已申请 2：已完成
	@FieldMapperAnnotation(dbFieldName = "orderInvoiceStatus", jdbcType = JdbcType.VARCHAR)
	private String orderInvoiceStatus;
	// 停车场
	@FieldMapperAnnotation(dbFieldName = "parkinglotId", jdbcType = JdbcType.INTEGER)
	private Integer parkinglotId;
	
	private String parkinglotName;
	private String duration;
	private Double coordinateX;
	private Double coordinateY;
	private ParkingLock parkingLock;
	private IndoorMap indoorMap;

	public Integer getParkinglotId() {
		return parkinglotId;
	}

	public void setParkinglotId(Integer parkinglotId) {
		this.parkinglotId = parkinglotId;
	}

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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public BigDecimal getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(BigDecimal discountFee) {
		this.discountFee = discountFee;
	}

	public BigDecimal getPayedFee() {
		return payedFee;
	}

	public void setPayedFee(BigDecimal payedFee) {
		this.payedFee = payedFee;
	}

	public BigDecimal getUnPayFee() {
		return unPayFee;
	}

	public void setUnPayFee(BigDecimal unPayFee) {
		this.unPayFee = unPayFee;
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

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getParkinglotName() {
		return parkinglotName;
	}

	public void setParkinglotName(String parkinglotName) {
		this.parkinglotName = parkinglotName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getIsFree() {
		return isFree;
	}

	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getOrderInvoiceStatus() {
		return orderInvoiceStatus;
	}

	public void setOrderInvoiceStatus(String orderInvoiceStatus) {
		this.orderInvoiceStatus = orderInvoiceStatus;
	}

	public Double getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(Double coordinateX) {
		this.coordinateX = coordinateX;
	}

	public Double getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(Double coordinateY) {
		this.coordinateY = coordinateY;
	}

	public ParkingLock getParkingLock() {
		return parkingLock;
	}

	public void setParkingLock(ParkingLock parkingLock) {
		this.parkingLock = parkingLock;
	}

	public IndoorMap getIndoorMap() {
		return indoorMap;
	}

	public void setIndoorMap(IndoorMap indoorMap) {
		this.indoorMap = indoorMap;
	}

}
