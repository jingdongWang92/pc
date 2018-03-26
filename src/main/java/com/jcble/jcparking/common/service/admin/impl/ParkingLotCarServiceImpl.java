package com.jcble.jcparking.common.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcble.jcparking.common.dao.admin.ParkingLotCarDao;
import com.jcble.jcparking.common.model.admin.ParkingLotCar;
import com.jcble.jcparking.common.service.admin.ParkingLotCarService;

@Service
public class ParkingLotCarServiceImpl implements ParkingLotCarService {

	@Autowired
	private ParkingLotCarDao dao;

	@Override
	public List<ParkingLotCar> queryParkingLotCar(ParkingLotCar queryParams) {
		List<ParkingLotCar> cars = dao.queryParkingLotCarByPage(queryParams);
		return cars;
	}

}
