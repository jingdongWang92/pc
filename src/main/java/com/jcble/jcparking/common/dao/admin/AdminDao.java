package com.jcble.jcparking.common.dao.admin;

import java.util.List;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.admin.Admin;

public interface AdminDao extends BaseSimpleDao {

	Admin findAdminByAccount(String account);

	List<Admin> findAdminByParkingId(Integer parkingId);

}
