package com.jcble.jcparking.common.dao.pay;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.pay.AlipayCallbackLog;

public interface AliPayCallbackLogDao extends BaseSimpleDao {

	AlipayCallbackLog getLogByNotifyId(String notify_id);

}
