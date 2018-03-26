package com.jcble.jcparking.common.dao.admin;

import java.util.List;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.admin.Privilege;

public interface PrivilegeDao extends BaseSimpleDao {

	List<Privilege> getPrivilegesByRoleId(Integer roleId);

}
