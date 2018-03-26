package com.jcble.jcparking.common.dao.admin;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.admin.Role;

public interface RoleDao extends BaseSimpleDao {

	public Role getRoleByAdminId(Integer adminId);

}
