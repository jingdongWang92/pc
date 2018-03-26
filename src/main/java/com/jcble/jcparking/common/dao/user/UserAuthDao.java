package com.jcble.jcparking.common.dao.user;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.user.UserAuth;

/**
 * 用户认证dao层接口
 * 
 * @author Jingdong Wang
 * 
 */
public interface UserAuthDao extends BaseSimpleDao {

	UserAuth getUserByAccount(String account);

	void updateUserAuth(UserAuth param);

}
