package com._1036225283.util.outsite.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com._1036225283.util.outsite.json.UtilJson;
import com._1036225283.util.self.log.LogManager;

public class Test {

	public static LogManager logManager = LogManager.getInstance();

	public static void test() {
		UtilRedisString.put("testString", "xws");
		System.out.println(UtilRedisString.get("testString"));

		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "xws");
		map.put("password", "xws123445");
		map.put("age", "24");
		UtilRedisHash.put("testHash", map);
		System.out.println(UtilRedisHash.get("testHash"));

		UtilRedisSet.put("testSet", "testSet1", "testSet2");
		System.out.println(UtilRedisSet.get("testSet"));

		String[] ss = new String[3];
		ss[0] = "sd";
		ss[1] = "2";
		ss[2] = "3";
		String ssss = UtilJson.objectToString(ss);
		String[] d = (String[]) UtilJson.stringToArray(ssss, String[].class);
		System.out.println(d);

		Set<String> set = UtilRedis.getKey(null);
		System.out.println("set.size() = " + set.size());

		// 获取部分key
		set = UtilRedis.getKey("ZDXX*");
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
			// System.out.println(UtilRedisHash.get(it.next()));
		}
		// System.out.println("set.size() = " + set.size());

	}

	public static void speed() {
		logManager.dateInfo("start");
		for (int i = 0; i < 10000; i++) {
			UtilRedisString.put("key" + i, "value" + i);
		}
		logManager.dateInfo("end");
		// 887.70125
	}

	public static void speed2() {
		logManager.dateInfo("start");
		for (int i = 0; i < 10000; i++) {
			UtilRedisString.get("key" + i);
		}
		logManager.dateInfo("end");
	}

	/**
	 * 
	1.24212584E8		:			:	start
	846.98456		:			:	end
	0.124395		:			:	start
	727.4825		:			:	end
	 * @param args
	 */
	public static void main(String[] args) {
		speed();
		speed2();
	}
}
