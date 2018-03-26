package com.jcble.jcparking.common.dao.user;

import java.util.List;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.user.UserCar;

/**
 * 用户车辆层接口
 * 
 * @author Jingdong Wang
 * 
 */
public interface UserCarDao extends BaseSimpleDao {

	/**
	 * 根据车牌号获取车辆信息
	 * 
	 * @param carNumber
	 * @return
	 */
	public List<UserCar> getCarByCarNumber(String carNumber);

	/**
	 * 根据用户id和车牌号获取车辆信息
	 * 
	 * @param dto
	 * @return
	 */
	public UserCar getCarByUserIdAndCarNumber(UserCar dto);

	/**
	 * 根据用户id获取用户车辆信息
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserCar> getCarsByUserId(String userId);

	/**
	 * 修改车辆状态为入场
	 * 
	 * @param carNumber 车牌号
	 */
	public void updateCarStatusToInByCarNumber(String carNumber);

}
