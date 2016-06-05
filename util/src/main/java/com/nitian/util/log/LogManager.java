package com.nitian.util.log;

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
	private static Log log = new Log("D:\\", "log");
	private static boolean isFileLog = true;
	private static long startTime = 0;

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
			if (isFileLog) {
				log.info(info);
			}
		}
	}

	public void info(String type, Object object, String info) {
		if (typeSet.contains(type)) {
			System.out.println(object.getClass().getName() + ":" + info);
			if (isFileLog) {
				log.info(object.getClass().getName() + ":" + info);
			}
		}
	}

	public void dateInfo(String type, Object object, String info) {
		if (typeSet.contains(type)) {
			long endTime = System.nanoTime();
			System.out.println((endTime - startTime) / 1000000f + ":"
					+ object.getClass().getName() + ":" + info);
			if (isFileLog) {
				log.info((endTime - startTime) / 1000000f + ":"
						+ object.getClass().getName() + ":" + info);
			}
		}
	}

	public void info(LogType logType, String info) {
		if (typeSet.contains(logType.toString())) {
			System.out.println(info);
			if (isFileLog) {
				log.info(info);
			}
		}
	}

	public void info(LogType logType, Object object, String info) {
		if (typeSet.contains(logType.toString())) {
			System.out.println(object.getClass().getName() + ":" + info);
			if (isFileLog) {
				log.info(object.getClass().getName() + ":" + info);
			}
		}
	}

	public void dateInfo(LogType logType, Object object, String info) {
		long endTime = System.nanoTime();
		float time = (endTime - startTime) / 1000000f;
		startTime = endTime;
		if (typeSet.contains(logType.toString())) {
			System.out.println(time + ":" + object.getClass().getName() + ":"
					+ info);
			if (isFileLog) {
				log.info(time + ":" + object.getClass().getName() + ":" + info
						+ "\n");
			}
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

	public static boolean isFileLog() {
		return isFileLog;
	}

	public static void setFileLog(boolean isFileLog) {
		LogManager.isFileLog = isFileLog;
	}

	public static Log getLog() {
		return log;
	}

	public static void setLog(Log log) {
		LogManager.log = log;
	}

}
