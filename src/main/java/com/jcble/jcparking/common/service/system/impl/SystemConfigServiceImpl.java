package com.jcble.jcparking.common.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcble.jcparking.common.dao.system.SystemConfigDao;
import com.jcble.jcparking.common.model.system.SystemConfig;
import com.jcble.jcparking.common.service.system.SystemConfigService;

/**
 * 系统参数配置
 * 
 * @author Jingdong Wang
 * @date 2017年3月21日 下午2:46:47
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SystemConfigServiceImpl implements SystemConfigService {

	@Autowired
	private SystemConfigDao dao;

	@Override
	public String getSysCfgParamValueByKey(String paramKey) throws Exception {
		SystemConfig dto = new SystemConfig();
		dto.setParamKey(paramKey);
		SystemConfig configParam = dao.getSysCfgParamValueByKey(dto);
		return configParam.getParamValue();
	}

}
