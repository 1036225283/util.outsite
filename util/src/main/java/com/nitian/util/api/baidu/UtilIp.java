package com.nitian.util.api.baidu;

import org.apache.commons.lang.StringEscapeUtils;

import com.nitian.util.http.UtilHttp;
import com.nitian.util.json.JsonStringToObject;

public class UtilIp {

	private static String url = "http://api.map.baidu.com/location/ip?ak="
			+ Ak.ak;

	public static String getLocation(String ip) {
		url = url + "&coor=bd09ll";
		url = url + "&ip=" + ip;
		String result = UtilHttp.get(url);
		result = StringEscapeUtils.unescapeJava(result);
		return result;
	}

	public static void main(String[] args) {
		String s = getLocation("223.99.217.3");
		JsonStringToObject jsonStringToObject = new JsonStringToObject();
		jsonStringToObject.goString(s);
		String city = jsonStringToObject.get("content.address_detail.city");
		String province = jsonStringToObject
				.get("content.address_detail.province");
		System.out.println(province + " " + city);
		System.out.println(s);
		// UtilGeocoding.geocoderByLocation("36.10521490,120.38442818");
		// System.out.println(a);
	}
}
