package com.jcble.jcparking.common.service.admin;

import java.util.List;

import com.jcble.jcparking.common.model.admin.ParkingLot;

public interface ParkingLotService {

	/**
	 * 获取停车场列表
	 * 
	 * @param queryParams
	 * @return
	 */
	public List<ParkingLot> queryParkingLots(ParkingLot queryParams);

	/**
	 * 获取停车场详情
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ParkingLot getParkinglotById(Integer id) throws Exception;

	/**
	 * 获取附近停车场
	 * 
	 * @param range
	 *            范围
	 * @param coordinateX
	 *            当前位置x坐标
	 * @param coordinateY
	 *            当前位置y坐标
	 * @return
	 */
	public List<ParkingLot> getParkinglotByRange(double range, double coordinateX, double coordinateY);

	/**
	 * 获取停车场车位数据信息
	 * 
	 * @param parkinglotId
	 * @return
	 */
	public ParkingLot getParkingCount(Integer parkinglotId);

}
