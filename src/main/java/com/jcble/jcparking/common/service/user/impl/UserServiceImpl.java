package com.jcble.jcparking.common.service.user.impl;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcble.jcparking.common.CommonEnum;
import com.jcble.jcparking.common.dao.user.UserAuthDao;
import com.jcble.jcparking.common.dao.user.UserDao;
import com.jcble.jcparking.common.dto.request.PayRecordReqDto;
import com.jcble.jcparking.common.dto.request.ReservationReqDto;
import com.jcble.jcparking.common.dto.request.UserReqDto;
import com.jcble.jcparking.common.dto.response.RechargeRespDto;
import com.jcble.jcparking.common.exception.ParkingServiceException;
import com.jcble.jcparking.common.model.user.User;
import com.jcble.jcparking.common.model.user.UserAuth;
import com.jcble.jcparking.common.service.pay.AliPayService;
import com.jcble.jcparking.common.service.pay.WechatPayService;
import com.jcble.jcparking.common.service.user.UserService;
import com.jcble.jcparking.common.utils.Generator;
import com.jcble.jcparking.common.utils.MathUtil;
import com.jcble.jcparking.common.utils.Validator;

import baseproj.common.util.DateUtil;
import weixin.popular.bean.paymch.UnifiedorderResult;

/**
 * 用户业务信息接口实现
 * 
 * @author Jingdong Wang
 * @date 2017年3月13日 下午3:53:32
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private UserAuthDao userAuthDao;
	@Autowired
	private AliPayService aliPayService;
	@Autowired
	private WechatPayService wechatPayService;

	@Override
	public void register(UserReqDto reqDto) throws Exception {
		String phoneNumber = reqDto.getAccount();
		User user = userDao.getUserPhoneNumber(phoneNumber);
		if (user != null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10015);
		}

		User userDto = new User();
		String userId = UUID.randomUUID().toString().replaceAll("-", "");
		userDto.setId(userId);
		userDto.setPhoneNumber(reqDto.getAccount());
		userDto.setRegisterTime(DateUtil.getDateTime());
		userDao.insert(userDto);

		UserAuth auth = new UserAuth();
		auth.setAccount(reqDto.getAccount());
		auth.setPassword(reqDto.getPassword());
		auth.setUserId(userId);
		userAuthDao.insert(auth);
	}

	@Override
	public User login(UserReqDto reqDto) throws Exception {
		User resp = new User();
		UserAuth userAuth = userAuthDao.getUserByAccount(reqDto.getAccount());
		String password = reqDto.getPassword();
		if (userAuth == null || !password.equalsIgnoreCase(userAuth.getPassword())) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10004);
		}
		User user = userDao.getUserByUserId(userAuth.getUserId());
		user.setRecentLoginTime(DateUtil.getDateTime());
		userDao.update(user);

		resp.setPhoneNumber(user.getPhoneNumber());
		resp.setId(user.getId());
		return resp;

	}

	@Override
	public User getUserById(String userId) {
		User resp = new User();
		User user = userDao.getUserByUserId(userId);
		if (user != null) {
			resp.setId(user.getId());
			resp.setBalance(user.getBalance());
			resp.setPhoneNumber(user.getPhoneNumber());
			resp.setWechat(user.getWechat());
		}
		return resp;
	}

	@Override
	public void updatePassword(UserReqDto reqDto) throws Exception {
		UserAuth userAuth = userAuthDao.getUserByAccount(reqDto.getAccount());
		if (userAuth == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10003);
		}
		String password = reqDto.getOldPassword();
		if (!password.equalsIgnoreCase(userAuth.getPassword())) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10016);
		}
		userAuth.setPassword(reqDto.getPassword());
		userAuthDao.update(userAuth);
	}

	@Override
	public void findPassword(UserReqDto reqDto) throws Exception {
		// String account = super.decryptAccount(adminDto.getAccount());
		UserAuth userAuth = userAuthDao.getUserByAccount(reqDto.getAccount());
		if (userAuth == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10003);
		}
		userAuth.setPassword(reqDto.getPassword());
		userAuthDao.update(userAuth);

	}

	@Override
	public void balancePay(PayRecordReqDto dto) throws Exception {
		User user = userDao.getUserByUserId(dto.getUserId());
		BigDecimal balance = user.getBalance();
		BigDecimal payAmount = dto.getAmount();
		if (balance.doubleValue() < payAmount.doubleValue()) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10025);
		}
		user.setBalance(MathUtil.sub(balance, payAmount));
		userDao.update(user);
	}

	@Override
	public void updatePhoneNumber(UserReqDto reqDto) throws Exception {
		User user = userDao.getUserByUserId(reqDto.getUserId());
		if(!Validator.isMobile(reqDto.getPhoneNumber())) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10008);
		}
		if(user == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10003);
		}
		
		if(user.getPhoneNumber().equals(reqDto.getPhoneNumber())) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10026);
		}
		
		User existUser = userDao.getUserPhoneNumber(reqDto.getPhoneNumber());
		if(existUser != null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10015);
		}
		
		user.setPhoneNumber(reqDto.getPhoneNumber());
		userDao.update(user);
		
		//修改手机登录账号
		UserAuth param = new UserAuth();
		param.setUserId(user.getId());
		param.setAccount(reqDto.getPhoneNumber());
		userAuthDao.updateUserAuth(param);
	}

	@Override
	public RechargeRespDto balanceRecharge(ReservationReqDto reqDto) throws Exception {
		RechargeRespDto resp = new RechargeRespDto();
		String orderNo = Generator.generateOrderNo();
		if(CommonEnum.PayWay.Alipay.code.equals(reqDto.getPayWay())) {
			try {
				String alipayInfo = aliPayService.getAlipayOrderInfo(orderNo,reqDto.getPayFee().toString(),CommonEnum.FeeType.BalanceRecharge.code);
				resp.setAlipayInfo(alipayInfo);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ParkingServiceException(ParkingServiceException.ERROR_10024);
			}
		}else if(CommonEnum.PayWay.WxPay.code.equals(reqDto.getPayWay())) {
			UnifiedorderResult weixinPayInfo = wechatPayService.getWxPayOrderInfo(reqDto.getIpAddr(),orderNo,reqDto.getPayFee().toString(),CommonEnum.FeeType.BalanceRecharge.code);
			if("FAIL".equals(weixinPayInfo.getResult_code())) {
				throw new ParkingServiceException(ParkingServiceException.ERROR_10024);
			}
			resp.setWeixinPayInfo(weixinPayInfo);
		}
		return resp;
	}
}
