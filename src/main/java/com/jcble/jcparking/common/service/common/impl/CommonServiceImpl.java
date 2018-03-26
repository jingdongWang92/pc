package com.jcble.jcparking.common.service.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jcble.jcparking.common.CommonEnum;
import com.jcble.jcparking.common.dto.request.VerifyCodeDto;
import com.jcble.jcparking.common.dto.response.DropDownDto;
import com.jcble.jcparking.common.exception.ParkingServiceException;
import com.jcble.jcparking.common.service.AbstractBaseService;
import com.jcble.jcparking.common.service.common.CommonService;
import com.jcble.jcparking.common.utils.Validator;

@Service
public class CommonServiceImpl extends AbstractBaseService implements CommonService {

	@Override
	public VerifyCodeDto getVerifyCode(VerifyCodeDto verifyCodeDto) throws Exception {
		VerifyCodeDto verifyCode = new VerifyCodeDto();
		//验证手机号格式
		if(!Validator.isMobile(verifyCodeDto.getMobile())) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10008);
		}
		// 下发验证码
//        String vcode = SMSUtil.sendVcodeSMSToMobile(verifyCodeDto.getMobile());
		verifyCode.setVerifyCode("1234");
		return verifyCode;
	}

	@Override
	public List<DropDownDto> getDropdownByName(String dropdownName) {
		List<DropDownDto> list = new ArrayList<DropDownDto>();
		Map<String,String> map = new HashMap<String,String>();
		switch (dropdownName) {
		case "geonstatus":
			map = CommonEnum.GeoStatus.codeMap;
			break;
		case "lockalarmtype":
			map = CommonEnum.LockAlarmType.codeMap;
			break;	
		case "geoalarmtype":
			map = CommonEnum.GeoAlarmType.codeMap;
			break;	
		case "devicetype":
			map = CommonEnum.DeviceType.codeMap;
			break;	
		case "lockstatus":
			map = CommonEnum.LockStatus.codeMap;
			break;	
		case "devbindstatus":
			map = CommonEnum.DevBindStatus.codeMap;
			break;	
		default:
			break;
		}
		if(map != null) {
			for (Map.Entry<String, String> entry : map.entrySet()) {  
				DropDownDto dropdown = new DropDownDto();
			    dropdown.setCode(entry.getKey());
			    dropdown.setDesc(entry.getValue());
			    list.add(dropdown);
			}  
		}
		return list;
	}


}
