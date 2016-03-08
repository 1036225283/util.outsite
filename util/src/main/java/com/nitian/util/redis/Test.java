package com.nitian.util.redis;

import java.util.HashMap;
import java.util.Map;

import com.nitian.util.json.UtilJson;

public class Test {

	public static void main(String[] args) {
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
	}
}
