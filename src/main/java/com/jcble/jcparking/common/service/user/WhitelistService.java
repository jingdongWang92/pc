package com.jcble.jcparking.common.service.user;

import java.util.List;

import com.jcble.jcparking.common.model.user.Whitelist;

/**
 * 固定车功能业务层接口
 * 
 * @author Jingdong Wang
 * @date 2017年3月17日 上午11:45:44
 *
 */
public interface WhitelistService {

	/**
	 * 获取固定车辆信息
	 * 
	 * @param dto
	 * @return 
	 */
	List<Whitelist> getWhitelistsByCar(Whitelist dto);

}
