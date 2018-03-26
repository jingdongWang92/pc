package com.jcble.jcparking.common.dao.admin;

import java.util.List;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.admin.ParkingArea;

/**
 * 停车场区域dao层接口
 * 
 * @author Jingdong Wang
 * 
 */
public interface ParkingAreaDao extends BaseSimpleDao {

   public List<ParkingArea> queryParkingAreas(Integer parkingLotId);

}
