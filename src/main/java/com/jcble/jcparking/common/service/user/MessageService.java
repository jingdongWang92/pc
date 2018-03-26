package com.jcble.jcparking.common.service.user;

import java.util.List;

import com.jcble.jcparking.common.model.user.Message;

/**
 * 用户消息
 * 
 * @author Jingdong Wang
 * @date 2017年3月21日 上午10:31:32
 *
 */
public interface MessageService {

	/**
	 * 获取用户消息列表
	 * 
	 * @param userId
	 * @return
	 */
	List<Message> getUserMessages(Message dto);

	/**
	 * 获取用户消息详情
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	Message getUserMessageDetail(Integer id) throws Exception;

	/**
	 * 修改用户消息状态
	 * 
	 * @param id
	 * @throws Exception
	 */
	void updateUserMessage(Integer id) throws Exception;

}
