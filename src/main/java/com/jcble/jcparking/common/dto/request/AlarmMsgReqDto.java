package com.jcble.jcparking.common.dto.request;

import java.util.List;

import com.jcble.jcparking.common.dto.request.BaseReqDto;
import com.jcble.jcparking.common.model.devices.AlarmMsg;

public class AlarmMsgReqDto extends BaseReqDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5820973250104763258L;

	private Integer operatorId;

	private List<AlarmMsg> alarmMessages;

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public List<AlarmMsg> getAlarmMessage() {
		return alarmMessages;
	}

	public void setAlarmMessages(List<AlarmMsg> alarmMessages) {
		this.alarmMessages = alarmMessages;
	}

}
