package com.jcble.jcparking.common.dao.pay;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.pay.WxPayCallbackLog;

public interface WxPayCallbackLogDao extends BaseSimpleDao {

	WxPayCallbackLog getWxPayLog(WxPayCallbackLog wxPayCallbackLog);

}
