package com.jcble.jcparking.common.dao.user;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.user.UserWechat;

/**
 * 用户微信dao层接口
 * 
 * @author Jingdong Wang
 * 
 */
public interface UserWechatDao extends BaseSimpleDao {

	UserWechat getWechatByAccount(String wechatAcc);

   

}
