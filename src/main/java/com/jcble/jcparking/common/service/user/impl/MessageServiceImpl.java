package com.jcble.jcparking.common.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcble.jcparking.common.CommonEnum;
import com.jcble.jcparking.common.dao.jpush.JPushMessageDao;
import com.jcble.jcparking.common.dao.user.MessageDao;
import com.jcble.jcparking.common.model.jpush.JPushMessage;
import com.jcble.jcparking.common.model.user.Message;
import com.jcble.jcparking.common.service.user.MessageService;

/**
 * 用户消息
 * 
 * @author Jingdong Wang
 * @date 2017年3月21日 上午11:08:51
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private JPushMessageDao jPushMessageDao;

	@Override
	public List<Message> getUserMessages(Message dto) {
		List<Message> list = messageDao.queryMessagesByPage(dto);
		return list;
	}

	@Override
	public void updateUserMessage(Integer id) throws Exception {
		Message dto = new Message();
		dto.setId(id);
		Message message = messageDao.select(dto);
		if (message != null) {
			message.setReadStatus(CommonEnum.YesNoFlg.Yes.code);
			messageDao.update(message);
			JPushMessage jpushMsgdto = new JPushMessage();
			jpushMsgdto.setId(message.getPushMessageId());
			JPushMessage jpushMessage = jPushMessageDao.select(jpushMsgdto);
			jpushMessage.setReadCount(jpushMessage.getReadCount() + 1);
			jPushMessageDao.update(jpushMessage);
		}
	}

	@Override
	public Message getUserMessageDetail(Integer id) throws Exception {
		Message dto = new Message();
		Message resp = new Message();
		dto.setId(id);
		Message message = messageDao.select(dto);
		JPushMessage jpushMsgdto = new JPushMessage();
		jpushMsgdto.setId(message.getPushMessageId());
		JPushMessage jpushMessage = jPushMessageDao.select(jpushMsgdto);
		resp.setId(message.getId());
		resp.setTitle(jpushMessage.getTitle());
		resp.setContent(jpushMessage.getContent());
		resp.setCreateTime(jpushMessage.getCreateTime());
		return resp;
	}
}
