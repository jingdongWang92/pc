package com.jcble.jcparking.common.service.admin;

import java.util.List;

import com.jcble.jcparking.common.model.admin.Parking;

public interface ParkingService {

	/**
	 * 获取车位列表
	 * 
	 * @param dto
	 * @return
	 */
	public List<Parking> queryParkings(Parking queryParams);

	/**
	 * 获取空闲车位
	 * 
	 * @param dto
	 * @return
	 */
	public List<Parking> queryUnusedParkings(Parking queryParams);

	/**
	 * 获取一个空闲车位
	 * @param parkinglotId 
	 * @return
	 */
	public Parking getFreeParking(Integer parkinglotId);
	
	/**
	 * 获取一个可预约车位
	 * @param parkinglotId 
	 * @return
	 */
	public Parking getReservationParking(Integer parkinglotId);
	
	/**
	 * 获取一个临停车位
	 * @param parkinglotId 
	 * @return
	 */
	public Parking getTempParking(Integer parkinglotId);

}
