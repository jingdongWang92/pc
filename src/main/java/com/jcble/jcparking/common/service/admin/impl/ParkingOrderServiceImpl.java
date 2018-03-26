package com.jcble.jcparking.common.service.admin.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jcble.jcparking.common.CommonEnum;
import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.dao.admin.ParkingDao;
import com.jcble.jcparking.common.dao.admin.ParkingLotCarDao;
import com.jcble.jcparking.common.dao.admin.ParkingLotDao;
import com.jcble.jcparking.common.dao.admin.ParkingOrderDao;
import com.jcble.jcparking.common.dao.common.ChargeRuleDao;
import com.jcble.jcparking.common.dao.user.IndoorMapDao;
import com.jcble.jcparking.common.dao.user.ReservationDao;
import com.jcble.jcparking.common.dao.user.UserCarDao;
import com.jcble.jcparking.common.dto.request.OrderReqDto;
import com.jcble.jcparking.common.dto.response.ChargeStatisticsRespDto;
import com.jcble.jcparking.common.exception.ParkingServiceException;
import com.jcble.jcparking.common.model.BaseDto;
import com.jcble.jcparking.common.model.admin.ChargeRule;
import com.jcble.jcparking.common.model.admin.Parking;
import com.jcble.jcparking.common.model.admin.ParkingLot;
import com.jcble.jcparking.common.model.admin.ParkingLotCar;
import com.jcble.jcparking.common.model.admin.ParkingOrder;
import com.jcble.jcparking.common.model.devices.ParkingLock;
import com.jcble.jcparking.common.model.parking.IndoorMap;
import com.jcble.jcparking.common.model.user.Reservation;
import com.jcble.jcparking.common.model.user.UserCar;
import com.jcble.jcparking.common.service.BaseServiceImpl;
import com.jcble.jcparking.common.service.admin.ParkingOrderService;
import com.jcble.jcparking.common.utils.ClientJPushSender;
import com.jcble.jcparking.common.utils.DateUtils;
import com.jcble.jcparking.common.utils.Generator;
import com.jcble.jcparking.common.utils.MathUtil;

import baseproj.common.util.DateUtil;

@Service
@Transactional(rollbackFor = { Exception.class })
public class ParkingOrderServiceImpl  extends BaseServiceImpl implements ParkingOrderService{

	@Autowired
	private ParkingOrderDao dao;
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
	private ReservationDao reservationDao;
	@Autowired
	private IndoorMapDao indoorMapDao;
	
	@Override
	protected BaseSimpleDao getDao() {
		return dao;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends BaseDto> List<T> queryByPage(T dto) throws Exception {
		List<ParkingOrder> list = (List<ParkingOrder>) dao.queryByPage(dto);
		if(list != null && list.size() > 0) {
			for (ParkingOrder order : list) {
				//计算停车时长
				String startTime = order.getInTime();
				String endTime = order.getOutTime();
				String duration = DateUtils.dateCommpute(startTime,endTime);
				if(StringUtils.isNotBlank(order.getPayWay()) && !CommonEnum.PayWay.Cash.code.equals(order.getPayWay())) {
					order.setPayWay(CommonEnum.PayWay.Online.code);
				}
				order.setDuration(duration);
			}
		}
		return (List<T>) list;
	}

	@Override
	public void createParkingOrder(OrderReqDto orderReqDto) throws Exception {
		String currentTime = DateUtil.getDateTime();
		String orderNo = Generator.generateOrderNo();
		
		//防止同一车牌号重复创建订单
		ParkingOrder queryParam = new ParkingOrder();
		queryParam.setCarNumber(orderReqDto.getCarNumber());
		queryParam.setOrderStatus(CommonEnum.OrderStatus.Ing.code);
		ParkingOrder existOrder = dao.getOrderByCar(queryParam);
		if(existOrder != null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10028);
		}

		// 获取车位信息,并判断车位状态
		Parking parkingDto = new Parking();
		parkingDto.setId(orderReqDto.getParkingId());
		Parking parking = parkingDao.select(parkingDto);
		if (parking == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10005);
		}
		// 判断车辆信息是否已经录入
		ParkingLotCar car = parkingLotCarDao.getParkingLotCarByParkingId(orderReqDto.getParkingId());
		// 录入场内车辆信息
		if (car == null) {
			car = new ParkingLotCar();
			car.setCarNumber(orderReqDto.getCarNumber());
			car.setCarType(orderReqDto.getCarType());
			car.setInTime(currentTime);
			car.setOrderNo(orderNo);
			car.setParkingId(orderReqDto.getParkingId());
			parkingLotCarDao.insert(car);
		} else {
			car.setCarNumber(orderReqDto.getCarNumber());
			car.setCarType(orderReqDto.getCarType());
			car.setOrderNo(orderNo);
			parkingLotCarDao.update(car);
		}
		
