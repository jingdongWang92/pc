package com.jcble.jcparking.common.service.devices;

import java.io.IOException;
import java.util.List;

import com.jcble.jcparking.common.dto.request.BindDevicesReqDto;
import com.jcble.jcparking.common.dto.request.DevicesOpReqDto;
import com.jcble.jcparking.common.dto.response.DeviceDto;
import com.jcble.jcparking.common.model.devices.AlarmMsg;
import com.jcble.jcparking.common.model.devices.Geomagnetism;
import com.jcble.jcparking.common.model.devices.ParkingLock;

/**
 * 设备相关业务层接口
 * 
 * @author Jingdong Wang
 * @date 2017年3月2日 上午10:06:17
 *
 */
public interface DevicesService {

	/**
	 * 绑定设备到车位
	 * 
	 * @param devicesDto
	 * @throws Exception
	 */
	public List<DeviceDto> bindDeviceToParking(BindDevicesReqDto devicesDto) throws Exception;

	/**
	 * 获取设备上报数据
	 * 
	 * @throws IOException
	 */
	public void createRabbitLink() throws IOException;

	void pushMsgToAdmin(AlarmMsg alarmDto);

	void alarmMsg(ParkingLock dto);
	void alarmMsg(Geomagnetism dto);

	/**
	 * 降下车位锁
	 * @param serialNumber
	 * @param operatorId 
	 * @throws Exception 
	 */
	public void controlLockDown(DevicesOpReqDto reqDto) throws Exception;
	
	/**
	 * 升起车位锁
	 * @param serialNumber
	 * @param operatorId 
	 * @throws Exception 
	 */
	public void controlLockUp(DevicesOpReqDto reqDto) throws Exception;

}
