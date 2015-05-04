package com.nitian.util.time;

import java.util.Date;

public class TimeInteger {

	public TimeInteger() {
		// TODO Auto-generated constructor stub
		timeToInteger();
	}

	public TimeInteger(Date date) {
		// TODO Auto-generated constructor stub
		this.date = date;
		timeToInteger();
	}

	private int year;

	private int month;

	private int day;

	private int hour;

	private int minute;

	private int second;

	private Date date;

	private void timeToInteger() {
		TimeString timeString = new TimeString(date);
		year = Integer.valueOf(timeString.getYear());
		month = Integer.valueOf(timeString.getMonth());
		day = Integer.valueOf(timeString.getDay());
		hour = Integer.valueOf(timeString.getHour());
		minute = Integer.valueOf(timeString.getMinute());
		second = Integer.valueOf(timeString.getSecond());
	}

	/**
	 * 转换为时间
	 * 
	 * @return
	 */
	public Date integerToTime() {
		TimeString timeString = new TimeString();
		timeString.setDay(String.valueOf(day));
		timeString.setHour(String.valueOf(hour));
		timeString.setMinute(String.valueOf(minute));
		timeString.setMonth(String.valueOf(month));
		timeString.setSecond(String.valueOf(second));
		timeString.setYear(String.valueOf(year));
		return timeString.stringToTime();
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

}