		//根据车牌号获取车信息,若存在则修改车辆状态
		List<UserCar> cars = carDao.getCarByCarNumber(orderReqDto.getCarNumber());
		if(cars != null && cars.size() > 0) {
			for (UserCar userCar : cars) {
				userCar.setStatus(CommonEnum.CarStatus.In.code);
				carDao.update(userCar);
			}
		}
		
		// 创建停车订单
		ParkingOrder order = new ParkingOrder();
		order.setOrderNo(orderNo);
		order.setParkingId(orderReqDto.getParkingId());
		order.setCarNumber(orderReqDto.getCarNumber());
		order.setCarType(orderReqDto.getCarType());
		order.setCreateTime(currentTime);
		order.setInTime(car.getInTime());
		order.setOrderStatus(CommonEnum.OrderStatus.Ing.code);
		//如果不是车辆 则设置为免费订单
		if (orderReqDto.getIsCar()) {
			order.setIsFree(CommonEnum.IsFreeOrder.No.code);
		} else {
			order.setIsFree(CommonEnum.IsFreeOrder.Yes.code);
			order.setFileUrl(orderReqDto.getFileName());
		} 
		order.setOperatorId(orderReqDto.getOperatorId());
		if(cars != null && cars.size() == 1) {
			order.setUserId(cars.get(0).getUserId());
		}
		dao.insert(order);

		// 将车位设置为占用
		parking.setStatus(CommonEnum.ParkingStatus.Use.code);
		parkingDao.update(parking);
		
