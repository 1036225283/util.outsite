package com.nitian.util.redis;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		UtilRedisString.put("user", "xws");
		System.out.println(UtilRedisString.get("user"));
		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "xws");
		map.put("password", "xws123445");
		map.put("age", "24");
		UtilRedisHash.put("user1", map);
		System.out.println(UtilRedisHash.get("user1"));
		
	}
}
