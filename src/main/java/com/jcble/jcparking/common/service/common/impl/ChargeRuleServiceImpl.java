package com.jcble.jcparking.common.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcble.jcparking.common.dao.admin.ParkingLotDao;
import com.jcble.jcparking.common.dao.common.ChargeRuleDao;
import com.jcble.jcparking.common.dto.response.ChargeRuleRespDto;
import com.jcble.jcparking.common.exception.ParkingServiceException;
import com.jcble.jcparking.common.model.admin.ChargeRule;
import com.jcble.jcparking.common.model.admin.ParkingLot;
import com.jcble.jcparking.common.service.AbstractBaseService;
import com.jcble.jcparking.common.service.common.ChargeRuleService;

@Service
public class ChargeRuleServiceImpl extends AbstractBaseService implements ChargeRuleService {

	@Autowired
	private ChargeRuleDao ruleDao;
	
	@Autowired
	private ParkingLotDao parkingLotDao;

	@Override
	public ChargeRuleRespDto getRuleByParkinglot(Integer parkinglotId) throws Exception {
		ChargeRuleRespDto resp = new ChargeRuleRespDto();
		//获取停车场信息
		ParkingLot parlinglotDto = new ParkingLot();
		parlinglotDto.setId(parkinglotId);
		ParkingLot parkingLot = parkingLotDao.select(parlinglotDto);
		if(parkingLot == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10010);
		}
		resp.setFreeTime(parkingLot.getFreeTime());
		//获取收费规则
		ChargeRule rule = ruleDao.getRuleByParkinglot(parkinglotId);
		if(rule != null) {
			resp.setRuleName(rule.getRuleName());
			resp.setCapping(rule.getUpLimitFee24());
			resp.setSmallCarPrice(rule.getPrice());
			resp.setMidCarPrice(rule.getPrice());
			resp.setBigCarPrice(rule.getPrice());
			resp.setOtherCarPrice(rule.getPrice());
		}
		return resp;
	}


}
