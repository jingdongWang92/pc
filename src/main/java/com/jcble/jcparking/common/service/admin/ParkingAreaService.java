package com.jcble.jcparking.common.service.admin;

import java.util.List;

import com.jcble.jcparking.common.model.admin.ParkingArea;

public interface ParkingAreaService {
	
	/**
	 * 获取停车场区域
	 * @param parkingLotId 停车场唯一标识
	 * @return
	 */
	public List<ParkingArea> queryParkingAreas(Integer parkingLotId);

}
