package com.jcble.jcparking.common.service.user;

import com.jcble.jcparking.common.dto.request.AdviceReqDto;

/**
 * 用户建议反馈业务接口
 * 
 * @author Jingdong Wang
 * 
 */
public interface AdviceService {

	void saveAdvice(AdviceReqDto reqDto) throws Exception;


}
