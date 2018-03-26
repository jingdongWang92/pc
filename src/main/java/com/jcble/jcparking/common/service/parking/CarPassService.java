package com.jcble.jcparking.common.service.parking;

import com.jcble.jcparking.common.dto.request.ParkingReqDto;
import com.jcble.jcparking.common.dto.response.CarPassRespDto;

/**
 * 处理已经过车请求
 * 
 * @author Jingdong Wang
 * @date 2017年4月10日 上午10:21:28
 *
 */
public interface CarPassService {

	/**
	 * 车辆通过后业务处理
	 * 
	 * @param reqDto
	 */
	void carPass(ParkingReqDto reqDto);

	/**
	 *  车辆未入场
	 * @param reqDto
	 */
	CarPassRespDto carIn(ParkingReqDto reqDto);

	/**
	 * 车辆未出场
	 * @param reqDto
	 * @return
	 */
	CarPassRespDto carOut(ParkingReqDto reqDto);

}
