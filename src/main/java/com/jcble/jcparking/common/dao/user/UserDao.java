package com.jcble.jcparking.common.dao.user;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.user.User;

/**
 * 用户dao层接口
 * 
 * @author Jingdong Wang
 * 
 */
public interface UserDao extends BaseSimpleDao {

	/**
	 * 根据用户id获取用户信息
	 * @param userId
	 * @return
	 */
	User getUserByUserId(String userId);

	User getUserPhoneNumber(String phoneNumber);

   

}
