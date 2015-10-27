package com.nitian.util.random;

import java.util.UUID;

public class UtilRandom {

	/**
	 * 创建uuid
	 * 
	 * @return
	 */
	public static String createUUID() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");
		return uuid;
	}

	/**
	 * 创建随机数
	 * 
	 * @param count
	 * @return
	 */
	public static String createVerify(Integer count) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < count; i++) {
			sb.append((int) (Math.random() * 10));
		}
		return sb.toString();
	}

	/**
	 * 创建随机0-f的字符表示,此十六进制字符可直接转换为字节数组
	 * 
	 * @param count
	 * @return
	 */
	public static String createHexString(Integer count) {
		String value = "0123456789abcdef";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < count; i++) {
			sb.append(0);
			int index = (int) (Math.random() * 16);
			sb.append(value.charAt(index));
		}
		return sb.toString();
	}

	/**
	 * 创建字符型字符串
	 * 
	 * @param count
	 * @return
	 */
	public static String createCharString(Integer count) {
		String value = "0123456789abcdefghijklmnopqrstuvwxzy";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < count; i++) {
			int index = (int) (Math.random() * 36);
			sb.append(value.charAt(index));
		}
		return sb.toString();
	}

}
