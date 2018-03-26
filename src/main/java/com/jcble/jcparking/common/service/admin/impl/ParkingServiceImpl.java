package com.jcble.jcparking.common.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcble.jcparking.common.CommonEnum;
import com.jcble.jcparking.common.dao.admin.ParkingDao;
import com.jcble.jcparking.common.exception.ParkingServiceException;
import com.jcble.jcparking.common.model.admin.Parking;
import com.jcble.jcparking.common.service.admin.ParkingService;

@Service
public class ParkingServiceImpl implements ParkingService {

	@Autowired
	private ParkingDao dao;
	
	@Override
	public List<Parking> queryParkings(Parking queryParams) {
		List<Parking> parkings = dao.queryParkingsByPage(queryParams);
		return parkings;
	}

	@Override
	public List<Parking> queryUnusedParkings(Parking queryParams) {
		List<Parking> respParkings = new ArrayList<Parking>();
		List<Parking> unUsedParkings = dao.queryUnusedParkings(queryParams);
		if(unUsedParkings != null && unUsedParkings.size() > 0) {
			for (Parking parking : unUsedParkings) {
				Parking respParking = new Parking();
				respParking.setId(parking.getId());
				respParking.setParkingNo(parking.getParkingNo());
				respParkings.add(respParking);
			}
		}
		return respParkings;
	}

	@Override
	public Parking getFreeParking(Integer parkinglotId) {
		Parking dto = new Parking();
		dto.setParkinglotId(parkinglotId);
		dto.setStatus(CommonEnum.ParkingStatus.Free.code);
		List<Parking> unUsedParkings = dao.queryUnusedParkings(dto);
		if(unUsedParkings == null || unUsedParkings.size() < 1) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10021);
		}
		return unUsedParkings.get(0);
	}
	
	@Override
	public Parking getTempParking(Integer parkinglotId) {
		Parking dto = new Parking();
		dto.setParkinglotId(parkinglotId);
		dto.setStatus(CommonEnum.ParkingStatus.Free.code);
		dto.setIsRevatation(CommonEnum.YesNoFlg.No.code);
		Parking tempParking = dao.getFreeParking(dto);
		if(tempParking == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10027);
		}
		return tempParking;
	}

	@Override
	public Parking getReservationParking(Integer parkinglotId) {
		Parking dto = new Parking();
		dto.setParkinglotId(parkinglotId);
		dto.setStatus(CommonEnum.ParkingStatus.Free.code);
		dto.setIsRevatation(CommonEnum.YesNoFlg.Yes.code);
		Parking reservationParking = dao.getFreeParking(dto);
		if(reservationParking == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10021);
		}
		return reservationParking;
	}
}
