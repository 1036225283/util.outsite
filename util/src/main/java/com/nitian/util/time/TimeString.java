package com.nitian.util.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeString {

	/**
	 * 将当前时间转换为年月日 yyyy-MM-dd HH:mm:ss
	 */
	public TimeString() {
		// TODO Auto-generated constructor stub
		date = new Date();
		timeToString();
	}

	public TimeString(Date date) {
		// TODO Auto-generated constructor stub
		this.date = date;
		timeToString();
	}

	private Date date;

	private String year;

	private String month;

	private String day;

	private String hour;

	private String minute;

	private String second;

	private void timeToString() {
		DateFormat yyyy = new SimpleDateFormat("yyyy");
		year = yyyy.format(date);

		DateFormat MM = new SimpleDateFormat("MM");
		month = MM.format(date);

		DateFormat dd = new SimpleDateFormat("dd");
		day = dd.format(date);

		DateFormat HH = new SimpleDateFormat("HH");
		hour = HH.format(date);

		DateFormat mm = new SimpleDateFormat("mm");
		minute = mm.format(date);

		DateFormat ss = new SimpleDateFormat("ss");
		second = ss.format(date);
	}

	public Date stringToTime() {
		DateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stringDate = year + "-" + month + "-" + day + " " + hour + ":"
				+ minute + ":" + second;
		Date date = null;
		try {
			date = time.parse(stringDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 对象互转
	 * 
	 * @return
	 */
	public TimeInteger timeStingToTimeInteger() {
		TimeInteger timeInteger = new TimeInteger();
		timeInteger.setDay(Integer.valueOf(day));
		timeInteger.setHour(Integer.valueOf(hour));
		timeInteger.setMinute(Integer.valueOf(minute));
		timeInteger.setMonth(Integer.valueOf(month));
		timeInteger.setSecond(Integer.valueOf(second));
		timeInteger.setYear(Integer.valueOf(year));
		return timeInteger;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

}
