package com.jcble.jcparking.common.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcble.jcparking.common.dao.user.AdviceDao;
import com.jcble.jcparking.common.dto.request.AdviceReqDto;
import com.jcble.jcparking.common.model.user.Advice;
import com.jcble.jcparking.common.service.user.AdviceService;

import baseproj.common.util.DateUtil;

/**
 * 用户建议反馈业务信息接口实现
 * @author Jingdong Wang 
 * @date 2017年3月14日 下午5:12:20
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdviceServiceImpl implements AdviceService {
	@Autowired
	private AdviceDao adviceDao;

	@Override
	public void saveAdvice(AdviceReqDto reqDto) throws Exception {
		Advice advice = new Advice();
		advice.setUserId(reqDto.getUserId());
		advice.setType(reqDto.getType());
		advice.setContent(reqDto.getContent());
		advice.setCreateTime(DateUtil.getDateTime());
		adviceDao.insert(advice);
	}

}
