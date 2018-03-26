package com.jcble.jcparking.common.service.devices.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcble.jcparking.common.CommonEnum;
import com.jcble.jcparking.common.dao.admin.AlarmMsgDao;
import com.jcble.jcparking.common.dto.request.AlarmMsgReqDto;
import com.jcble.jcparking.common.dto.response.CountDto;
import com.jcble.jcparking.common.model.devices.AlarmMsg;
import com.jcble.jcparking.common.service.devices.AlarmMsgService;

import baseproj.common.util.DateUtil;

@Service
public class AlarmMsgServiceImpl implements AlarmMsgService {

	@Autowired
	private AlarmMsgDao alarmMsgDao;

	@Override
	public List<AlarmMsg> getAlarmMessages(AlarmMsg dto) {
		dto.setStatus(CommonEnum.AlarmHandleStatus.UnProcessed.code);
		List<AlarmMsg> alarmMessages = alarmMsgDao.queryAlarmMessagesByPage(dto);
		return alarmMessages;

	}

	@Override
	public void handAlarmMessages(AlarmMsgReqDto dto) throws Exception {
		List<AlarmMsg> AlarmMessages = dto.getAlarmMessage();
		for (AlarmMsg alarmMsgDto : AlarmMessages) {
			AlarmMsg alarmMsg = alarmMsgDao.select(alarmMsgDto);
			if (alarmMsg != null) {
				alarmMsg.setModifier(dto.getOperatorId());
				alarmMsg.setModifyTime(DateUtil.getDateTime());
				alarmMsg.setHandler(dto.getOperatorId());
				alarmMsg.setStatus(CommonEnum.AlarmHandleStatus.Processed.code);
				alarmMsgDao.update(alarmMsg);
			}
		}
	}

	@Override
	public CountDto getCountByOperator(AlarmMsg dto) {
		CountDto count = new CountDto();
		int unProcessedCount = alarmMsgDao.getCountByOperator(dto);
		count.setCount(unProcessedCount);
		return count;
	}

}
