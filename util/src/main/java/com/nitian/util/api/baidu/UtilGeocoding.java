package com.nitian.util.api.baidu;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import com.nitian.util.http.UtilHttp;

/**
 * grcoding
 * 
 * @author 1036225283
 *
 */
public class UtilGeocoding {

	private static String ak = "5033f8777254d8e7844d04a0602655f7";

	private static String geocoder_url = "http://api.map.baidu.com/geocoder/v2/?output=json&ak="
			+ ak + "&";

	/**
	 * 根据地址查坐标
	 * @param address
	 */
	public static void geocoderByAddress(String address) {

		try {
			address = URLEncoder.encode(address, "utf8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		geocoder_url = geocoder_url + "address=" + address;

		String s = UtilHttp.get(geocoder_url);
		try {
			s = URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(s);
	}
	
	
	/**
	 * 根据坐标获取地址
	 * @param location
	 */
	public static void geocoderByLocation(String location) {

		try {
			location = URLEncoder.encode(location, "utf8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		geocoder_url = geocoder_url + "location=" + location;

		String s = UtilHttp.get(geocoder_url);
		try {
			s = URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(s);
	}

	public static void main(String[] args) {
//		geocoderByAddress("青岛 ");
		geocoderByLocation("36.112518,120.435807");
	}

}
