package com.nitian.util.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 日志管理
 * 
 * @author 1036225283
 *
 */
public class LogManager {

	private static LogManager logManager = new LogManager();

	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");

	public static LogManager getInstance() {
		return logManager;
	}

	private LogManager() {
		// TODO Auto-generated constructor stub
	}

	private Set<String> typeSet = new HashSet<String>();

	public void info(String type, String info) {
		if (typeSet.contains(type)) {
			System.out.println(info);
		}
	}

	public void info(String type, Object object, String info) {
		if (typeSet.contains(type)) {
			System.out.println(object.getClass().getName() + ":" + info);
		}
	}

	public void dateInfo(String type, Object object, String info) {
		if (typeSet.contains(type)) {
			System.out.println(dateFormat.format(new Date()) + ":"
					+ object.getClass().getName() + ":" + info);
		}
	}

	public void info(LogType logType, String info) {
		if (typeSet.contains(logType.toString())) {
			System.out.println(info);
		}
	}

	public void info(LogType logType, Object object, String info) {
		if (typeSet.contains(logType.toString())) {
			System.out.println(object.getClass().getName() + ":" + info);
		}
	}

	public void dateInfo(LogType logType, Object object, String info) {
		if (typeSet.contains(logType.toString())) {
			System.out.println(dateFormat.format(new Date()) + ":"
					+ object.getClass().getName() + ":" + info);
		}
	}

	/**
	 * 添加日志输出类型
	 * 
	 * @param type
	 */
	public void putType(String type) {
		typeSet.add(type);
	}

	/**
	 * 移除日志输出类型
	 * 
	 * @param type
	 */
	public void remoteType(String type) {
		typeSet.remove(type);
	}

}
