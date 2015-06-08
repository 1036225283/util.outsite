package com.nitian.util.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

public class UtilRequest {

	/**
	 * 获取访问url
	 * 
	 * @param request
	 * @return
	 */
	public static String getUrl(HttpServletRequest request) {
		String url = request.getScheme() + "://" + request.getServerName()
				+ request.getContextPath() + request.getServletPath();
		return url;
	}

	/**
	 * 获取字符串
	 * 
	 * @return
	 */
	public static String getString(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 读取request.getReader()的值
	 * 
	 * @param reader
	 * @return
	 */
	public static String getReader(BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(reader);
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
