package com.jcble.jcparking.common.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcble.jcparking.common.CommonEnum;
import com.jcble.jcparking.common.dao.admin.AdminDao;
import com.jcble.jcparking.common.dao.admin.PrivilegeDao;
import com.jcble.jcparking.common.dao.admin.RoleDao;
import com.jcble.jcparking.common.dto.request.AdminReqDto;
import com.jcble.jcparking.common.exception.ParkingServiceException;
import com.jcble.jcparking.common.model.admin.Admin;
import com.jcble.jcparking.common.model.admin.Privilege;
import com.jcble.jcparking.common.model.admin.Role;
import com.jcble.jcparking.common.service.AbstractBaseService;
import com.jcble.jcparking.common.service.admin.AdminService;

import baseproj.common.util.DateUtil;

@Service
public class AdminServiceImpl extends AbstractBaseService implements AdminService {

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private PrivilegeDao privilegeDao;

	@Autowired
	private RoleDao roleDao;

	@Override
	public Admin login(AdminReqDto adminLoginDto) throws Exception {

		// String account = super.decryptAccount(adminLoginDto.getAccount());
		Admin admin = adminDao.findAdminByAccount(adminLoginDto.getAccount());
		if (admin == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10003);
		}
		String password = adminLoginDto.getPassword();
		if (!password.equalsIgnoreCase(admin.getPassword())) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10004);
		}

		// 获取管理员的角色权限信息
		Role role = roleDao.getRoleByAdminId(admin.getId());
		if (role != null) {
			// 获取角色权限信息
			List<Privilege> privileges = privilegeDao.getPrivilegesByRoleId(role.getId());
			if (privileges == null || privileges.size() < 1) {
				throw new ParkingServiceException(ParkingServiceException.ERROR_10002);
			}
			StringBuffer url = new StringBuffer();
			for (Privilege privilegeDto : privileges) {
				url.append(privilegeDto.getPageUrl());
			}
			if (url.toString().contains("parking")) {
				admin.setType(CommonEnum.AdminType.Device.code);
			}
			if (url.toString().contains("order")) {
				admin.setType(CommonEnum.AdminType.Charge.code);
			}
			if (url.toString().contains("order") && url.toString().contains("parking")) {
				admin.setType(CommonEnum.AdminType.Both.code);
			}
		}
		Admin upAdminDto = adminDao.select(admin);
		// 记录最近登录时间
		upAdminDto.setRecentLoginTime(DateUtil.getDateTime());
		adminDao.update(upAdminDto);
		admin.setPassword(null);
		return admin;
	}

	@Override
	public void resetPassword(AdminReqDto adminDto) throws Exception {
		// String account = super.decryptAccount(adminDto.getAccount());
		Admin admin = adminDao.findAdminByAccount(adminDto.getAccount());
		if (admin == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10003);
		}
		admin.setModifyTime(DateUtil.getDateTime());
		admin.setPassword(adminDto.getPassword());
		adminDao.update(admin);
	}

	@Override
	public void setRegistrationID(AdminReqDto adminDto) throws Exception {
		Admin admin = adminDao.findAdminByAccount(adminDto.getAccount());
		if (admin == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10003);
		}
		admin.setJpushId(adminDto.getJpushId());
		adminDao.update(admin);
	}
}
