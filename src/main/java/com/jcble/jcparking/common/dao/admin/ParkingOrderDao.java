package com.jcble.jcparking.common.dao.admin;

import java.util.List;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.BaseDto;
import com.jcble.jcparking.common.model.admin.ParkingOrder;

/**
 * 停车订单dao层接口
 * 
 * @author Jingdong Wang
 * @Date 2017-02-24
 * 
 */
public interface ParkingOrderDao extends BaseSimpleDao {

	/**
	 * 分页加载数据
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	<T extends BaseDto> List<T> queryByPage(T dto) throws Exception;

	/**
	 * 获取订单详情
	 * 
	 * @param id
	 *            订单id
	 * @return
	 */
	public ParkingOrder getOrderById(Integer id);

	/**
	 * 获取欠费订单
	 * 
	 * @param ownFeeOrderDto
	 * @return
	 */
	List<ParkingOrder> getOwnFeeOrders(ParkingOrder ownFeeOrderDto);

	/**
	 * 获取管理员的订单
	 * 
	 * @param operatorId
	 * @return
	 */
	List<ParkingOrder> getOrdersByOperator(ParkingOrder dto);

	/**
	 * 根据订单号获取订单
	 * 
	 * @param orderNo
	 * @return
	 */
	ParkingOrder getOrderByOrderNo(String orderNo);

	/**
	 * 获取所有未结束且已支付的订单信息
	 * 
	 * @param dto
	 * @return
	 */
	List<ParkingOrder> queryPayedOrders(ParkingOrder dto);

	/**
	 * 获取用户停车订单
	 * 
	 * @param dto
	 * @return
	 */
	List<ParkingOrder> queryUserOrdersByPage(ParkingOrder dto);

	/**
	 * 获取车辆未结束的停车订单信息
	 * @param dto
	 * @return
	 */
	ParkingOrder getOrderByCar(ParkingOrder dto);

}
