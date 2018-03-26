package com.jcble.jcparking.common.dao.user;

import java.util.List;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.user.Message;

/**
 * 用户消息
 * @author Jingdong Wang 
 * @date 2017年3月21日 上午11:10:06
 *
 */
public interface MessageDao extends BaseSimpleDao {

	List<Message> queryMessagesByPage(Message dto);

}
