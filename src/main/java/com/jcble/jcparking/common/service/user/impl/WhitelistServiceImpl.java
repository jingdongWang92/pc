package com.jcble.jcparking.common.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcble.jcparking.common.dao.admin.ParkingLotDao;
import com.jcble.jcparking.common.dao.user.WhitelistDao;
import com.jcble.jcparking.common.model.admin.ParkingLot;
import com.jcble.jcparking.common.model.user.Whitelist;
import com.jcble.jcparking.common.service.user.WhitelistService;

/**
 * 固定车功能业务层实现类
 * 
 * @author Jingdong Wang
 * @date 2017年3月17日 上午11:45:44
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WhitelistServiceImpl implements WhitelistService {
	@Autowired
	private WhitelistDao whitelistDao;
	@Autowired
	private ParkingLotDao parkinglotDao;

	@Override
	public List<Whitelist> getWhitelistsByCar(Whitelist dto) {
		List<Whitelist> respList = new ArrayList<Whitelist>();
		List<Whitelist> whitelists = whitelistDao.getWhitelistsByCar(dto);
		if(whitelists != null && whitelists.size() > 0) {
			for (Whitelist whitelist : whitelists) {
				Whitelist respWhitelist = new Whitelist();
				//获取停车场信息
				ParkingLot parkinglot = parkinglotDao.getParkingLotByParkingId(whitelist.getParkingId());
				respWhitelist.setParkinglotName(whitelist.getParkinglotName());
				respWhitelist.setEndDate(whitelist.getEndDate());
				respWhitelist.setCoordinateX(parkinglot.getCoordinateX());
				respWhitelist.setCoordinateY(parkinglot.getCoordinateY());
				respList.add(respWhitelist);
			}
		}
		return respList;
	}
}
