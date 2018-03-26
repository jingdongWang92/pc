package com.jcble.jcparking.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 
 * @author Jingdong Wang
 * @Date   2017-02-24
 *
 */
public class Generator {

	/**
	 * 生成订单号
	 * 
	 * @return
	 */
	public synchronized static String generateOrderNo() {
		long currentTime = System.currentTimeMillis();
		Date date = new Date(currentTime);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		StringBuilder tempStr = new StringBuilder(formatter.format(date));
		tempStr.append(RandomStringUtils.randomNumeric(1));
		return tempStr.toString();
	}
	
	/**
	 * 生成支付订单号
	 * 在原来订单号后面增加5位随机数
	 * 
	 * @return
	 */
	public synchronized static String generatePayOrderNo(String orderNo) {
		StringBuilder tempStr = new StringBuilder(orderNo);
		tempStr.append(RandomStringUtils.randomNumeric(5));
		return tempStr.toString();
	}
	
	/**
	 * 生成用户id
	 * 
	 * @return
	 */
	public synchronized static String generateUserId() {
		StringBuilder tempStr = new StringBuilder();
		tempStr.append(RandomStringUtils.random(1, "123456789"));
		tempStr.append(RandomStringUtils.randomNumeric(8));
		return tempStr.toString();
	}

}
