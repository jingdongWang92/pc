package com.jcble.jcparking.common.dto.request;

import java.util.List;

import com.jcble.jcparking.common.dto.response.DeviceDto;

public class BindDevicesReqDto extends BaseReqDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 740665517224682698L;

	private Integer operatorId;

	private List<DeviceDto> devices;

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public List<DeviceDto> getDevices() {
		return devices;
	}

	public void setDevices(List<DeviceDto> devices) {
		this.devices = devices;
	}

}
