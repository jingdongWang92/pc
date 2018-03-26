package com.jcble.jcparking.common.service.admin;

import java.util.List;

import com.jcble.jcparking.common.model.admin.ParkingLotCar;

/**
 * 停车场车辆相关Service接口
 * 
 * @author Jingdong Wang
 * @date 2017-02-24
 *
 */
public interface ParkingLotCarService {

	/**
	 * 获取场内车辆列表
	 * 
	 * @param queryParams
	 * @return
	 */
	List<ParkingLotCar> queryParkingLotCar(ParkingLotCar queryParams);

}
