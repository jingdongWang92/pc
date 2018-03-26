package com.jcble.jcparking.common.service.user.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.response.AlipayTradeRefundResponse;
import com.jcble.jcparking.common.CommonEnum;
import com.jcble.jcparking.common.dao.admin.ParkingDao;
import com.jcble.jcparking.common.dao.admin.ParkingLotDao;
import com.jcble.jcparking.common.dao.user.IndoorMapDao;
import com.jcble.jcparking.common.dao.user.ReservationDao;
import com.jcble.jcparking.common.dao.user.UserCarDao;
import com.jcble.jcparking.common.dao.user.UserDao;
import com.jcble.jcparking.common.dto.request.ReservationReqDto;
import com.jcble.jcparking.common.dto.response.ReserveRespDto;
import com.jcble.jcparking.common.exception.ParkingServiceException;
import com.jcble.jcparking.common.model.admin.Parking;
import com.jcble.jcparking.common.model.admin.ParkingLot;
import com.jcble.jcparking.common.model.parking.IndoorMap;
import com.jcble.jcparking.common.model.user.Reservation;
import com.jcble.jcparking.common.model.user.UserCar;
import com.jcble.jcparking.common.model.user.User;
import com.jcble.jcparking.common.service.admin.ParkingService;
import com.jcble.jcparking.common.service.pay.AliPayService;
import com.jcble.jcparking.common.service.pay.WechatPayService;
import com.jcble.jcparking.common.service.user.ReservationService;
import com.jcble.jcparking.common.utils.DateUtils;
import com.jcble.jcparking.common.utils.Generator;
import com.jcble.jcparking.common.utils.MathUtil;

import baseproj.common.util.DateUtil;
import weixin.popular.bean.paymch.SecapiPayRefundResult;
import weixin.popular.bean.paymch.UnifiedorderResult;

