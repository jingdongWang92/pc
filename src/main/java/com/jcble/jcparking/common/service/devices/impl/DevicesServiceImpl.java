package com.jcble.jcparking.common.service.devices.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jcble.jcparking.common.CommonEnum;
import com.jcble.jcparking.common.dao.admin.AdminDao;
import com.jcble.jcparking.common.dao.admin.AlarmMsgDao;
import com.jcble.jcparking.common.dao.admin.GeomagnetismDao;
import com.jcble.jcparking.common.dao.admin.ParkingDao;
import com.jcble.jcparking.common.dao.admin.ParkingLockDao;
import com.jcble.jcparking.common.dao.admin.ParkingLotDao;
import com.jcble.jcparking.common.dao.devices.GeoDataLogDao;
import com.jcble.jcparking.common.dao.devices.LockDataLogDao;
import com.jcble.jcparking.common.dao.devices.ParkingLockOpLogDao;
import com.jcble.jcparking.common.dto.request.BindDevicesReqDto;
import com.jcble.jcparking.common.dto.request.DevicesOpReqDto;
import com.jcble.jcparking.common.dto.response.DeviceDto;
import com.jcble.jcparking.common.exception.ParkingServiceException;
import com.jcble.jcparking.common.model.admin.Admin;
import com.jcble.jcparking.common.model.admin.Parking;
import com.jcble.jcparking.common.model.admin.ParkingLot;
import com.jcble.jcparking.common.model.devices.AlarmMsg;
import com.jcble.jcparking.common.model.devices.GeoDataLog;
import com.jcble.jcparking.common.model.devices.Geomagnetism;
import com.jcble.jcparking.common.model.devices.LockDataLog;
import com.jcble.jcparking.common.model.devices.ParkingLock;
import com.jcble.jcparking.common.model.devices.ParkingLockOpLog;
import com.jcble.jcparking.common.model.system.RabbitMq;
import com.jcble.jcparking.common.service.admin.ParkingOrderService;
import com.jcble.jcparking.common.service.devices.DevicesService;
import com.jcble.jcparking.common.utils.ClientJPushSender;
import com.jcble.jcparking.common.utils.GetSystemConfig;
import com.jcble.jcparking.common.utils.JcDeviceServerUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import baseproj.common.util.DateUtil;

@Service
@Transactional(rollbackFor = { Exception.class })
public class DevicesServiceImpl implements DevicesService {

	@Autowired
	private ParkingLockDao lockDao;

	@Autowired
	private GeomagnetismDao geoDao;

	@Autowired
	private ParkingDao parkingDao;

	@Autowired
	private ParkingLotDao parkinglotDao;

	@Autowired
	private AlarmMsgDao alarmDao;

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private ParkingLockOpLogDao logDao;
	@Autowired
	private GeoDataLogDao geoDataLogDao;
	@Autowired
	private LockDataLogDao lockDataLogDao;

	@Autowired
	private ParkingOrderService orderService;

