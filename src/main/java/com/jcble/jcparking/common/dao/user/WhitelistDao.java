package com.jcble.jcparking.common.dao.user;

import java.util.List;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.user.Whitelist;

/**
 * 固定车功能dao层接口
 * 
 * @author Jingdong Wang
 * @date 2017年3月17日 上午11:09:20
 *
 */
public interface WhitelistDao extends BaseSimpleDao {

	List<Whitelist> getWhitelistsByCar(Whitelist dto);

}
