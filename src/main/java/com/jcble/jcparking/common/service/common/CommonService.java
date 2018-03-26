package com.jcble.jcparking.common.service.common;

import java.util.List;

import com.jcble.jcparking.common.dto.request.VerifyCodeDto;
import com.jcble.jcparking.common.dto.response.DropDownDto;

public interface CommonService {

	/**
	 * 获取验证码
	 * @param verifyCodeDto
	 * @return
	 * @throws Exception
	 */
	public VerifyCodeDto getVerifyCode(VerifyCodeDto verifyCodeDto) throws Exception;
	
	public List<DropDownDto> getDropdownByName(String dropdownName);

}
