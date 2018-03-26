package com.jcble.jcparking.common.service.user;

import java.util.List;

import com.jcble.jcparking.common.dto.request.UserCarReqDto;
import com.jcble.jcparking.common.model.user.UserCar;

/**
 * 用户车辆业务接口
 * 
 * @author Jingdong Wang
 * 
 */
public interface UserCarService {

	/**
	 * 获取用户车辆信息
	 * 
	 * @param userId
	 * @return
	 */
	List<UserCar> getCarsByUserId(String userId);

	/**
	 * 创建用户车辆信息
	 * 
	 * @param reqDto
	 * @throws Exception
	 */
	void createUserCar(UserCarReqDto reqDto) throws Exception;

	/**
	 * 删除用户车辆信息
	 * 
	 * @param carId
	 * @throws Exception
	 */
	void deleteCar(Integer carId) throws Exception;

	/**
	 * 修改用户车辆信息
	 * 
	 * @param reqDto
	 * @throws Exception
	 */
	void updateCar(UserCarReqDto reqDto) throws Exception;
}
