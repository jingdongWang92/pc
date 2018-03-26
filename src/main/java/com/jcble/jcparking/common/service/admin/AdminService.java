package com.jcble.jcparking.common.service.admin;

import com.jcble.jcparking.common.dto.request.AdminReqDto;
import com.jcble.jcparking.common.model.admin.Admin;

/**
 * 管理员业务层接口
 * 
 * @author Jingdong Wang
 * @date 2017年2月15日 下午2:40:15
 *
 */
public interface AdminService {

	/**
	 * 管理员登录
	 * 
	 * @param reqDto
	 * @return
	 * @throws Exception
	 */
	public Admin login(AdminReqDto reqDto) throws Exception;

	/**
	 * 重置密码
	 * 
	 * @param reqDto
	 * @param request
	 * @throws Exception
	 */
	public void resetPassword(AdminReqDto reqDto) throws Exception;

	/**
	 * 设置极光推送id
	 * 
	 * @param reqDto
	 * @throws Exception
	 */
	public void setRegistrationID(AdminReqDto reqDto) throws Exception;

}