		//向绑定该车牌的用户推送消息
		ClientJPushSender.sendAllByTagWithExtras(orderReqDto.getCarNumber(),"",CommonEnum.ClientType.User.code,"order","change");
	}

	@Override
	public ParkingOrder getOrderById(Integer orderId) throws Exception {
		ParkingOrder resp = new ParkingOrder();
		ParkingOrder order = dao.getOrderById(orderId);
		if(order != null) {
			String startTime = order.getInTime();
			String endTime = order.getOutTime();
			if(StringUtils.isBlank(endTime)) {
				endTime = DateUtil.getDateTime();
			}
			String duration = DateUtils.dateCommpute(startTime,endTime);
			resp.setOrderNo(order.getOrderNo());
			resp.setCarNumber(order.getCarNumber());
			resp.setInTime(order.getInTime());
			resp.setOutTime(endTime);
			resp.setDuration(duration);
			resp.setTotalFee(order.getTotalFee());
			BigDecimal totalFee = order.getTotalFee() == null ? new BigDecimal(0) :order.getTotalFee();
			BigDecimal payedFee = order.getPayedFee() == null ? new BigDecimal(0) :order.getPayedFee();
			resp.setPayedFee(payedFee);
			resp.setUnPayFee(MathUtil.sub(totalFee, payedFee, 2));
			resp.setParkinglotId(order.getParkinglotId());
			resp.setParkinglotName(order.getParkinglotName());
		}
		return resp;
	}

	@Override
	public void chargeConfirm(OrderReqDto dto) throws Exception {
		//更新订单信息
		ParkingOrder orderDto = new ParkingOrder();
		orderDto.setId(dto.getOrderId());
		ParkingOrder order = dao.select(orderDto);
		if(order == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10009);
		}
		BigDecimal totalFee = order.getTotalFee() == null ? new BigDecimal(0) : order.getTotalFee();
		BigDecimal payedFee = order.getTotalFee() == null ? new BigDecimal(0) : order.getPayedFee();
		//费用小于0 无需支付
		if(totalFee.doubleValue() <= 0 || MathUtil.sub(totalFee, payedFee).doubleValue() <= 0) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10029);
		}
		order.setModifier(dto.getOperatorId());
		order.setModifyTime(DateUtil.getDateTime());
		order.setPayStatus(CommonEnum.PayStatus.Payed.code);
		order.setPayedFee(order.getTotalFee());
		order.setPayWay(CommonEnum.PayWay.Cash.code);
		order.setPayTime(DateUtil.getDateTime());
		order.setOperatorId(dto.getOperatorId());
		dao.update(order);

		//根据车牌号获取欠费订单
		ParkingOrder ownFeeOrderDto = new ParkingOrder();
		ownFeeOrderDto.setCarNumber(order.getCarNumber());
		ownFeeOrderDto.setOrderStatus(CommonEnum.OrderStatus.OwnFee.code);
		List<ParkingOrder> ownFeeOrders = dao.getOwnFeeOrders(ownFeeOrderDto);
		if(ownFeeOrders != null && ownFeeOrders.size() > 0) {
			for (ParkingOrder ownFeeOrder : ownFeeOrders) {
				ownFeeOrder.setModifier(dto.getOperatorId());
				ownFeeOrder.setModifyTime(DateUtil.getDateTime());
				ownFeeOrder.setPayStatus(CommonEnum.PayStatus.Payed.code);
				ownFeeOrder.setOrderStatus(CommonEnum.OrderStatus.End.code);
				ownFeeOrder.setPayedFee(ownFeeOrder.getTotalFee());
				ownFeeOrder.setModifyTime(DateUtil.getDateTime());
				dao.update(ownFeeOrder);
			}
		}
		//推送消息给用户,刷新订单信息
		ClientJPushSender.sendAllByTagWithExtras(order.getCarNumber(),"",CommonEnum.ClientType.User.code,"order","change");
	}

	@Override
	public ParkingOrder orderSettlement(Integer orderId) throws Exception {
		ParkingOrder resp = new ParkingOrder();
		ParkingOrder order = dao.getOrderById(orderId);
		if(order == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10009);
		}
		String currentTime = DateUtil.getDateTime();
		String startTime = order.getInTime();
		//计算停车时长
		String duration = DateUtils.dateCommpute(startTime,currentTime);
		//根据停车订单号计算停车总费用
		BigDecimal totalFee = parkingOrderSettlement(order);
		order.setTotalFee(totalFee);
		order.setModifyTime(currentTime);
		//更新订单总金额
		dao.update(order);
		
		//根据车牌号获取欠费订单
		BigDecimal ownFee = new BigDecimal(0);
		ParkingOrder ownFeeOrderDto = new ParkingOrder();
		ownFeeOrderDto.setCarNumber(order.getCarNumber());
		ownFeeOrderDto.setOrderStatus(CommonEnum.OrderStatus.OwnFee.code);
		List<ParkingOrder> ownFeeOrders = dao.getOwnFeeOrders(ownFeeOrderDto);
		if(ownFeeOrders != null && ownFeeOrders.size() > 0) {
			for (ParkingOrder parkingOrderDto : ownFeeOrders) {
				//计算欠费订单所欠的费用
				BigDecimal ownOrderFee = parkingOrderDto.getTotalFee();
				BigDecimal ownOrderPayedFee = parkingOrderDto.getPayedFee();
				if(ownOrderFee == null){
					continue;
				}else {
					if(ownOrderPayedFee == null) {
						ownFee = ownOrderFee.add(ownFee);
					}else {
						ownFee = ownOrderFee.subtract(ownOrderPayedFee).add(ownFee);
					}
				}
			}
			ownFee = ownFee.setScale(2, RoundingMode.HALF_UP);
		}
		// 未缴纳的金额 = 总金额 - 已支付的金额 + 欠费金额
		BigDecimal payedFee = order.getPayedFee();
		if(payedFee != null) {
			totalFee = MathUtil.sub(totalFee, payedFee);
		}
		resp.setCarNumber(order.getCarNumber());
		resp.setInTime(order.getInTime());
		resp.setOutTime(currentTime);
		resp.setParkinglotName(order.getParkinglotName());
		resp.setParkinglotId(order.getParkinglotId());
		resp.setUnPayFee(ownFee);
		resp.setDuration(duration);
		resp.setTotalFee(MathUtil.add(totalFee,ownFee));
		return resp;
	}
	
	@Override
	public BigDecimal parkingOrderSettlement(ParkingOrder order) throws Exception {
		BigDecimal totalFee = new BigDecimal(0);
		//获取入场时间
		String inTime = order.getInTime();
		//获取停车场信息
		ParkingLot parkinglot = parkinglotDao.getParkingLotByParkingId(order.getParkingId());
		//根据停车场获取收费规则
		ChargeRule chargeRule = chargeRuleDao.getRuleByParkinglot(parkinglot.getId());
		if(chargeRule == null) {
			return new BigDecimal(0);
		}
		//若没有设置封顶金额则按小时计费,若设置了封顶金额则需按照有封顶金额的计算方法
		if(chargeRule.getUpLimitFee24() == null) {
			totalFee = parkingFeeCompute(inTime, DateUtil.getDateTime(), chargeRule, parkinglot);
		} else {
			totalFee = parkingFeeCompute(inTime, DateUtil.getDateTime(), chargeRule, parkinglot);
		}
		return totalFee;
	}
	
	/**
	 * 计算停车总费用 
	 * @param inTimeStr 入场时间
	 * @param currentTimeStr 当前时间
	 * @param chargeRule 收费规则
	 * @param parlingLot 停车场
	 * @return
	 * @throws Exception
	 */
	@Override
	public synchronized BigDecimal parkingFeeCompute(String inTimeStr, String outTimeStr, ChargeRule chargeRule,
			ParkingLot parlingLot) throws Exception {
		if (chargeRule == null) {
			// 若没有收费规则 则免费
			return new BigDecimal(0);
		}
		BigDecimal totalFee = new BigDecimal(0);
		Date inTme = DateUtils.getDatetimeFromString(inTimeStr);
		Date outTime = DateUtils.getDatetimeFromString(outTimeStr);
		Long minutes = (outTime.getTime() - inTme.getTime()) / (1000 * 60);
		int day = (int) (minutes/(24*60));
		double price = chargeRule.getPrice().doubleValue();
		double halfHourPrice = price / 2;//半小时单价
		// 获取停车场信息
		if (parlingLot != null && parlingLot.getFreeTime() != null) {
			if (minutes < parlingLot.getFreeTime()) {
				minutes = (long) 0;
			}
		}
		if(chargeRule.getUpLimitFee24() == null) {
			//按半小时计费
			double hoursFree = MathUtil.div(new BigDecimal(minutes), new BigDecimal(30)).doubleValue();
			int halfHours = (int) hoursFree;
			if (halfHours != hoursFree) {
				halfHours++;
			}
			totalFee = MathUtil.mul(new BigDecimal(halfHours),new BigDecimal(halfHourPrice));
		} else {
			BigDecimal daysFee = new BigDecimal(0);
			BigDecimal limitFee = chargeRule.getUpLimitFee24();
			if(day >= 1) {
				daysFee = MathUtil.mul(new BigDecimal(day), chargeRule.getUpLimitFee24());
				minutes = (long) (minutes - day *24*60);
			}
			double halfHoursDou = MathUtil.div(new BigDecimal(minutes), new BigDecimal(30)).doubleValue();
			int halfHours = (int) halfHoursDou;
			if (halfHours != halfHoursDou) {
				halfHours++;
			}
			BigDecimal hoursFee = MathUtil.mul(new BigDecimal(halfHours), new BigDecimal(halfHourPrice));
			if(hoursFee.intValue() > limitFee.intValue()) {
				hoursFee = limitFee;
			}
			totalFee = MathUtil.add(daysFee, hoursFee);
		}
		return totalFee;
	}

	@Override
	public void setOrderFree(OrderReqDto reqDto) throws Exception {
		String currentTime = DateUtil.getDateTime();
		// 获取订单
		ParkingOrder dto = new ParkingOrder();
		dto.setId(reqDto.getOrderId());
		ParkingOrder order = dao.select(dto);
		if (order == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10009);
		}
		order.setIsFree(CommonEnum.IsFreeOrder.Yes.code);
		order.setReason(reqDto.getReason());
		order.setModifier(reqDto.getOperatorId());
		order.setModifyTime(currentTime);
		order.setOperatorId(reqDto.getOperatorId());
		dao.update(order);
		
		//推送消息给用户,刷新订单信息
		ClientJPushSender.sendAllByTagWithExtras(order.getCarNumber(),"",CommonEnum.ClientType.User.code,"order","change");
	}

	@Override
	public ChargeStatisticsRespDto orderStatistics(ParkingOrder dto) {
		ChargeStatisticsRespDto resp = new ChargeStatisticsRespDto();
		//获取管理员的所有订单
		List<ParkingOrder> orders = dao.getOrdersByOperator(dto);
		BigDecimal receive = new BigDecimal(0);
		BigDecimal total = new BigDecimal(0);
		BigDecimal onlinePay = new BigDecimal(0);
		BigDecimal cashPay = new BigDecimal(0);
		if(orders != null && orders.size() > 0) {
			for (ParkingOrder order : orders) {
				BigDecimal totalFee = order.getTotalFee() == null ? new BigDecimal(0) : order.getTotalFee();
				BigDecimal payedFee = order.getPayedFee() == null ? new BigDecimal(0) : order.getPayedFee();
				total = MathUtil.add(total,totalFee);
				if(order.getPayStatus() != null) {
					if(CommonEnum.PayWay.Cash.code.equals(order.getPayWay())) {
						cashPay = MathUtil.add(cashPay, payedFee);
					} else {
						onlinePay = MathUtil.add(onlinePay, payedFee);
					}
					receive = MathUtil.add(receive, payedFee);
				}
			}
		}
		resp.setReceive(receive);
		resp.setTotal(total);
		resp.setOnlinePay(onlinePay);
		resp.setCashPay(cashPay);
		return resp;
	}
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void carOut(Integer parkingId) throws Exception {
		String currentTime = DateUtil.getDateTime();
		// 根据车位id获取场内车辆信息
		ParkingLotCar car = parkingLotCarDao.getParkingLotCarByParkingId(parkingId);
		if (car != null) {
			try {
				// 更新订单信息
				if (StringUtils.isNotBlank(car.getOrderNo())) {
					ParkingOrder order = dao.getOrderByOrderNo(car.getOrderNo());
					if (order != null) {
						//若订单为免费订单则不计算费用
						if(!CommonEnum.IsFreeOrder.Yes.code.equals(order.getIsFree())) {
							ParkingLot parkinglot = parkinglotDao.getParkingLotByParkingId(parkingId);
							// 根据停车场获取收费规则
							ChargeRule chargeRule = chargeRuleDao.getRuleByParkinglot(parkinglot.getId());
							if (chargeRule == null || chargeRule.getPrice() == null) {
								order.setOutTime(currentTime);// 设置离场时间
								dao.update(order);
								parkingLotCarDao.delete(car);
								return;
							}
							BigDecimal totalFee = parkingFeeCompute(order.getInTime(), DateUtil.getDateTime(), chargeRule,parkinglot);
							// 支付状态不为空
							if (StringUtils.isNotBlank(order.getPayStatus())) {
								BigDecimal payedFee = order.getPayedFee() == null ? new BigDecimal(0) : order.getPayedFee();
								if (isOverTime(order) && payedFee.doubleValue() < totalFee.doubleValue()) {
									// 超时更新订单信息
									order.setTotalFee(totalFee);
									order.setPayStatus(CommonEnum.PayStatus.UnPay.code);
									order.setOrderStatus(CommonEnum.OrderStatus.OwnFee.code);// 设置订单为欠费状态
								} else {
									order.setOrderInvoiceStatus(CommonEnum.OrderInvoiceStatus.UnApply.code);
									order.setOrderStatus(CommonEnum.OrderStatus.End.code);
								}
							} else {// 支付状态为空
								if (totalFee.doubleValue() > 0) {
									order.setOrderStatus(CommonEnum.OrderStatus.OwnFee.code);// 设置订单为欠费状态
								} else {
									order.setPayStatus(CommonEnum.PayStatus.Payed.code);
									order.setTotalFee(totalFee);
									order.setPayedFee(totalFee);
									order.setPayWay(CommonEnum.PayWay.Cash.code);
									order.setOrderStatus(CommonEnum.OrderStatus.End.code);// 设置订单为结束状态
								}
							}
						} else {
							//免费订单直接结束
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
						dao.update(order);
						
						//根据车牌号获取车信息,若存在则修改车辆状态
						List<UserCar> cars = carDao.getCarByCarNumber(order.getCarNumber());
						if(cars != null && cars.size() > 0) {
							for (UserCar userCarDto : cars) {
								userCarDto.setStatus(CommonEnum.CarStatus.Out.code);
								carDao.update(userCarDto);
							}
						}
						//向绑定该车牌的用户推送消息
						ClientJPushSender.sendAllByTagWithExtras(order.getCarNumber(),"",CommonEnum.ClientType.User.code,"order","change");
					}
				}
			} catch (Exception e) {
				logger.error("车辆离场处理发生异常");
			}
			// 设置场内车辆离场时间
			car.setOutTime(DateUtil.getDateTime());
			parkingLotCarDao.update(car);
		}
		// 释放车位
		Parking parking = new Parking();
		parking.setId(parkingId);
		parking.setStatus(CommonEnum.ParkingStatus.Free.code);
		parkingDao.update(parking);
	}

	@Override
	public void carIn(Integer parkingId) {
		try {
			// 根据车位id获取场内车辆信息
			ParkingLotCar car = parkingLotCarDao.getParkingLotCarByParkingId(parkingId);
			if (car == null) {
				ParkingLotCar dto = new ParkingLotCar();
				dto.setParkingId(parkingId);
				dto.setInTime(DateUtil.getDateTime());
				parkingLotCarDao.insert(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateOrder(OrderReqDto reqDto) throws Exception {
		String currentTime = DateUtil.getDateTime();
		// 获取订单信息
		ParkingOrder dto = new ParkingOrder();
		dto.setId(reqDto.getOrderId());
		ParkingOrder order = dao.select(dto);
		
		//若车位发生变化
		if(reqDto.getParkingId() != order.getParkingId()) {
			//释放原来的车位
			parkingDao.updateParkingFree(order.getParkingId());
			//将当前传入的车位设置为占用
			parkingDao.updateParkingUse(reqDto.getParkingId());
			//修改订单车位
			order.setParkingId(reqDto.getParkingId());
		} 
		if (reqDto.getIsCar()) {
			order.setIsFree(CommonEnum.IsFreeOrder.No.code);
		} else {
			order.setIsFree(CommonEnum.IsFreeOrder.Yes.code);
			order.setFileUrl(reqDto.getFileName());
		}
		order.setModifier(reqDto.getOperatorId());
		order.setModifyTime(currentTime);
		order.setCarNumber(reqDto.getCarNumber());
		order.setCarType(reqDto.getCarType());
		dao.update(order);
		
		ParkingLotCar parkinglotCarDto = new ParkingLotCar();
		parkinglotCarDto.setId(reqDto.getParkinglotCarId());
		ParkingLotCar car = parkingLotCarDao.select(parkinglotCarDto);
		car.setParkingId(reqDto.getParkingId());
		car.setCarNumber(reqDto.getCarNumber());
		car.setCarType(reqDto.getCarType());
		car.setOperatorId(reqDto.getOperatorId());
		parkingLotCarDao.update(car);
	}
	
	@Override
	public Integer getParkingTime(BigDecimal payedFee, ChargeRule chargeRule) {
		Integer totalMinute = 0;
		try {
			BigDecimal price = chargeRule.getPrice();// 每小时单价
			if (chargeRule.getUpLimitFee24() == null) {
				// 计算停车时长
				totalMinute = MathUtil.mul(MathUtil.div(payedFee, price), new BigDecimal(60)).intValue();
			} else {
				BigDecimal limitFee = chargeRule.getUpLimitFee24();// 全天封顶金额
				// 已支付金额大于全天封顶金额
				if (payedFee.intValue() >= limitFee.intValue()) {
					Integer day = MathUtil.div(payedFee, limitFee).intValue();
					BigDecimal restFee = MathUtil.sub(payedFee,
							MathUtil.mul(new BigDecimal(day), limitFee));
					BigDecimal restTime = MathUtil.mul(MathUtil.div(restFee, chargeRule.getPrice()),new BigDecimal(60));
					totalMinute = day * 24 * 60 + restTime.intValue();
				} else {
					totalMinute = MathUtil.mul(MathUtil.div(payedFee, price), new BigDecimal(60)).intValue();
				}
			}
		} catch (Exception e) {
			logger.error("compute parking time occurr exception.");
		}
		return totalMinute;
	}

	@Override
	public List<ParkingOrder> queryUserOrdersByPage(ParkingOrder dto) {
		List<ParkingOrder> list = dao.queryUserOrdersByPage(dto);
		if(list != null && list.size() > 0) {
			for (ParkingOrder order : list) {
				//计算停车时长
				String startTime = order.getInTime();
				String endTime = order.getOutTime();
				String duration = DateUtils.dateCommpute(startTime,endTime);
				order.setDuration(duration);
				if(StringUtils.isNotBlank(order.getPayWay()) && !CommonEnum.PayWay.Cash.code.equals(order.getPayWay())) {
					order.setPayWay(CommonEnum.PayWay.Online.code);
				}
			}
		}
		return list;
	}

	@Override
	public ParkingOrder getOrderByCar(Integer carId) throws Exception {
		ParkingOrder respOrder = new ParkingOrder();
		//获取车辆信息
		UserCar carDto = new UserCar();
		carDto.setId(carId);
		UserCar car = carDao.select(carDto);
		ParkingOrder dto = new ParkingOrder();
//		dto.setUserId(car.getUserId());
		dto.setCarNumber(car.getCarNumber());
		dto.setOrderStatus(CommonEnum.OrderStatus.Ing.code);
		//获取订单信息
		ParkingOrder order = dao.getOrderByCar(dto);
		if(order == null) {
			return null;
		}
		String currentTime = DateUtil.getDateTime();
		String intime = order.getInTime();
		//获取停车场信息
		ParkingLot parkinglot = parkinglotDao.getParkingLotByParkingId(order.getParkingId());
		BigDecimal totalFee = order.getTotalFee() == null ? new BigDecimal(0) : order.getTotalFee();
		BigDecimal payedFee = order.getPayedFee() == null ? new BigDecimal(0) : order.getPayedFee();
		//若订单状态为已支付 则计算离场时间
		if(CommonEnum.PayStatus.Payed.code.equals(order.getPayStatus())) {
			//根据停车场获取收费规则
			ChargeRule chargeRule = chargeRuleDao.getRuleByParkinglot(parkinglot.getId());
			Integer parkingTime = getParkingTime(payedFee, chargeRule);
			String outTime = DateUtils.afterNMinuteTime(intime,parkinglot.getLeaveOutTime() + parkingTime);
			respOrder.setOutTime(outTime);
		} else {
			//根据停车订单号计算停车总费用
			totalFee = parkingOrderSettlement(order);
			order.setTotalFee(totalFee);
			//总费用 = 停车总费用 + 预约费用
			Reservation reserveInfo = reservationDao.getReservationByOrderNo(order.getOrderNo());
			if(reserveInfo != null) {
				totalFee = MathUtil.add(totalFee, reserveInfo.getPayedFee());
			}
			//更新订单总金额
			dao.update(order);
		}
		
		Parking parkingDto = new Parking();
		parkingDto.setId(order.getParkingId());
		Parking parking = parkingDao.select(parkingDto);
		if(parkinglot.getHaveLock()) {
			//获取车位锁
			if(StringUtils.isNotBlank(parking.getLockSerialNumber())) {
				ParkingLock lock = new ParkingLock();
				lock.setSerialNumber(parking.getLockSerialNumber());
				respOrder.setParkingLock(lock);
			}
		}
		//获取地图
		if(parkinglot.getHaveMap()) {
			IndoorMap map = indoorMapDao.getIndoorMapById(parking.getIndoorMapId());
			respOrder.setIndoorMap(map);
		}
		
		respOrder.setId(order.getId());
		respOrder.setOrderNo(order.getOrderNo());
		respOrder.setIsFree(order.getIsFree());
		respOrder.setTotalFee(totalFee);
		respOrder.setUnPayFee(MathUtil.sub(totalFee, payedFee));
		respOrder.setPayStatus(StringUtils.isBlank(order.getPayStatus()) ? CommonEnum.PayStatus.UnPay.code : order.getPayStatus());
		String duration = DateUtils.dateCommpute(intime,currentTime);
		respOrder.setDuration(duration);
		respOrder.setParkinglotName(parkinglot.getParkinglotName());
		respOrder.setCoordinateX(parkinglot.getCoordinateX());
		respOrder.setCoordinateY(parkinglot.getCoordinateY());
		
		return respOrder;
	}

	@Override
	public ParkingOrder getUserOrderById(Integer orderId) throws Exception {
		ParkingOrder resp = new ParkingOrder();
		ParkingOrder order = dao.getOrderById(orderId);
		String currentTime = DateUtil.getDateTime();
		if(order != null) {
			//获取停车场信息
			ParkingLot parkinglot = parkinglotDao.getParkingLotByParkingId(order.getParkingId());
			String startTime = order.getInTime();
			String outTime = order.getOutTime();
			if(StringUtils.isBlank(outTime)) {
				outTime = currentTime;
			}
			String duration = DateUtils.dateCommpute(startTime,outTime);
			//根据停车订单号计算停车总费用
			BigDecimal totalFee = parkingOrderSettlement(order);
			//已支付费用
			BigDecimal payedFee = order.getPayedFee() == null ? new BigDecimal(0) : order.getPayedFee();
			//总费用 = 停车总费用 + 预约费用
			Reservation reserveInfo = reservationDao.getReservationByOrderNo(order.getOrderNo());
			if(reserveInfo != null) {
				totalFee = MathUtil.add(totalFee, reserveInfo.getPayedFee());
			}
			
			if(!CommonEnum.IsFreeOrder.Yes.code.equals(order.getIsFree())) {
				/*
				 * 订单已支付 根据支付金额计算离场时间
				 * 离场时间=进场时间+(支付费用/单价)*小时  + 停车场免费离场时间 
				 */
				if(CommonEnum.PayStatus.Payed.code.equals(order.getPayStatus())){
					//检查支付是否超时
					if(isOverTime(order)) {
						order.setTotalFee(totalFee);
						order.setPayStatus(CommonEnum.PayStatus.UnPay.code);
						//更新订单信息
						dao.update(order);
					} else {
						resp.setPayTime(order.getPayTime());
						ChargeRule chargeRule = chargeRuleDao.getRuleByParkinglot(parkinglot.getId());
						Integer parkingTime = getParkingTime(payedFee, chargeRule);
						String intime = order.getInTime();
						outTime = DateUtils.afterNMinuteTime(intime,parkinglot.getLeaveOutTime() + parkingTime);
					}
				}else {
					//未支付 则无离场时间
					order.setTotalFee(totalFee);
					//更新订单总金额
					dao.update(order);
				}
			}
			
			
			resp.setOrderNo(order.getOrderNo());
			resp.setCarNumber(order.getCarNumber());
			resp.setInTime(order.getInTime());
			resp.setOutTime(outTime);
			resp.setPayTime(currentTime);
			resp.setDuration(duration);
			resp.setTotalFee(order.getTotalFee());
			resp.setPayStatus(StringUtils.isBlank(order.getPayStatus()) ? CommonEnum.PayStatus.UnPay.code : order.getPayStatus());
			resp.setPayedFee(payedFee);
			resp.setUnPayFee(MathUtil.sub(totalFee, payedFee));
			resp.setParkinglotId(order.getParkinglotId());
			resp.setParkinglotName(order.getParkinglotName());
			//获取车位锁
			Parking parkingDto = new Parking();
			parkingDto.setId(order.getParkingId());
			Parking parking = parkingDao.select(parkingDto);
			if(parking != null && StringUtils.isNotBlank(parking.getLockSerialNumber())) {
				ParkingLock lock = new ParkingLock();
				lock.setSerialNumber(parking.getLockSerialNumber());
				resp.setParkingLock(lock);
			}
		
			//获取地图
			if(parking != null && parking.getIndoorMapId() != null) {
				IndoorMap map = indoorMapDao.getIndoorMapById(parking.getIndoorMapId());
				resp.setIndoorMap(map);
			}
		}
		return resp;
	}
	
	@Override
	public boolean isOverTime(ParkingOrder order) throws ParseException {
		boolean isOverTime = false;
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_FULL_STR);
		String currentTime = DateUtil.getDateTime();
		ParkingLot parkinglot = parkinglotDao.getParkingLotByParkingId(order.getParkingId());
		// 根据停车场获取收费规则
		ChargeRule chargeRule = chargeRuleDao.getRuleByParkinglot(parkinglot.getId());
		BigDecimal payedFee = order.getPayedFee() == null ? new BigDecimal(0) : order.getPayedFee();
		// 计算停车时长
		Integer parkingTime = getParkingTime(payedFee, chargeRule);
		String intime = order.getInTime();
		Calendar endTime = Calendar.getInstance();
		endTime.setTime(sdf.parse(intime));
		endTime.add(Calendar.MINUTE, parkinglot.getLeaveOutTime());
		endTime.add(Calendar.MINUTE, parkingTime.intValue());
		Date now = sdf.parse(currentTime);
		if (endTime.getTime().getTime() < now.getTime()) {
			isOverTime = true;
		}
		return isOverTime;
	}
	
}
