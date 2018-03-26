package com.jcble.jcparking.common.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcble.jcparking.common.CommonEnum;
import com.jcble.jcparking.common.dao.user.UserCarDao;
import com.jcble.jcparking.common.dao.user.UserDao;
import com.jcble.jcparking.common.dto.request.UserCarReqDto;
import com.jcble.jcparking.common.exception.ParkingServiceException;
import com.jcble.jcparking.common.model.user.UserCar;
import com.jcble.jcparking.common.model.user.User;
import com.jcble.jcparking.common.service.user.UserCarService;

import baseproj.common.util.DateUtil;

/**
 * 用户车辆业务接口实现
 * 
 * @author Jingdong Wang
 * @date 2017年3月13日 下午4:51:44
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserCarServiceImpl implements UserCarService {

	@Autowired
	private UserCarDao userCarDao;
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<UserCar> getCarsByUserId(String userId) {
		List<UserCar> cars = userCarDao.getCarsByUserId(userId);
		return cars;

	}

	@Override
	public void createUserCar(UserCarReqDto reqDto) throws Exception {
		User user = userDao.getUserByUserId(reqDto.getUserId());
		if (user == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10003);
		}
		UserCar dto = new UserCar();
		dto.setCarNumber(reqDto.getCarNumber());
		dto.setUserId(reqDto.getUserId());
		UserCar car = userCarDao.getCarByUserIdAndCarNumber(dto);
		if (car != null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10012);
		}
		dto.setCreateTime(DateUtil.getDateTime());
		dto.setStatus(CommonEnum.CarStatus.Out.code);
		userCarDao.insert(dto);
	}

	@Override
	public void deleteCar(Integer carId) throws Exception {
		UserCar carDto = new UserCar();
		carDto.setId(carId);
		UserCar car = userCarDao.select(carDto);
		if(car == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10011);
		} else {
			if(CommonEnum.CarStatus.Reservation.code.equals(car.getStatus())) {
				throw new ParkingServiceException(ParkingServiceException.ERROR_10013);
			} else if(CommonEnum.CarStatus.In.code.equals(car.getStatus()) || CommonEnum.CarStatus.NotLeave.code.equals(car.getStatus())) {
				throw new ParkingServiceException(ParkingServiceException.ERROR_10014);
			} 
			userCarDao.delete(car);
		}
	}

	@Override
	public void updateCar(UserCarReqDto reqDto) throws Exception {
		UserCar carDto = new UserCar();
		carDto.setId(reqDto.getCarId());
		UserCar car = userCarDao.select(carDto);
		if(car == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10011);
		}
		car.setCarNumber(reqDto.getCarNumber());
		userCarDao.update(car);
	}

}
