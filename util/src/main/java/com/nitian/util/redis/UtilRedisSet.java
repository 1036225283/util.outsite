package com.nitian.util.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class UtilRedisSet {

	public static Jedis jedis = new Jedis("localhost");

	/**
	 * 获取值
	 * 
	 * @param key
	 * @return
	 */
	public static Set<String> get(String key) {
		Set<String> set = jedis.smembers(key);
		return set;
	}

	/**
	 * 存放值
	 * 
	 * @param key
	 * @param map
	 */
	public static void put(String key, String... value) {
		jedis.sadd(key, value);
	}

}
