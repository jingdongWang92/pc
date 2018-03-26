package com.jcble.jcparking.common.service.pay.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcble.jcparking.common.CommonEnum;
import com.jcble.jcparking.common.dao.admin.ParkingDao;
import com.jcble.jcparking.common.dao.admin.ParkingOrderDao;
import com.jcble.jcparking.common.dao.pay.PayLogDao;
import com.jcble.jcparking.common.dao.user.RechargeRecordDao;
import com.jcble.jcparking.common.dao.user.ReservationDao;
import com.jcble.jcparking.common.dao.user.UserCarDao;
import com.jcble.jcparking.common.dao.user.UserDao;
import com.jcble.jcparking.common.dto.request.PayRecordReqDto;
import com.jcble.jcparking.common.exception.ParkingServiceException;
import com.jcble.jcparking.common.model.admin.ParkingOrder;
import com.jcble.jcparking.common.model.pay.PayLog;
import com.jcble.jcparking.common.model.user.RechargeRecord;
import com.jcble.jcparking.common.model.user.Reservation;
import com.jcble.jcparking.common.model.user.User;
import com.jcble.jcparking.common.model.user.UserCar;
import com.jcble.jcparking.common.service.pay.PayLogService;
import com.jcble.jcparking.common.utils.ClientJPushSender;
import com.jcble.jcparking.common.utils.MathUtil;

import baseproj.common.util.DateUtil;

/**
 * 支付日志接口实现
 * 
 * @author Jingdong Wang
 * @date 2017年3月21日 下午2:46:47
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PayLogServiceImpl implements PayLogService {

	@Autowired
	private PayLogDao payLogDao;
	@Autowired
	private ParkingOrderDao orderDao;
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private UserCarDao userCarDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ParkingDao parkingDao;
	@Autowired
	private RechargeRecordDao rechargeDao;
	
	@Override
	public void payFeedback(PayRecordReqDto reqDto) throws Exception {
		System.out.println("==========================支付回调开始==============================");
		PayLog payLog = new PayLog();
		String currentTime = DateUtil.getDateTime();
		String payStatus = reqDto.getPayStatus();
		String tradeStatus = reqDto.getTradeStatus();
		String type = reqDto.getType();
		String payWay = reqDto.getPayWay();
		BigDecimal amount = reqDto.getAmount();
		String orderNo = reqDto.getOrderNo();
		
		payLog.setPayStatus(payStatus);
		payLog.setPayWay(payWay);
		payLog.setType(type);
		payLog.setTradeTime(currentTime);
		payLog.setAmount(amount);
		payLog.setOrderNo(orderNo);
		payLog.setTradeStatus(tradeStatus);
		System.out.println(reqDto.toString());
		//判断是否重复
		PayLog payLogDto = payLogDao.getPayLog(payLog);
		if(!CommonEnum.PayWay.Balance.code.equals(payWay) && payLogDto != null) {
			System.out.println("交易记录已存在");
			return;
		}
		
		if(orderNo.length() > 16) {
			orderNo = orderNo.substring(0, 18);
		}
		// 停车缴费
		if (CommonEnum.FeeType.ParkingFee.code.equals(type)) {
			if (CommonEnum.PayStatus.Payed.code.equals(payStatus)) {
				// 获取停车订单
				ParkingOrder order = orderDao.getOrderByOrderNo(orderNo);
				if (order == null) {
					throw new ParkingServiceException(ParkingServiceException.ERROR_10009);
				}
				payLog.setParkingId(order.getParkingId());
				order.setPayStatus(CommonEnum.PayStatus.Payed.code);
				BigDecimal payedFee = order.getPayedFee() == null ? new BigDecimal(0) : order.getPayedFee();
				order.setPayWay(payWay);
				order.setPayedFee(MathUtil.add(payedFee, amount));
				order.setPayTime(currentTime);
				orderDao.update(order);
				// 修改用户车辆状态
				UserCar carDto = new UserCar();
				carDto.setUserId(order.getUserId());
				carDto.setCarNumber(order.getCarNumber());
				UserCar car = userCarDao.getCarByUserIdAndCarNumber(carDto);
				if (car != null) {
					car.setStatus(CommonEnum.CarStatus.NotLeave.code);
					userCarDao.update(car);
				}
				payLog.setUserId(order.getUserId());
				payLog.setParkingId(order.getParkingId());
				
				//推送消息给管理员
				ClientJPushSender.sendAllByAliasWithExtras(order.getParkingId()+"","",CommonEnum.ClientType.Admin.code,"order","change");
			}
		} else if (CommonEnum.FeeType.AppointParkingFee.code.equals(type)) {
			Reservation order = reservationDao.getReservationByOrderNo(orderNo);
			order.setPayTime(currentTime);
			order.setPayWay(reqDto.getPayWay());
			order.setPayedFee(reqDto.getAmount());
			payLog.setUserId(order.getUserId());
			payLog.setParkingId(order.getParkingId());
			//支付成功
			if (CommonEnum.PayStatus.Payed.code.equals(payStatus)) {
				order.setStatus(CommonEnum.RevatationStatus.Success.code);
				order.setPayStatus(CommonEnum.PayStatus.Payed.code);
				// 修改用户车辆状态
				UserCar carDto = new UserCar();
				carDto.setUserId(order.getUserId());
				carDto.setCarNumber(order.getCarNumber());
				UserCar car = userCarDao.getCarByUserIdAndCarNumber(carDto);
				car.setStatus(CommonEnum.CarStatus.Reservation.code);
				userCarDao.update(car);
			} else if(CommonEnum.PayStatus.Refund.code.equals(payStatus)) {
				//修改订单支付状态为已退款
				order.setPayStatus(CommonEnum.PayStatus.Refund.code);
			} else {
				//支付失败 释放车位
				parkingDao.updateParkingFree(order.getParkingId());
				//修改预约订单状态为预约失败
				order.setStatus(CommonEnum.RevatationStatus.Failure.code);
			}
			//修改预约订单信息
			reservationDao.update(order);
		} else if(CommonEnum.FeeType.BalanceRecharge.code.equals(type)) {
			if (CommonEnum.PayStatus.Payed.code.equals(payStatus)) {
				User user = userDao.getUserByUserId("");
				BigDecimal userBalance = user.getBalance() == null ? new BigDecimal(0) : user.getBalance();
				user.setBalance(MathUtil.add(userBalance,reqDto.getAmount()));
				userDao.update(user);
				
				//保存充值记录
				RechargeRecord rechargeRecord = new RechargeRecord();
				rechargeRecord.setUserId(user.getId());
				rechargeRecord.setPayWay(payWay);
				rechargeRecord.setPayTime(currentTime);
				rechargeRecord.setChargeAmount(amount);
				rechargeRecord.setRealAmount(amount);
				rechargeRecord.setIsDiscount(CommonEnum.YesNoFlg.No.code);
				rechargeDao.insert(rechargeRecord);
				
			}
		}
		payLogDao.insert(payLog);
		System.out.println("==========================支付回调结束==============================");
	}

}
