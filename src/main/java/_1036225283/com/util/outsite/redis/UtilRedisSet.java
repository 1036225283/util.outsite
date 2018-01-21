package _1036225283.com.util.outsite.redis;

import java.util.Set;

import _1036225283.com.util.outsite.json.UtilJson;

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
	 * 获取[1,3,4,5]
	 * 
	 * @param key
	 * @return
	 */
	public static String getJson(String key) {
		Set<String> set = jedis.smembers(key);
		return UtilJson.objectToString(set);
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
