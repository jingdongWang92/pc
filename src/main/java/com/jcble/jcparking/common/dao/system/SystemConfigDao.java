package com.jcble.jcparking.common.dao.system;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.system.SystemConfig;

/**
 * 支付日志
 * 
 * @author Jingdong Wang
 * @date 2017年3月21日 上午11:10:06
 *
 */
public interface SystemConfigDao extends BaseSimpleDao {

	SystemConfig getSysCfgParamValueByKey(SystemConfig dto);

}
