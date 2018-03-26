package com.jcble.jcparking.common.dao.admin;

import java.util.List;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.admin.Parking;

/**
 * 车位dao层接口
 * 
 * @author Jingdong Wang
 * 
 */
public interface ParkingDao extends BaseSimpleDao {

	/**
	 * 分页查询车位信息
	 * @param param
	 * @return
	 */
	public List<Parking> queryParkingsByPage(Parking param);

	/**
	 * 获取未使用车位
	 * @param dto
	 * @return
	 */
	public List<Parking> queryUnusedParkings(Parking dto);

	/**
	 * 设置车位为空闲状态
	 * @param parkingId
	 */
	public void updateParkingFree(Integer parkingId);

	/**
	 * 设置车位为占用状态
	 * @param parkingId
	 */
	public void updateParkingUse(Integer parkingId);

	/**
	 * 获取一个空闲车位
	 * @param dto
	 * @return
	 */
	public Parking getFreeParking(Parking dto);

}
