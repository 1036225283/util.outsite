package com.nitian.util.random;

import java.util.UUID;

public class UtilRandom {

	/**
	 * 创建uuid
	 * 
	 * @return
	 */
	public static String createUUID() {
		return UUID.randomUUID().toString();
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

}
