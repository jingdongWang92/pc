package com.jcble.jcparking.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理相关工具类
 * 
 * @author Jingdong Wang
 *
 */
public class DateUtils {

	/** yyyy-MM-dd HH:mm:ss **/
	public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
	/** yyyy-MM-dd **/
	public static final String DATE_SMALL_STR = "yyyy-MM-dd";

	/**
	 * 日期格式
	 */
	public static final SimpleDateFormat defaultDatetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 获取系统当前时间
	 * 
	 * @return
	 */
	public static Date getDate() {
		return new Date();
	}

	/**
	 * 获取指定格式的系统当前时间
	 * 
	 * @param type
	 *            日期格式
	 * @return
	 */
	public static String getFormatDate(String type) {
		SimpleDateFormat df = new SimpleDateFormat(type);
		return df.format(new Date());
	}

	/**
	 * 使用预设格式提取日期
	 * 
	 * @param strDate
	 *            日期字符串
	 * @return
	 */
	public static Date strToDate(String strDate) {
		return strToDate(strDate, DATE_FULL_STR);
	}

	/**
	 * 使用用户格式提取日期
	 * 
	 * @param strDate
	 *            日期字符串
	 * @param pattern
	 *            日期格式
	 * @return
	 */
	public static Date strToDate(String strDate, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 使用预设格式提取字符串日期
	 * 
	 * @param strDate
	 *            日期字符串
	 * @return
	 */
	public static String dateToStr(Date date) {
		return dateToStr(date, DATE_FULL_STR);
	}

	/**
	 * 使用用户格式提取字符串日期
	 * 
	 * @param strDate
	 *            日期字符串
	 * @param pattern
	 *            日期格式
	 * @return
	 */
	public static String dateToStr(Date date, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}

	/**
	 * 计算时间差 并格式化成 xx天xx小时xx分
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static String dateCommpute(String startTime, String endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FULL_STR);
		String formatTime = "";
		try {
			Date date1 = sdf.parse(startTime);
			Date date2 = sdf.parse(endTime);
			long min = dateMinDiff(date1, date2);
			Integer hoursStr = 0;
			Integer minutesStr = 0;
			if (min <= 60) {
				minutesStr = (int) min;
			} else {
				hoursStr = (int) (min / 60);
				if (min > hoursStr * 60) {
					minutesStr = (int) (min - hoursStr * 60);
				}
			}
			formatTime = hoursStr + "小时" + minutesStr + "分";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return formatTime;
	}

	/**
	 * 计算时间的分钟数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static long dateMinDiff(Date date1, Date date2) {
		long diff = date2.getTime() - date1.getTime();
		return diff / (1000 * 60);
	}
	
	/**
	 * 将字符串转换为时间(yyyy-MM-dd HH:mm:ss形式)
	 * 
	 * @param strDatetime
	 *            时间字符串
	 * @return
	 */
	public static Date getDatetimeFromString(String strDatetime) {
		return getDateFromString(strDatetime, defaultDatetimeFormat);
	}

	/**
	 * 将字符串转换为时间
	 * 
	 * @param strDate
	 *            时间字符串
	 * @param format
	 *            格式化样式
	 * @return
	 */
	public static Date getDateFromString(String strDate, SimpleDateFormat format) {
		if (strDate == null) {
			return null;
		}
		try {
			SimpleDateFormat localDateFormat = format;
			Date date = localDateFormat.parse(strDate);
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	/**
     * 计算系统当前时间加N分钟后的时间
     * 
     * @param minute
     */
    public static String afterNMinuteTime(int minute) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, minute);
		return defaultDatetimeFormat.format(now.getTimeInMillis());
    	
    }
    
    /**
     * 计算指定时间加N分钟后的时间
     * 
     * @param minute
     * @throws ParseException 
     */
    public static String afterNMinuteTime(String startTime,int minute) throws ParseException {
    	Date startDate = defaultDatetimeFormat.parse(startTime);
    	Calendar now=Calendar.getInstance();
    	now.setTime(startDate);;
    	now.add(Calendar.MINUTE, minute);
    	return defaultDatetimeFormat.format(now.getTimeInMillis());
    	
    }

}