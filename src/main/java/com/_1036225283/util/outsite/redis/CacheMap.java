package com._1036225283.util.outsite.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class CacheMap {

	private String mapName;

	public static Jedis jedis = new Jedis("localhost");

	public CacheMap(String mapName) {
		// TODO Auto-generated constructor stub
		this.mapName = mapName;
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "id");
		jedis.hmset(mapName, map);
	}

	/**
	 * 删除map的值key
	 * 
	 * @param key
	 */
	public void remove(String key) {
		jedis.hdel(mapName, key);
	}

	public String get(String key) {
		List<String> list = jedis.hmget(mapName, key);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	/**
	 * 存放key-value
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
		jedis.hset(mapName, key, value);
	}

}
