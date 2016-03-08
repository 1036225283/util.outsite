package com.nitian.util.redis;

public class Test {

	public static void main(String[] args) {
		UtilRedisString.put("user", "xws");
		System.out.println(UtilRedisString.get("user"));
	}
}
