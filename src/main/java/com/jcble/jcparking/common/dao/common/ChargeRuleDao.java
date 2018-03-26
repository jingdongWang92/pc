package com.jcble.jcparking.common.dao.common;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.admin.ChargeRule;

public interface ChargeRuleDao extends BaseSimpleDao {
	
	public ChargeRule getRuleByParkinglot(Integer parkinglotId);


}
