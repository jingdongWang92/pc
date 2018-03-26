package com.jcble.jcparking.common.service.common;

import com.jcble.jcparking.common.dto.response.ChargeRuleRespDto;

public interface ChargeRuleService {

	/**
	 * 获取停车场收费规则
	 * 
	 * @param parkinglotId
	 * @return
	 * @throws Exception 
	 */
	public ChargeRuleRespDto getRuleByParkinglot(Integer parkinglotId) throws Exception;

	
}