	@Override
	public List<DeviceDto> bindDeviceToParking(BindDevicesReqDto devicesDto) throws Exception {
		List<DeviceDto> deviceResp = new ArrayList<DeviceDto>();
		List<DeviceDto> devices = devicesDto.getDevices();

		for (DeviceDto deviceDto : devices) {
			// 获取车位信息
			Parking dto = new Parking();
			dto.setId(deviceDto.getParkingId());
			Parking parking = parkingDao.select(dto);
			if (parking == null) {
				throw new ParkingServiceException(ParkingServiceException.ERROR_10005);
			}
			if (deviceDto.getSerialNumber().startsWith(CommonEnum.DeviceType.Lock.getCode())) {
				// 如果车位已经绑定设备
				if (StringUtils.isNotBlank(parking.getLockSerialNumber())) {
					//重复绑定则跳过
					if(parking.getLockSerialNumber().equals(deviceDto.getSerialNumber())) {
						continue;
					}
					deviceDto.setErrMsg("车位已经绑定同种设备");
					deviceResp.add(deviceDto);
					continue;
				}
				// 检查设备是否已经被绑定
				ParkingLock device = lockDao.getParkingLockBySerialNo(deviceDto.getSerialNumber());
				if(device != null) {
					deviceDto.setErrMsg("该设备已被绑定");
					deviceResp.add(deviceDto);
					continue;
				}
				
				ParkingLock parkingLock = new ParkingLock();
				parkingLock.setCreator(devicesDto.getOperatorId());
				parkingLock.setCreateTime(DateUtil.getDateTime());
				parkingLock.setSerialNumber(deviceDto.getSerialNumber());
				parkingLock.setParkingId(deviceDto.getParkingId());
				lockDao.insert(parkingLock);
				// 修改车位为可预约状态
				if (!CommonEnum.YesNoFlg.Yes.code.equals(parking.getIsRevatation())) {
					parking.setIsRevatation(CommonEnum.YesNoFlg.Yes.code);
					parking.setLockSerialNumber(deviceDto.getSerialNumber());
					parkingDao.update(parking);
				}
				// 更新车位信息
				parking.setLockSerialNumber(deviceDto.getSerialNumber());
				parkingDao.update(parking);

			} else if (deviceDto.getSerialNumber().startsWith(CommonEnum.DeviceType.Geomagnetism.getCode())) {
				// 如果车位已经绑定设备
				if (StringUtils.isNotBlank(parking.getGeoSerialNumber())) {
					//重复绑定则跳过
					if (parking.getGeoSerialNumber().equals(deviceDto.getSerialNumber())) {
						continue;
					}
					deviceDto.setErrMsg("车位已经绑定同种设备");
					deviceResp.add(deviceDto);
					continue;
				}
				// 检查设备是否已经被绑定
				Geomagnetism device = geoDao.getGeomagnetismBySerialNo(deviceDto.getSerialNumber());
				if(device != null) {
					deviceDto.setErrMsg("该设备已被绑定");
					deviceResp.add(deviceDto);
					continue;
				}
				
				Geomagnetism geomagnetism = new Geomagnetism();
				geomagnetism.setCreator(devicesDto.getOperatorId());
				geomagnetism.setCreateTime(DateUtil.getDateTime());
				geomagnetism.setSerialNumber(deviceDto.getSerialNumber());
				geomagnetism.setParkingId(deviceDto.getParkingId());
				geoDao.insert(geomagnetism);
				// 更新车位信息
				parking.setGeoSerialNumber(deviceDto.getSerialNumber());
				parkingDao.update(parking);
			}
		}
		return deviceResp;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public void createRabbitLink() throws IOException {
		try {

			RabbitMq rabbitMq = GetSystemConfig.getRabbitParam();
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(rabbitMq.getHost());
			factory.setPort(rabbitMq.getPort());
			factory.setUsername(rabbitMq.getUsername());
			factory.setPassword(rabbitMq.getPassword());
			factory.setVirtualHost(rabbitMq.getVirtualHost());
			// 关键所在，指定线程池  
	        ExecutorService service = Executors.newFixedThreadPool(10);  
	        factory.setSharedExecutor(service);
	        // 设置自动恢复  
	        factory.setAutomaticRecoveryEnabled(true);  
	        factory.setNetworkRecoveryInterval(2);// 设置 没10s ，重试一次  
	        factory.setTopologyRecoveryEnabled(false);// 设置不重新声明交换器，队列等信息。  
			Connection connection = factory.newConnection();
			
			Channel channel = connection.createChannel();
			channel.exchangeDeclare(rabbitMq.getExchangeName(), "topic");
			// 声明队列
			String queueName = channel.queueDeclare().getQueue();
			channel.queueBind(queueName, rabbitMq.getExchangeName(), "#");
			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public synchronized void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body, "UTF-8");
					String currentTime = DateUtil.getDateTime();
					try {
						JSONObject json = JSON.parseObject(message);
						String payload = json.getString("payload");
						String serialNumber = json.getString("device_id");
						// 根据设备类型做相应的处理
						if (serialNumber.startsWith(CommonEnum.DeviceType.Geomagnetism.code)) {
							byte[] data = Base64.getDecoder().decode(payload);
							Geomagnetism dto = geoDao.getGeomagnetismBySerialNo(serialNumber);
							if (dto != null) {
								// 记录地磁数据上报日志
								saveGeoDataLog(serialNumber, data, currentTime);
								String lastStatus = dto.getStatus();
								String currentStatus = data[4] + "";
								System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>地磁"+serialNumber+"当前状态"+currentStatus);
								// 空闲=>占用
								if (CommonEnum.GeoStatus.Free.code.equals(lastStatus)
										&& CommonEnum.GeoStatus.Use.code.equals(currentStatus)) {
									orderService.carIn(dto.getParkingId());
									pushToAdminCarIn(dto.getParkingId(), "车位有车来了");
								} else if (CommonEnum.GeoStatus.Use.code.equals(lastStatus)
										&& CommonEnum.GeoStatus.Free.code.equals(currentStatus)) {
									// 占用=>空闲
									orderService.carOut(dto.getParkingId());
									// 通知管理员车走了
									pushToAdminCarIn(dto.getParkingId(), "车位的车辆刚刚离开了");
								}
								dto.setPower((data[3] + ""));// 地磁电量
								dto.setStatus(currentStatus);// 地磁状态
								dto.setSmiStatus(data[5] + "");// 强磁干扰状态
								dto.setCarStatus(data[6] + "");// 车辆状态
								dto.setReceiveDataTime(currentTime);// 数据上报时间
								geoDao.update(dto);
								alarmMsg(dto);
							}
						} else if (serialNumber.startsWith(CommonEnum.DeviceType.Lock.code)) {
							byte[] data = Base64.getDecoder().decode(payload);
							ParkingLock lock = lockDao.getParkingLockBySerialNo(serialNumber);
							if (lock != null) {
								System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>车位锁：'"+serialNumber+">>>>>>>>"+data[0] + ":" + data[2] + ":" + data[3] + ":" + data[4] + ":" + data[5]);
								// 记录车位锁数据上报日志
								saveLockDataLog(serialNumber, data, currentTime);
								lock.setStatus(data[3] + "");// 车位锁状态
								lock.setPower(data[4] + "");// 车位锁电量
								lock.setReceiveDataTime(currentTime);
								lockDao.update(lock);
								alarmMsg(lock);
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			channel.basicConsume(queueName, true, consumer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alarmMsg(Geomagnetism dto) {
		try {
			AlarmMsg alarmDto = new AlarmMsg();
			alarmDto.setSerialNumber(dto.getSerialNumber());
			alarmDto.setParkingId(dto.getParkingId());
			alarmDto.setDeviceType(CommonEnum.DeviceType.Geomagnetism.code);
			if (Integer.parseInt(dto.getPower()) < 20) {
				alarmDto.setSnagType(CommonEnum.GeoAlarmType.LowPower.code);
				pushMsgToAdmin(alarmDto);
			}
			if (CommonEnum.SMIStatus.SMI.code.equals(dto.getSmiStatus())) {
				alarmDto.setSnagType(CommonEnum.GeoAlarmType.SMI.code);
				pushMsgToAdmin(alarmDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alarmMsg(ParkingLock dto) {
		try {
			AlarmMsg alarmDto = new AlarmMsg();
			alarmDto.setSerialNumber(dto.getSerialNumber());
			alarmDto.setParkingId(dto.getParkingId());
			alarmDto.setDeviceType(CommonEnum.DeviceType.Lock.code);
			if (Integer.parseInt(dto.getPower()) < 20) {
				alarmDto.setSnagType(CommonEnum.LockAlarmType.LowPower.code);
				pushMsgToAdmin(alarmDto);
			}
			if (CommonEnum.ParkingLockStatus.Impact.code.equals(dto.getStatus())) {
				alarmDto.setSnagType(CommonEnum.LockAlarmType.Impact.code);
				pushMsgToAdmin(alarmDto);
			} else if (CommonEnum.ParkingLockStatus.Block.code.equals(dto.getStatus())) {
				alarmDto.setSnagType(CommonEnum.LockAlarmType.Block.code);
				pushMsgToAdmin(alarmDto);
			} else if (CommonEnum.ParkingLockStatus.Fault.code.equals(dto.getStatus())) {
				alarmDto.setSnagType(CommonEnum.LockAlarmType.Fault.code);
				pushMsgToAdmin(alarmDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void pushMsgToAdmin(AlarmMsg alarmDto) {
		try {
			String currentTime = DateUtil.getDateTime();
			// 获取车位信息
			Parking parkingDto = new Parking();
			parkingDto.setId(alarmDto.getParkingId());
			Parking parking = parkingDao.select(parkingDto);
			// 获取停车场
			ParkingLot parkinglotDto = new ParkingLot();
			parkinglotDto.setId(parking.getParkinglotId());
			ParkingLot parkinglot = parkinglotDao.select(parkinglotDto);
			// 判断该设备此类故障是否有未处理的记录
			alarmDto.setStatus(CommonEnum.AlarmHandleStatus.UnProcessed.code);
			AlarmMsg alarm = alarmDao.getAlarmMsg(alarmDto);
			if (alarm == null) {
				alarmDto.setCreateTime(currentTime);
				alarmDto.setParkinglotId(parkinglot.getId());
				alarmDto.setAlarmTime(currentTime);
				StringBuilder content = new StringBuilder();
				content.append(parkinglot.getParkinglotName());
				content.append(parking.getParkingNo());
				if (CommonEnum.DeviceType.Lock.code.equals(alarmDto.getDeviceType())) {
					if (CommonEnum.LockAlarmType.LowPower.code.equals(alarmDto.getSnagType())) {
						content.append("车位的车位锁电量过低,请及时更换电池");
					} else if (CommonEnum.LockAlarmType.Impact.code.equals(alarmDto.getSnagType())) {
						content.append("车位的车位锁受到外力撞击,请查看");
					} else if (CommonEnum.LockAlarmType.Block.code.equals(alarmDto.getSnagType())) {
						content.append("车位的车位锁升起受阻,请查看");
					} else if (CommonEnum.LockAlarmType.Fault.code.equals(alarmDto.getSnagType())) {
						content.append("车位的车位锁硬件发生故障,请查看");
					} else {
						content.append("车位的车位锁发送未知错误");
					}
				} else {
					if (CommonEnum.GeoAlarmType.LowPower.code.equals(alarmDto.getSnagType())) {
						content.append("车位的地磁电量过低,请及时更换电池");
					} else if (CommonEnum.GeoAlarmType.SMI.code.equals(alarmDto.getSnagType())) {
						content.append("车位的地磁遭到强磁干扰,请查看");
					} else {
						content.append("车位的车位锁发送未知错误");
					}
				}
				alarmDto.setContent(content.toString());
				alarmDao.insert(alarmDto);
				// 根据车位获取管理员
				List<Admin> admins = adminDao.findAdminByParkingId(alarmDto.getParkingId());
				if (admins != null && admins.size() > 0) {
					for (Admin adminDto : admins) {
						if (StringUtils.isNotBlank(adminDto.getJpushId())) {
							ClientJPushSender.sendExtrasByRegId(adminDto.getJpushId(), content.toString(), "type", "2",
									true);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void pushToAdminCarIn(Integer parkingId, String content) {
		try {
			Parking dto = new Parking();
			dto.setId(parkingId);
			Parking parking = parkingDao.select(dto);
			// 根据车位获取管理员
			List<Admin> admins = adminDao.findAdminByParkingId(parkingId);
			if (admins != null && admins.size() > 0) {
				for (Admin adminDto : admins) {
					if (StringUtils.isNotBlank(adminDto.getJpushId())) {
						ClientJPushSender.sendExtrasByRegId(adminDto.getJpushId(), parking.getParkingNo() + content,
								"type", "1", true);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void controlLockDown(DevicesOpReqDto reqDto) throws Exception {
		String msg = "操作成功！";
		Admin adminDto = new Admin();
		adminDto.setId(reqDto.getOperatorId());
		Admin admin = adminDao.select(adminDto);
		try {
			String command = "8wMCAADQ";// 降下车位锁指令
			String result = JcDeviceServerUtil.lockControl(reqDto.getSerialNumber(), command);
			if (null == result || "".equals(result)) {
				msg = "操作失败！接口返回空！";
			}
			JSONObject json = JSONObject.parseObject(result);
			if (json.getBooleanValue("error")) {
				msg = " 操作失败！接口返回消息：" + json.getString("message");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = " 操作失败！";
		} finally {
			ParkingLockOpLog log = new ParkingLockOpLog();
			log.setCreateTime(DateUtil.getDateTime());
			log.setOpWay(CommonEnum.LockOpWay.JURA.code);
			log.setOpType(CommonEnum.LockOpType.Down.code);
			log.setOperatorId(reqDto.getOperatorId());
			log.setSerialNumber(reqDto.getSerialNumber());
			log.setRemark(admin.getName() + "通过" + CommonEnum.LockOpWay.JURA.name + "方式降下车位锁" + msg);
			logDao.insert(log);
		}
	}

	@Override
	public void controlLockUp(DevicesOpReqDto reqDto) throws Exception {
		String msg = "操作成功！";
		Admin adminDto = new Admin();
		adminDto.setId(reqDto.getOperatorId());
		Admin admin = adminDao.select(adminDto);
		try {
			String command = "8wMBAAA0";
			String result = JcDeviceServerUtil.lockControl(reqDto.getSerialNumber(), command);
			if (null == result || "".equals(result)) {
				msg = "操作失败！接口返回空！";
			}
			JSONObject json = JSONObject.parseObject(result);
			if (json.getBooleanValue("error")) {
				msg = " 操作失败！接口返回消息：" + json.getString("message");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = " 操作失败！";
		} finally {
			ParkingLockOpLog log = new ParkingLockOpLog();
			log.setCreateTime(DateUtil.getDateTime());
			log.setOpWay(CommonEnum.LockOpWay.JURA.code);
			log.setOpType(CommonEnum.LockOpType.Up.code);
			log.setOperatorId(reqDto.getOperatorId());
			log.setSerialNumber(reqDto.getSerialNumber());
			log.setRemark(admin.getName() + "通过" + CommonEnum.LockOpWay.JURA.name + "方式升起车位锁" + msg);
			logDao.insert(log);
		}
	}

	// 记录地磁数据上报日志
	private void saveGeoDataLog(String serialNumber, byte[] data, String receiveDataTime) {
		GeoDataLog datalog = new GeoDataLog();
		datalog.setSerialNumber(serialNumber);
		datalog.setPower((data[3] + ""));// 地磁电量
		datalog.setStatus(data[4] + "");// 地磁状态
		datalog.setSmiStatus(data[5] + "");// 强磁干扰状态
		datalog.setCarStatus(data[6] + "");// 车辆状态
		datalog.setReceiveDataTime(receiveDataTime);
		try {
			geoDataLogDao.insert(datalog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 记录车位锁数据上报日志
	private void saveLockDataLog(String serialNumber, byte[] data, String receiveDataTime) {
		LockDataLog datalog = new LockDataLog();
		datalog.setSerialNumber(serialNumber);
		datalog.setStatus(data[3] + "");// 车位锁状态
		datalog.setPower((data[4] + ""));// 车位锁电量
		datalog.setReceiveDataTime(receiveDataTime);
		try {
			lockDataLogDao.insert(datalog);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
