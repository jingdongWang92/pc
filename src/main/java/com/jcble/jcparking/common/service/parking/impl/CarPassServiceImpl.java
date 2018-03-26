package com.jcble.jcparking.common.service.parking.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcble.jcparking.common.CommonEnum;
import com.jcble.jcparking.common.dao.admin.ParkingDao;
import com.jcble.jcparking.common.dao.admin.ParkingLotCarDao;
import com.jcble.jcparking.common.dao.admin.ParkingLotDao;
import com.jcble.jcparking.common.dao.admin.ParkingOrderDao;
import com.jcble.jcparking.common.dao.common.ChargeRuleDao;
import com.jcble.jcparking.common.dao.user.ReservationDao;
import com.jcble.jcparking.common.dao.user.UserCarDao;
import com.jcble.jcparking.common.dto.request.ParkingReqDto;
import com.jcble.jcparking.common.dto.response.CarPassRespDto;
import com.jcble.jcparking.common.model.admin.ChargeRule;
import com.jcble.jcparking.common.model.admin.Parking;
import com.jcble.jcparking.common.model.admin.ParkingLotCar;
import com.jcble.jcparking.common.model.admin.ParkingLot;
import com.jcble.jcparking.common.model.admin.ParkingOrder;
import com.jcble.jcparking.common.model.user.Reservation;
import com.jcble.jcparking.common.model.user.UserCar;
import com.jcble.jcparking.common.service.admin.ParkingOrderService;
import com.jcble.jcparking.common.service.admin.ParkingService;
import com.jcble.jcparking.common.service.parking.CarPassService;
import com.jcble.jcparking.common.utils.Generator;
import com.jcble.jcparking.common.utils.MathUtil;

import baseproj.common.util.DateUtil;

