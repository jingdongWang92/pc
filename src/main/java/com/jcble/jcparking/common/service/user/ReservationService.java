package com.jcble.jcparking.common.service.user;

import java.util.List;

import com.jcble.jcparking.common.dto.request.ReservationReqDto;
import com.jcble.jcparking.common.dto.response.ReserveRespDto;
import com.jcble.jcparking.common.model.user.Reservation;

/**
 * 预约订单模块
 * 
 * @author Jingdong Wang
 * @date 2017年3月14日 上午10:22:28
 *
 */
public interface ReservationService {

	/**
	 * 获取个人预约订单记录
	 * 
	 * @param dto
	 *            用户id
	 * @return
	 * @throws Exception 
	 */
	List<Reservation> getUserReservations(Reservation dto) throws Exception;

	/**
	 * 预约车位
	 * 
	 * @param reqDto
	 * @return
	 * @throws Exception
	 */
	ReserveRespDto reservationParking(ReservationReqDto reqDto) throws Exception;

	/**
	 * 取消预约
	 * 
	 * @param id
	 *            预约订单id
	 * @throws Exception
	 */
	void cancelReservation(Integer id) throws Exception;

	/**
	 * 根据车辆获取预约订单记录
	 * 
	 * @param carId
	 * @param x
	 * @param y
	 * @return
	 * @throws Exception
	 */
	Reservation getReservationByCar(Integer carId,double x,double y) throws Exception;

	/**
	 * 退款
	 * 
	 * @param id
	 * @throws Exception 
	 */
	void refund(Integer id) throws Exception;

}
