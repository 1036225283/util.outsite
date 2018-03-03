package com._1036225283.util.outsite.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class UtilRedis {

	public static Jedis jedis = new Jedis("localhost");

	/**
	 * 获取所有的key||或者正则表达key
	 * 
	 * @param string
	 * @return
	 */
	public static Set<String> getKey(String string) {
		Set<String> set = null;
		if (string == null) {
			set = jedis.keys("*");
		} else {
			set = jedis.keys(string);
		}
		return set;
	}

	/**
	 * 根据key获取值
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		return jedis.get(key);
	}
}
