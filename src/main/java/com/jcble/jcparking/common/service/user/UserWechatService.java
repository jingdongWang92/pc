package com.jcble.jcparking.common.service.user;

import com.jcble.jcparking.common.dto.request.UserWechatReqDto;
import com.jcble.jcparking.common.model.user.User;

/**
 * 用户微信业务接口
 * 
 * @author Jingdong Wang
 * 
 */
public interface UserWechatService {

	/**
	 * 关联微信账号
	 * 
	 * @param reqDto
	 * @throws Exception 
	 */
	void linkWechat(UserWechatReqDto reqDto) throws Exception;

	/**
	 * 删除微信关联
	 * 
	 * @param wechatId
	 * @throws Exception 
	 */
	void deleteWechatLink(Integer wechatId) throws Exception;

	/**
	 * 微信登录
	 * @param reqDto
	 * @return 
	 * @throws Exception 
	 */
	User wechatAuth(UserWechatReqDto reqDto) throws Exception;

}
