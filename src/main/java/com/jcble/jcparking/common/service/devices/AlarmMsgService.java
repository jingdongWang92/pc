package com.jcble.jcparking.common.service.devices;

import java.util.List;

import com.jcble.jcparking.common.dto.request.AlarmMsgReqDto;
import com.jcble.jcparking.common.dto.response.CountDto;
import com.jcble.jcparking.common.model.devices.AlarmMsg;

/**
 * 告警消息相关
 * @author Jingdong Wang
 *
 */
public interface AlarmMsgService {

	/**
	 * 获取告警消息
	 * @param dto
	 * @return
	 */
	public List<AlarmMsg> getAlarmMessages(AlarmMsg dto);

	/**
	 * 处理告警消息
	 * @param dto
	 * @throws Exception 
	 */
	public void handAlarmMessages(AlarmMsgReqDto dto) throws Exception;

	/**
	 * 根据管理员获取未处理的告警消息总的记录条数
	 * @param dto
	 * @return
	 */
	public CountDto getCountByOperator(AlarmMsg dto);

	
	
}
