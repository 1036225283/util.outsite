package com.nitian.util.redis;

import java.util.Map;

import redis.clients.jedis.Jedis;

public class UtilRedisHash {

	public static Jedis jedis = new Jedis("localhost");

	/**
	 * 获取值
	 * 
	 * @param key
	 * @return
	 */
	public static Map<String, String> get(String key) {
		Map<String, String> map = jedis.hgetAll(key);
		return map;
	}

	/**
	 * 存放值
	 * 
	 * @param key
	 * @param map
	 */
	public static void put(String key, Map<String, String> map) {
		jedis.hmset(key, map);
		// for (Map.Entry<String, String> entry : map.entrySet()) {
		// jedis.hset(key, entry.getKey(), entry.getValue());
		//
		// }
	}

}