@Service
public class CarPassServiceImpl implements CarPassService {
	protected static final Logger logger = LoggerFactory.getLogger(CarPassServiceImpl.class);
	@Autowired
	private ReservationDao reservationDao;
	WaitPay task;
	@Autowired
	private ParkingOrderDao orderDao;
	@Autowired
	private UserCarDao carDao;
	@Autowired
	private ParkingDao parkingDao;
	@Autowired
	private ParkingLotDao parkinglotDao;
	@Autowired
	private ParkingLotCarDao parkingLotCarDao;
	@Autowired
	private ChargeRuleDao chargeRuleDao;
	@Autowired
	private ParkingOrderService orderService;
	@Autowired
	private ParkingService parkingService;

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void carPass(ParkingReqDto reqDto) {
		try {
			String currentTime = DateUtil.getDateTime();
			if (CommonEnum.CarInOrOut.In.code.equals(reqDto.getInOrOut())) {
				// 创建场内车辆
				ParkingLotCar parkinglotCar = new ParkingLotCar();
				parkinglotCar.setParkinglotId(reqDto.getParkinglotId());
				parkinglotCar.setInTime(DateUtil.getDateTime());
				parkinglotCar.setCarNumber(reqDto.getCarNumber());
				
				// 创建停车订单
				ParkingOrder order = new ParkingOrder();
				order.setCreateTime(currentTime);
				order.setCarNumber(reqDto.getCarNumber());
				order.setOrderStatus(CommonEnum.OrderStatus.Ing.code);
				order.setIsFree(CommonEnum.YesNoFlg.No.code);
				order.setParkinglotId(reqDto.getParkinglotId());
				order.setInTime(currentTime);
				
				// 根据车辆获取预约信息
				Reservation rDto = new Reservation();
				rDto.setStatus(CommonEnum.RevatationStatus.Success.code);
				rDto.setCarNumber(reqDto.getCarNumber());
				Reservation reserveInfo = reservationDao.getReservationByCar(rDto);
				if (reserveInfo != null) {
					order.setTotalFee(reserveInfo.getPayedFee());
					order.setPayedFee(reserveInfo.getPayedFee());
					order.setPayStatus(CommonEnum.PayStatus.Payed.code);
					order.setPayWay(reserveInfo.getPayWay());
					order.setPayTime(reserveInfo.getPayTime());
					order.setOrderNo(reserveInfo.getOrderNo());
					order.setUserId(reserveInfo.getUserId());
					order.setParkingId(reserveInfo.getParkingId());

					//更新预约订单状态为使用中
					reserveInfo.setStatus(CommonEnum.RevatationStatus.Using.code);
					reservationDao.update(reserveInfo);
					
					parkinglotCar.setOrderNo(reserveInfo.getOrderNo());
					parkinglotCar.setParkingId(reserveInfo.getParkingId());

					//修改用户车辆状态为入场
					UserCar carDto = new UserCar();
					carDto.setUserId(reserveInfo.getUserId());
					carDto.setCarNumber(reserveInfo.getCarNumber());
					UserCar car = carDao.getCarByUserIdAndCarNumber(carDto);
					car.setStatus(CommonEnum.CarStatus.In.code);
					carDao.update(car);
				} else {
					Parking freeParking = parkingService.getTempParking(reqDto.getParkinglotId());
					String orderNo = Generator.generateOrderNo();
					parkinglotCar.setOrderNo(orderNo);
					parkinglotCar.setParkingId(freeParking.getId());
					
					// 设置车位为占用
					freeParking.setStatus(CommonEnum.ParkingStatus.Use.code);
					parkingDao.update(freeParking);

					// 根据车牌号获取车信息,若存在则修改车辆状态
					List<UserCar> cars = carDao.getCarByCarNumber(reqDto.getCarNumber());
					if (cars != null && cars.size() > 0) {
						for (UserCar userCarDto : cars) {
							userCarDto.setStatus(CommonEnum.CarStatus.In.code);
							carDao.update(userCarDto);
						}
					}
					
					order.setPayStatus(CommonEnum.PayStatus.UnPay.code);
					order.setOrderNo(orderNo);
					order.setParkingId(freeParking.getId());
					order.setParkinglotId(reqDto.getParkinglotId());
					if (cars != null && cars.size() == 1) {
						order.setUserId(cars.get(0).getUserId());
					}
				}
				orderDao.insert(order);
				parkingLotCarDao.insert(parkinglotCar);
			} else {
				// 离场
				try {
					ParkingOrder orderDto = new ParkingOrder();
					orderDto.setCarNumber(reqDto.getCarNumber());
					orderDto.setOrderStatus(CommonEnum.OrderStatus.Ing.code);
					ParkingOrder order = orderDao.getOrderByCar(orderDto);
					BigDecimal prepaid = new BigDecimal(0);
					if (order != null) {
						Reservation reserveInfo = reservationDao.getReservationByOrderNo(order.getOrderNo());
						if (reserveInfo != null) {
							reserveInfo.setStatus(CommonEnum.RevatationStatus.End.code);
							reservationDao.update(reserveInfo);
							prepaid = reserveInfo.getPayedFee();
						}
						// 若订单为免费订单则不计算费用
						if (!CommonEnum.IsFreeOrder.Yes.code.equals(order.getIsFree())) {
							ParkingLot parkinglot = parkinglotDao.getParkingLotByParkingId(order.getParkingId());
							// 根据停车场获取收费规则
							ChargeRule chargeRule = chargeRuleDao.getRuleByParkinglot(parkinglot.getId());
							if (chargeRule == null || chargeRule.getPrice() == null) {
								order.setOutTime(currentTime);// 设置离场时间
								orderDao.update(order);
								return;
							}
							BigDecimal totalFee = orderService.parkingFeeCompute(order.getInTime(),
									DateUtil.getDateTime(), chargeRule, parkinglot);
							BigDecimal payedFee = order.getPayedFee() == null ? new BigDecimal(0) : order.getPayedFee();
							// 支付状态不为空
							if (StringUtils.isNotBlank(order.getPayStatus())) {
								totalFee = MathUtil.add(totalFee, prepaid);// 总费用 = 停车总费用 + 预约费用
								order.setTotalFee(totalFee);// 更新订单总费用
								// 超时
								if (orderService.isOverTime(order) && payedFee.doubleValue() < totalFee.doubleValue()) {
									order.setPayStatus(CommonEnum.PayStatus.UnPay.code);
									order.setOrderStatus(CommonEnum.OrderStatus.OwnFee.code);// 设置订单为欠费状态
								} else {
									order.setOrderInvoiceStatus(CommonEnum.OrderInvoiceStatus.UnApply.code);
									order.setOrderStatus(CommonEnum.OrderStatus.End.code);
								}
							} else {// 支付状态为空
								order.setTotalFee(totalFee);
								if (totalFee.doubleValue() > 0) {
									order.setOrderStatus(CommonEnum.OrderStatus.OwnFee.code);// 设置订单为欠费状态
								} else {
									order.setOrderStatus(CommonEnum.OrderStatus.End.code);// 设置订单为欠费状态
								}
							}
						} else {
							// 免费订单直接结束
							order.setTotalFee(new BigDecimal(0));
							order.setPayedFee(new BigDecimal(0));
							order.setPayStatus(CommonEnum.PayStatus.Payed.code);
							order.setPayWay(CommonEnum.PayWay.Cash.code);
							order.setPayTime(currentTime);
							order.setOrderStatus(CommonEnum.OrderStatus.End.code);
							order.setOrderInvoiceStatus(CommonEnum.OrderInvoiceStatus.NotQualified.code);
						}
						order.setOutTime(currentTime);// 设置离场时间
						order.setModifyTime(currentTime);
						orderDao.update(order);

						// 根据车牌号获取车信息,若存在则修改车辆状态
						List<UserCar> cars = carDao.getCarByCarNumber(reqDto.getCarNumber());
						if (cars != null && cars.size() > 0) {
							for (UserCar userCarDto : cars) {
								userCarDto.setStatus(CommonEnum.CarStatus.Out.code);
								carDao.update(userCarDto);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 修改场内车辆信息
				ParkingLotCar car = parkingLotCarDao.getParkingLotCarByCarNumber(reqDto.getCarNumber());
				// 设置场内车辆离场时间
				car.setOutTime(currentTime);
				parkingLotCarDao.update(car);

				// 释放车位
				Parking parking = new Parking();
				parking.setId(car.getParkingId());
				parking.setStatus(CommonEnum.ParkingStatus.Free.code);
				parkingDao.update(parking);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public CarPassRespDto carIn(ParkingReqDto reqDto) {
		Parking parking = parkingService.getTempParking(reqDto.getParkinglotId());
		CarPassRespDto resp = new CarPassRespDto();
		if (parking == null) {
			resp.setCode("1");
			resp.setCommand("1");
		} else {
			resp.setCode("0");
			resp.setCommand("0");
		}
		return resp;
	}

	@Override
	public CarPassRespDto carOut(ParkingReqDto reqDto) {
		CarPassRespDto resp = new CarPassRespDto();
		// 根据车牌号获取订单信息
		ParkingOrder dto = new ParkingOrder();
		dto.setCarNumber(reqDto.getCarNumber());
		dto.setOrderStatus(CommonEnum.OrderStatus.Ing.code);
		ParkingOrder order = orderDao.getOrderByCar(dto);
		if (order == null) {
			resp.setCode("0");
			resp.setCommand("0");
		} else {
			if (CommonEnum.PayStatus.Payed.code.equals(order.getPayStatus())) {
				resp.setCode("0");
				resp.setCommand("0");
			} else {
				// 开启线程 等待用户支付
				try {
					String payStatus = out(order.getOrderNo());
					if (CommonEnum.PayStatus.Payed.code.equals(payStatus)) {
						resp.setCode("0");
						resp.setCommand("0");
					} else {
						resp.setCode("1");
						resp.setCommand("1");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return resp;
	}

	@SuppressWarnings("deprecation")
	private String out(String orderNo) throws Exception {
		logger.info("等待用户支付.......");
		Integer time = 10 * 60 * 1000;
		String payStatus = "0";
		logger.info("time======" + time);
		try {
			if (task != null) {
				task.stop();
				task = null;
			}
			synchronized (CarPassServiceImpl.class) {
				try {
					task = new WaitPay(payStatus, orderNo);
					task.start();
					task.join(time);
					task.stopTask();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (task != null) {
				payStatus = task.getPayStatus();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return payStatus;
	}

	class WaitPay extends Thread {
		private volatile boolean flag = true;
		private String payStatus;
		private String orderNo;

		public WaitPay(String payStatus, String orderNo) {
			this.payStatus = payStatus;
			this.orderNo = orderNo;
		}

		public void stopTask() {
			flag = false;
		}

		public String getPayStatus() {
			return payStatus;
		}

		public void setPayStatus(String payStatus) {
			this.payStatus = payStatus;
		}

		public String getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}

		@Override
		public void run() {
			int i = 0;
			while (flag) {
				try {
					ParkingOrder order = orderDao.getOrderByOrderNo(orderNo);
					if (CommonEnum.PayStatus.Payed.code.equals(order.getPayStatus())) {// 已经支付
						payStatus = CommonEnum.PayStatus.Payed.code;
						logger.info("用户已支付...." + i);
						break;
					}
					i = i + 3;
					logger.info("等待中用户支付...." + i);
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
