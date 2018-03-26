package com.jcble.jcparking.common.dao.user;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.parking.IndoorMap;

/**
 * 室内地图
 * @author Jingdong Wang 
 * @date 2017年4月11日 上午9:30:38
 *
 */
public interface IndoorMapDao extends BaseSimpleDao {

	IndoorMap getIndoorMapById(Integer indoorMapId);

}
