package com.jcble.jcparking.common.dao.admin;

import java.util.List;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.admin.ParkingLot;

/**
 * 停车场dao层接口
 * 
 * @author Jingdong Wang
 * 
 */
public interface ParkingLotDao extends BaseSimpleDao {

	public List<ParkingLot> queryParkingLotsByPage(ParkingLot param);

	public ParkingLot getParkingLotByParkingId(Integer parkingId);

	public ParkingLot getParkinglotById(Integer parkinglotId);

	public List<ParkingLot> getAllParkingLot();

	public ParkingLot getParkingCount(Integer parkinglotId);

}
