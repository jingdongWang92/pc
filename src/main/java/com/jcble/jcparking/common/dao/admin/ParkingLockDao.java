package com.jcble.jcparking.common.dao.admin;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.devices.ParkingLock;

/**
 * 车位锁dao层接口
 * 
 * @author Jingdong Wang
 * 
 */
public interface ParkingLockDao extends BaseSimpleDao {

	public ParkingLock getDeviceByParkingId(ParkingLock lockDto);

	public ParkingLock getParkingLockBySerialNo(String serialNumber);

}
