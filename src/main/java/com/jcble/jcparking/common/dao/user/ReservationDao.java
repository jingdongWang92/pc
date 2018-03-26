package com.jcble.jcparking.common.dao.user;

import java.util.List;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.user.Reservation;

/**
 * 预约订单模块
 * 
 * @author Jingdong Wang
 * 
 */
public interface ReservationDao extends BaseSimpleDao {

	/**
	 * 获取个人预约订单记录
	 * 
	 * @param dto
	 * @return
	 */
	List<Reservation> queryReservationsByPage(Reservation dto);

	/**
	 * 获取预约订单
	 * 
	 * @param dto
	 * @return
	 */
	List<Reservation> getReservations(Reservation dto);

	/**
	 * 获取车辆预约订单记录
	 * 
	 * @param dto
	 * @return
	 */
	Reservation getReservationByCar(Reservation dto);

	/**
	 * 根据订单号获取预约记录
	 * 
	 * @param orderNo
	 * @return
	 */
	Reservation getReservationByOrderNo(String orderNo);

}
