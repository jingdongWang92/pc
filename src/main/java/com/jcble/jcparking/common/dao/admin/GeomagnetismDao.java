package com.jcble.jcparking.common.dao.admin;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.devices.Geomagnetism;
import com.jcble.jcparking.common.model.devices.ParkingLock;

/**
 * 地磁dao层接口
 * 
 * @author Jingdong Wang
 * @date 2017年3月2日 上午10:02:49
 *
 */
public interface GeomagnetismDao extends BaseSimpleDao {

	public ParkingLock getDeviceByParkingId(ParkingLock lockDto);

	public Geomagnetism getGeomagnetismBySerialNo(String serialNumber);

}
