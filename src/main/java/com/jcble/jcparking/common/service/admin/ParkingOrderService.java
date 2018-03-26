package com.jcble.jcparking.common.service.admin;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import com.jcble.jcparking.common.dto.request.OrderReqDto;
import com.jcble.jcparking.common.dto.response.ChargeStatisticsRespDto;
import com.jcble.jcparking.common.model.admin.ChargeRule;
import com.jcble.jcparking.common.model.admin.ParkingLot;
import com.jcble.jcparking.common.model.admin.ParkingOrder;
import com.jcble.jcparking.common.service.BaseService;

/**
 * 停车订单Service层接口
 * 
 * @author Jingdong Wang
 * @Date 2017-02-24
 * 
 */
public interface ParkingOrderService extends BaseService {

	/**
	 * 创建停车订单
	 * 
	 * @param reqDto
	 * @throws Exception
	 */
	public void createParkingOrder(OrderReqDto reqDto) throws Exception;

	/**
	 * 获取订单详情
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public ParkingOrder getOrderById(Integer orderId) throws Exception;

	/**
	 * 确认收费
	 * 
	 * @param reqDto
	 * @throws Exception
	 */
	public void chargeConfirm(OrderReqDto reqDto) throws Exception;

	/**
	 * 订单结算
	 * 
	 * @param orderId
	 * @throws Exception
	 */
	public ParkingOrder orderSettlement(Integer orderId) throws Exception;

	/**
	 * 免单设置
	 * 
	 * @param reqDto
	 * @throws Exception
	 */
	public void setOrderFree(OrderReqDto reqDto) throws Exception;

	/**
	 * 收费员账单统计
	 * 
	 * @param reqDto
	 */
	public ChargeStatisticsRespDto orderStatistics(ParkingOrder reqDto);

	/**
	 * 计算订单总金额
	 * 
	 * @param order
	 * @throws Exception
	 */
	public BigDecimal parkingOrderSettlement(ParkingOrder order) throws Exception;

	/**
	 * 车辆出场处理
	 * 
	 * @param parkingId
	 *            车位id
	 * @throws Exception
	 */
	public void carOut(Integer parkingId) throws Exception;

	/**
	 * 车辆入场处理
	 * 
	 * @param parkingId
	 *            车位id
	 * @throws Exception
	 */
	public void carIn(Integer parkingId);

	/**
	 * 修改停车订单信息
	 * 
	 * @param reqDto
	 * @throws Exception
	 */
	public void updateOrder(OrderReqDto reqDto) throws Exception;

	/**
	 * 根据已支付费用计算停车时长
	 * 
	 * @param payedFee
	 *            已支付费用
	 * @param chargeRule
	 *            收费规则
	 * @return
	 */
	public Integer getParkingTime(BigDecimal payedFee, ChargeRule chargeRule);

	/**
	 * 获取用户订单信息
	 * 
	 * @param order
	 * @return
	 */
	public List<ParkingOrder> queryUserOrdersByPage(ParkingOrder order);

	/**
	 * 获取车辆未结束的停车订单信息
	 * 
	 * @param carId
	 * @return
	 * @throws Exception
	 */
	public ParkingOrder getOrderByCar(Integer carId) throws Exception;

	/**
	 * 获取未结束的订单详情
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public ParkingOrder getUserOrderById(Integer orderId) throws Exception;

	/**
	 * 停车费用计算
	 * 
	 * @param inTime
	 * @param dateTime
	 * @param chargeRule
	 * @param parkinglot
	 * @return
	 * @throws Exception
	 */
	public BigDecimal parkingFeeCompute(String inTime, String dateTime, ChargeRule chargeRule,
			ParkingLot parkinglot) throws Exception;

	/**
	 * 判断停车订单是否超时
	 * 
	 * @param order
	 * @return
	 * @throws ParseException
	 */
	public boolean isOverTime(ParkingOrder order) throws ParseException;

}
