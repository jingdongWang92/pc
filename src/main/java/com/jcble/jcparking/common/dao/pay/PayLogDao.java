package com.jcble.jcparking.common.dao.pay;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.pay.PayLog;

/**
 * 支付日志
 * 
 * @author Jingdong Wang
 * @date 2017年3月21日 上午11:10:06
 *
 */
public interface PayLogDao extends BaseSimpleDao {

	/**
	 * 根据订单号、支付状态、支付方式、交易类型判断是否重复
	 * @param payLog
	 * @return
	 */
	PayLog getPayLog(PayLog payLog);

	PayLog getLatestPayRecordByOrderNo(String orderNo);

}
