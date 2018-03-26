package com.jcble.jcparking.common.service.user.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcble.jcparking.common.service.user.RechargeRecordService;

/**
 * 充值记录
 * 
 * @author Jingdong Wang
 * @date 2017年5月4日 下午2:29:33
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RechargeRecordServiceImpl implements RechargeRecordService {

}
