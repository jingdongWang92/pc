package com.jcble.jcparking.common.service.admin.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcble.jcparking.common.dao.admin.ParkingLotDao;
import com.jcble.jcparking.common.dao.common.ChargeRuleDao;
import com.jcble.jcparking.common.model.admin.ChargeRule;
import com.jcble.jcparking.common.model.admin.ParkingLot;
import com.jcble.jcparking.common.service.admin.ParkingLotService;
import com.jcble.jcparking.common.utils.MathUtil;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

	@Autowired
	private ParkingLotDao dao;
	@Autowired
	private ChargeRuleDao ruleDao;

	@Override
	public List<ParkingLot> queryParkingLots(ParkingLot queryParams) {
		List<ParkingLot> parkingLots = dao.queryParkingLotsByPage(queryParams);
		return parkingLots;
	}

	@Override
	public ParkingLot getParkinglotById(Integer parkinglotId) throws Exception {
		ParkingLot resp = new ParkingLot();
		ParkingLot dto = new ParkingLot();
		dto.setId(parkinglotId);
		ParkingLot parkinglot = dao.select(dto);
		//获取收费规则
		ChargeRule rule = ruleDao.getRuleByParkinglot(parkinglot.getId());
		BigDecimal parkingPrice = new BigDecimal(0);
		if(rule != null) {
			parkingPrice = rule.getPrice();
		}
		//获取停车场车位数据信息
		ParkingLot count = dao.getParkingCount(parkinglot.getId());
		resp.setId(parkinglot.getId());
		resp.setParkinglotName(parkinglot.getParkinglotName());
		resp.setLocation(parkinglot.getLocation());
		resp.setFreeCount(count.getFreeCount());
		resp.setRevatationCount(count.getRevatationCount());
		resp.setParkingPrice(parkingPrice);
		return resp;
	}

	@Override
	public List<ParkingLot> getParkinglotByRange(double range, double x1, double y1) {
		List<ParkingLot> allParkinglots = dao.getAllParkingLot();
		List<ParkingLot> resp = new ArrayList<ParkingLot>();
		
		if(allParkinglots != null && allParkinglots.size() > 0) {
			for (ParkingLot parkinglot : allParkinglots) {
				if(parkinglot.getCoordinateX() == null || parkinglot.getCoordinateY() == null) {
					continue;
				}
				Double x2 = parkinglot.getCoordinateX();//停车场位置x坐标
				Double y2 = parkinglot.getCoordinateY();//停车场位置y坐标
				int distance = MathUtil.getDistance(x1,y1,x2,y2);
				//计算当前位置与停车场的距离
				if(distance < range) {
					ParkingLot dto = new ParkingLot();
					dto.setId(parkinglot.getId());
					dto.setParkinglotName(parkinglot.getParkinglotName());
					dto.setFreeTime(parkinglot.getFreeTime());
					//获取停车场车位数据信息
					ParkingLot count = dao.getParkingCount(parkinglot.getId());
					dto.setTotalCount(count.getTotalCount());
					dto.setFreeCount(count.getFreeCount());
					dto.setRevatationCount(count.getRevatationCount());
					dto.setIsRevatation(parkinglot.getIsRevatation());
					dto.setHaveMap(parkinglot.getHaveMap());
					dto.setHaveLock(parkinglot.getHaveLock());
					dto.setCoordinateX(parkinglot.getCoordinateX());
					dto.setCoordinateY(parkinglot.getCoordinateY());
					dto.setDistance(distance);
					dto.setLocation(parkinglot.getLocation());
					//获取收费规则
					ChargeRule rule = ruleDao.getRuleByParkinglot(parkinglot.getId());
					BigDecimal parkingPrice = new BigDecimal(0);
					if(rule != null) {
						parkingPrice = rule.getPrice();
					}
					dto.setParkingPrice(parkingPrice);
					resp.add(dto);
				}
			}
		}
		if (resp != null && resp.size() > 0) {
			//按距离排序
			for (int i = 0; i < resp.size() - 1; i++) {
				for (int j = i + 1; j < resp.size(); j++) {
					if (resp.get(i).getDistance() > resp.get(j).getDistance()) {
						ParkingLot temp = resp.get(i);
						resp.set(i, resp.get(j));
						resp.set(j, temp);
					}
				}
			}
		}
		return resp;
	}

	@Override
	public ParkingLot getParkingCount(Integer parkinglotId) {
		return dao.getParkingCount(parkinglotId);
	}  
}
