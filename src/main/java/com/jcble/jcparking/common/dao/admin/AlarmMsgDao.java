package com.jcble.jcparking.common.dao.admin;

import java.util.List;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.devices.AlarmMsg;

public interface AlarmMsgDao extends BaseSimpleDao {

	public List<AlarmMsg> queryAlarmMessagesByPage(AlarmMsg dto);

	public int getCountByOperator(AlarmMsg dto);

	/**
	 * 根据串号和异常类型获取告警信息
	 * 
	 * @param alarm
	 *            serialNumber:设备串号 sangType:异常类型
	 * @return
	 */
	public AlarmMsg getAlarmMsg(AlarmMsg alarm);

}
