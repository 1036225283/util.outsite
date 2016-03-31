package com.nitian.util.log;

import java.util.HashSet;
import java.util.Set;

/**
 * 日志管理
 * 
 * @author 1036225283
 *
 */
public class LogManage {

	private static LogManage logManage = new LogManage();

	public static LogManage getInstance() {
		return logManage;
	}

	private LogManage() {
		// TODO Auto-generated constructor stub
	}

	private Set<String> typeSet = new HashSet<String>();

	/**
	 * 根据类型输出日志
	 * 
	 * @param type
	 * @param info
	 */
	public void info(String type, String info) {
		if (typeSet.contains(type)) {
			System.out.println(info);
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
