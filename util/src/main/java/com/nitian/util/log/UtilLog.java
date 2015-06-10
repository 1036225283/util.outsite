package com.nitian.util.log;

import org.slf4j.Logger;

/**
 * 日志管理
 * 
 * @author 1036225283
 *
 */
public class UtilLog {

	/**
	 * 日志
	 * 
	 * @param title
	 * @param content
	 * @param logger
	 */
	public static void LogInfo(String title, String content, Logger logger) {
		logger.info(title + "<<<<<<<<<<<<<<<<<<<<");
		logger.info(content);
		logger.info(title + ">>>>>>>>>>>>>>>>>>>>");

	}
}
