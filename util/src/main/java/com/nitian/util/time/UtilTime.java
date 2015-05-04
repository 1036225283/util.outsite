package com.nitian.util.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilTime {

	/**
	 * 将String 转换为Date
	 * 
	 * @return
	 */
	public static Date StringToDate(String time) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将Date转换为String
	 * 
	 * @return
	 */
	public static String DateToString(Date date) {
		return DateFormat.getDateInstance().format(date);
	}

	public static void main(String[] args) {
		System.out.println(StringToDate("2013-2-3"));
	}
}
