package _1036225283.com.util.outsite.redis;

import java.util.Map;

import redis.clients.jedis.Jedis;

public class UtilRedisString {

	public static String JEDIS_URL = "localhost";

	public static Jedis jedis = new Jedis(JEDIS_URL);

	public static void put(String key, String value) {
		jedis.set(key, value);
	}

	public static String get(String key) {
		return jedis.get(key);
	}

	public static void remove(String key) {
		jedis.del(key);
	}

	/**
	 * 关于map的操作
	 * 
	 * @author 1036225283
	 *
	 */
	static class Maps {
		/**
		 * 缓存map
		 * 
		 * @param key
		 * @param map
		 */
		public static void put(String key, Map<String, String> map) {
			jedis.hmset(key, map);
		}

		public static String get(String key) {
			return null;
		}
	}

}
