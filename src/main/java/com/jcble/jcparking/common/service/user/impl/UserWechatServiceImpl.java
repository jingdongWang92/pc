package com.jcble.jcparking.common.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcble.jcparking.common.dao.user.UserDao;
import com.jcble.jcparking.common.dao.user.UserWechatDao;
import com.jcble.jcparking.common.dto.request.UserWechatReqDto;
import com.jcble.jcparking.common.exception.ParkingServiceException;
import com.jcble.jcparking.common.model.user.User;
import com.jcble.jcparking.common.model.user.UserWechat;
import com.jcble.jcparking.common.service.user.UserWechatService;

import baseproj.common.util.DateUtil;

/**
 * 用户微信
 * @author Jingdong Wang
 * @date 2017年3月13日 下午4:11:37
 *
 */
@Service
public class UserWechatServiceImpl implements UserWechatService {
	
	@Autowired
	private UserWechatDao dao;
	
	@Autowired
	private UserDao userDao;

	@Override
	public void linkWechat(UserWechatReqDto reqDto) throws Exception {
		User user = userDao.getUserByUserId(reqDto.getUserId());
		if(user == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10003);
		}else {
			if(user.getWechat() != null) {
				throw new ParkingServiceException(ParkingServiceException.ERROR_10018);
			}
		}
		//检查微信账号是否已被其他用户绑定
		UserWechat wechat = dao.getWechatByAccount(reqDto.getWechatAcc());
		if(wechat != null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10017);
		}
		UserWechat linkWechat = new UserWechat();
		linkWechat.setWechatAcc(reqDto.getWechatAcc());
		linkWechat.setNickname(reqDto.getNickname());
		linkWechat.setUserId(reqDto.getUserId());
		dao.insert(linkWechat);
	}

	@Override
	public void deleteWechatLink(Integer wechatId) throws Exception {
		UserWechat dto = new UserWechat();
		dto.setId(wechatId);
		dao.delete(dto);
	}

	@Override
	public User wechatAuth(UserWechatReqDto reqDto) throws Exception {
		User resp = new User();
		//检查微信账号是否已被其他用户绑定
		UserWechat wechat = dao.getWechatByAccount(reqDto.getWechatAcc());
		if(wechat == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10003);
		}
		User user = userDao.getUserByUserId(wechat.getUserId());
		user.setRecentLoginTime(DateUtil.getDateTime());
		userDao.update(user);
		
		resp.setPhoneNumber(user.getPhoneNumber());
		resp.setId(user.getId());
		return resp;
	}

}
