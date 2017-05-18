package com.nitian.util.api.baidu;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang.StringEscapeUtils;

import com.nitian.util.http.UtilHttp;

/**
 * grcoding
 * 
 * @author 1036225283
 *
 */
public class UtilGeocoding {

	private static String geocoder_url = "http://api.map.baidu.com/geocoder/v2/?output=json&ak="
			+ Ak.ak + "&";

	/**
	 * 根据地址查坐标
	 * 
	 * @param address
	 */
	public static String geocoderByAddress(String address) {

		try {
			address = URLEncoder.encode(address, "utf8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = geocoder_url + "address=" + address;
		String result = UtilHttp.get(url);
		result = StringEscapeUtils.unescapeJava(result);
		return result;
	}

	/**
	 * 根据坐标获取地址
	 * 
	 * @param location
	 */
	public static String geocoderByLocation(String location) {

		try {
			location = URLEncoder.encode(location, "utf8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = geocoder_url + "location=" + location;

		String result = UtilHttp.get(url);
		result = StringEscapeUtils.unescapeJava(result);
		return result;
	}

	public static void main(String[] args) {
		String s = geocoderByAddress("青岛 ");
		System.out.println(s);
		String a = geocoderByLocation("36.112518,120.435807");
		System.out.println(a);
	}

}
