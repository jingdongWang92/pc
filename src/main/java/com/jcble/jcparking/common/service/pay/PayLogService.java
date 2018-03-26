package com.jcble.jcparking.common.service.pay;

import com.jcble.jcparking.common.dto.request.PayRecordReqDto;

/**
 * 支付记录业务接口
 * 
 * @author Jingdong Wang
 * 
 */
public interface PayLogService {

	void payFeedback(PayRecordReqDto dto) throws Exception;


}
