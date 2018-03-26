package com.jcble.jcparking.common.dao.admin;

import java.util.List;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.admin.ParkingLotCar;

/**
 * 停车场dao层接口
 * 
 * @author Jingdong Wang
 * 
 */
public interface ParkingLotCarDao extends BaseSimpleDao {

	public List<ParkingLotCar> queryParkingLotCarByPage(ParkingLotCar param);

	public ParkingLotCar getCarByCarNumber(ParkingLotCar carDto);

	/**
	 * 根据车位获取场内车辆信息
	 * @param parkingId
	 * @return
	 */
	public ParkingLotCar getParkingLotCarByParkingId(Integer parkingId);

	/**
	 * 根据车牌号获取场内车辆信息
	 * @param carNumber
	 * @return
	 */
	public ParkingLotCar getParkingLotCarByCarNumber(String carNumber);

}
