package com.jcble.jcparking.common.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcble.jcparking.common.dao.admin.ParkingAreaDao;
import com.jcble.jcparking.common.model.admin.ParkingArea;
import com.jcble.jcparking.common.service.admin.ParkingAreaService;

@Service
public class ParkingAreaServiceImpl implements ParkingAreaService {

	@Autowired
	private ParkingAreaDao dao;

	@Override
	public List<ParkingArea> queryParkingAreas(Integer parkingLotId) {
		List<ParkingArea> parkingAreas = dao.queryParkingAreas(parkingLotId);
		return parkingAreas;
	}
}
