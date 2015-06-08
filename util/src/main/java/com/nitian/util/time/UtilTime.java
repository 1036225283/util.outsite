package com.nitian.util.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilTime {

	public static long beforeTime = 30l * 1000 * 60;

	/**
	 * 当天预约,提前30分钟
	 * 
	 * @return
	 */
	public static boolean judgeTime(int currentHour) {
		TimeInteger timeInteger = new TimeInteger(new Date());
		timeInteger.setHour(currentHour + 1);
		timeInteger.setMinute(0);
		timeInteger.setSecond(0);
		Long judgeTime = timeInteger.integerToTime().getTime();
		Long currentTime = new Date().getTime();
		if ((judgeTime - currentTime) >= beforeTime) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 将date转换为yyyyMMddHHmmss字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToyyyyMMddHHmmss(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		return simpleDateFormat.format(date);
	}

	/**
	 * 字符串转换为时间yyyy-MM-dd HH:mm:ss
	 * 
	 * @param time
	 * @return
	 */
	public static Date stringToDate(String time) {
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = formatter.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将字符串年月日转换为时间
	 * 
	 * @param time
	 * @return
	 */
	public static Date stringYMDToDate(String time) {
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = formatter.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}