/**
 * 预约订单模块业务实现类
 * 
 * @author Jingdong Wang
 * @date 2017年3月14日 上午10:22:28
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationDao dao;
	@Autowired
	private UserCarDao carDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ParkingDao parkingDao;
	@Autowired
	private ParkingLotDao parkinglotDao;

	@Autowired
	private ParkingService parkingService;
	@Autowired
	private AliPayService aliPayService;
	@Autowired
	private WechatPayService wechatPayService;
	@Autowired
	private IndoorMapDao indoorMapDao;

	@Override
	public List<Reservation> getUserReservations(Reservation dto) throws Exception {
		List<Reservation> list = dao.queryReservationsByPage(dto);
		for (Reservation reservation : list) {
			Parking parkingDto = new Parking();
			parkingDto.setId(reservation.getParkingId());
			Parking parking = parkingDao.select(parkingDto);
			if(parking != null && parking.getIndoorMapId() != null) {
				IndoorMap map = indoorMapDao.getIndoorMapById(parking.getIndoorMapId());
				reservation.setIndoorMap(map);
			}
		}
		return list;
	}

	@Override
	public ReserveRespDto reservationParking(ReservationReqDto reqDto) throws Exception {
		ReserveRespDto resp = new ReserveRespDto();
		String currentTime = DateUtil.getDateTime();
		UserCar carDto = new UserCar();
		carDto.setUserId(reqDto.getUserId());
		carDto.setCarNumber(reqDto.getCarNumber());
		UserCar car = carDao.getCarByUserIdAndCarNumber(carDto);
		if (car == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10011);
		} else {
			if (!CommonEnum.CarStatus.Out.code.equals(car.getStatus())) {
				throw new ParkingServiceException(ParkingServiceException.ERROR_10020);
			}
		}
		// 获取一个可预约车位
		Parking freeParking = parkingService.getReservationParking(reqDto.getParkinglotId());
		// 新增预约记录
		String orderNo = Generator.generateOrderNo();
		Reservation reservationDto = new Reservation();
		reservationDto.setUserId(reqDto.getUserId());
		reservationDto.setParkingId(freeParking.getId());
		reservationDto.setStartTime(currentTime);
		reservationDto.setExpireTime(DateUtils.afterNMinuteTime(currentTime, reqDto.getDuration()));
		reservationDto.setCarNumber(reqDto.getCarNumber());
		reservationDto.setOrderNo(orderNo);
		reservationDto.setStatus(CommonEnum.RevatationStatus.Revatationed.code);
		reservationDto.setPayStatus(CommonEnum.PayStatus.UnPay.code);
		reservationDto.setDuration(reqDto.getDuration());
		dao.insert(reservationDto);

		// 设置车位为占用
		freeParking.setStatus(CommonEnum.ParkingStatus.Use.code);
		parkingDao.update(freeParking);

//		car.setStatus(CommonEnum.CarStatus.Reservation.code);
//		carDao.update(car);
		
		//生成交易订单
		orderNo = Generator.generatePayOrderNo(orderNo);
		if(CommonEnum.PayWay.Balance.code.equals(reqDto.getPayWay())) {
			User user = userDao.getUserByUserId(reqDto.getUserId());
			BigDecimal balance = user.getBalance() == null ? new BigDecimal(0) : user.getBalance();
			BigDecimal payAmount = reqDto.getPayFee();
			if(balance.doubleValue() < payAmount.doubleValue()) {
				throw new ParkingServiceException(ParkingServiceException.ERROR_10025);
			}
			user.setBalance(MathUtil.sub(balance,payAmount));
			userDao.update(user);
			dao.update(reservationDto);
			
		} else if(CommonEnum.PayWay.Alipay.code.equals(reqDto.getPayWay())) {
			try {
				String alipayInfo = aliPayService.getAlipayOrderInfo(orderNo,reqDto.getPayFee().toString(),CommonEnum.FeeType.AppointParkingFee.code);
				resp.setAlipayInfo(alipayInfo);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ParkingServiceException(ParkingServiceException.ERROR_10024);
			}
		}else if(CommonEnum.PayWay.WxPay.code.equals(reqDto.getPayWay())) {
			UnifiedorderResult weixinPayInfo = wechatPayService.getWxPayOrderInfo(reqDto.getIpAddr(),orderNo,reqDto.getPayFee().toString(),CommonEnum.FeeType.AppointParkingFee.code);
			if("FAIL".equals(weixinPayInfo.getResult_code())) {
				throw new ParkingServiceException(ParkingServiceException.ERROR_10024);
			}
			resp.setWeixinPayInfo(weixinPayInfo);
		}
		resp.setParkingNo(freeParking.getParkingNo());
		resp.setOrderNo(orderNo);
		return resp;
	}

	@Override
	public void cancelReservation(Integer id) throws Exception {
		Reservation dto = new Reservation();
		dto.setId(id);
		Reservation reservation = dao.select(dto);
		if (reservation == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10009);
		}
		if (!CommonEnum.RevatationStatus.Success.code.equals(reservation.getStatus())) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10022);
		}

		String startTime = reservation.getStartTime();
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_FULL_STR);
		long diffMinute = DateUtils.dateMinDiff(sdf.parse(startTime),new Date());
		if (diffMinute > 10) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10023);
		}

		// 更新车位状态为空闲
		parkingDao.updateParkingFree(reservation.getParkingId());
		// 修改用户车牌状态
		UserCar carDto = new UserCar();
		carDto.setUserId(reservation.getUserId());
		carDto.setCarNumber(reservation.getCarNumber());
		UserCar car = carDao.getCarByUserIdAndCarNumber(carDto);
		car.setStatus(CommonEnum.CarStatus.Out.code);
		carDao.update(car);

		// 退款
		refund(id);

		// 更新预约订单状态为"已取消"
		reservation.setStatus(CommonEnum.RevatationStatus.Cancel.code);
		dao.update(reservation);
	}

	@Override
	public Reservation getReservationByCar(Integer carId,double x, double y) throws Exception {
		Reservation resp = new Reservation();
		UserCar carDto = new UserCar();
		carDto.setId(carId);
		UserCar car = carDao.select(carDto);
		if (car != null) {
			Reservation reservationDto = new Reservation();
			reservationDto.setCarNumber(car.getCarNumber());
			reservationDto.setUserId(car.getUserId());
			reservationDto.setStatus(CommonEnum.RevatationStatus.Success.code);
			Reservation reservation = dao.getReservationByCar(reservationDto);
			if (reservation != null) {
				ParkingLot parkinglot = parkinglotDao.getParkingLotByParkingId(reservation.getParkingId());
				resp.setId(reservation.getId());
				resp.setParkingNo(reservation.getParkingNo());
				resp.setParkinglotName(reservation.getParkinglotName());
				resp.setExpireTime(reservation.getExpireTime());
				resp.setCoordinateX(parkinglot.getCoordinateX());
				resp.setCoordinateY(parkinglot.getCoordinateY());
				Double x1 = parkinglot.getCoordinateX();//停车场位置x坐标
				Double y1 = parkinglot.getCoordinateY();//停车场位置y坐标
				Integer distance = MathUtil.getDistance(x,y,x1,y1);
				resp.setDistance(distance);
			}
		}
		return resp;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void refund(Integer id) throws Exception {
		Reservation dto = new Reservation();
		dto.setId(id);
		Reservation reservation = dao.select(dto);
		if (reservation == null) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10009);
		}
		// 根据订单的支付方式将钱原路返给用户
		String payWay = reservation.getPayWay();
		if (StringUtils.isNotBlank(payWay)) {
			if (CommonEnum.PayWay.Balance.code.equals(payWay)) {
				// 将本次订单的费用返回到用户余额中
				User user = userDao.getUserByUserId(reservation.getUserId());
				if (user != null) {
					BigDecimal balance = user.getBalance() == null ? new BigDecimal(0) : user.getBalance();
					user.setBalance(MathUtil.add(balance, reservation.getPayedFee()));
					userDao.update(user);
					reservation.setPayStatus(CommonEnum.PayStatus.Refund.code);
				}
			} else if (CommonEnum.PayWay.Alipay.code.equals(payWay)) {
				// 调用支付接口退款
				AlipayTradeRefundResponse response = new AlipayTradeRefundResponse();
				try {
					response = aliPayService.refund(reservation.getOrderNo());
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 退款成功
				if (response.isSuccess()) {
					reservation.setPayStatus(CommonEnum.PayStatus.Refund.code);
				} else {
					reservation.setPayStatus(CommonEnum.PayStatus.UnRefund.code);
				}

			} else if (CommonEnum.PayWay.WxPay.code.equals(payWay)) {
				SecapiPayRefundResult result = new SecapiPayRefundResult();
				try {
					// 微信退款
					result = wechatPayService.refund(reservation.getOrderNo());
				} catch (Exception e) {
					result.setResult_code("FAIL");
					e.printStackTrace();
				}
				// 退款成功
				if ("SUCCESS".equals(result.getReturn_code())) {
					reservation.setPayStatus(CommonEnum.PayStatus.Refund.code);
				} else {
					reservation.setPayStatus(CommonEnum.PayStatus.UnRefund.code);
				}
			}
		}
		dao.update(reservation);
	}

}
