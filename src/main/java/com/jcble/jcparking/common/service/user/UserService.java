package com.jcble.jcparking.common.service.user;

import com.jcble.jcparking.common.dto.request.PayRecordReqDto;
import com.jcble.jcparking.common.dto.request.ReservationReqDto;
import com.jcble.jcparking.common.dto.request.UserReqDto;
import com.jcble.jcparking.common.dto.response.RechargeRespDto;
import com.jcble.jcparking.common.model.user.User;

/**
 * 用户业务接口
 * 
 * @author Jingdong Wang
 * 
 */
public interface UserService {

	/**
	 * 用户注册
	 * 
	 * @param userRegisterReq
	 * @throws Exception
	 */
	public void register(UserReqDto reqDto) throws Exception;

	/**
	 * 用户登录
	 * 
	 * @param reqDto
	 * @return
	 * @throws Exception
	 */
	public User login(UserReqDto reqDto) throws Exception;

	/**
	 * 获取用户详情
	 * 
	 * @param userId
	 * @return
	 */
	public User getUserById(String userId);

	/**
	 * 用户修改密码
	 * 
	 * @param reqDto
	 * @throws Exception
	 */
	public void updatePassword(UserReqDto reqDto) throws Exception;

	/**
	 * 用户找回密码
	 * 
	 * @param reqDto
	 * @throws Exception
	 */
	public void findPassword(UserReqDto reqDto) throws Exception;

	/**
	 * 余额支付
	 * 
	 * @param dto
	 * @throws Exception 
	 */
	public void balancePay(PayRecordReqDto dto) throws Exception;

	/**
	 * 更换手机号码
	 * @param reqDto
	 * @throws Exception 
	 */
	public void updatePhoneNumber(UserReqDto reqDto) throws Exception;

	/**
	 * 余额充值
	 * 
	 * @param reqDto
	 * @return 
	 * @throws Exception 
	 */
	RechargeRespDto balanceRecharge(ReservationReqDto reqDto) throws Exception;

}
