package com.nitian.util.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UtilNoSessionHttp {

	public String POST(String Url, String value) throws Exception {
		/**
		 * 新建连接，打开连接
		 * */
		URL url = new URL(Url);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		/**
		 * 设置输入输出流
		 * */
		connection.setDoOutput(true);
		connection.setDoInput(true);
		/**
		 * 设置POST方式
		 */
		connection.setRequestMethod("GET");
		/**
		 * 设置是否可以使用缓存
		 */
		connection.setUseCaches(false);
		/**
		 * 设置是否可以获取头域
		 */
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		/**
		 * 设置块大小
		 * */
		// connection.setChunkedStreamingMode(50);
		// System.out.println(connection.getRequestProperties());
		connection.connect();
		/**
		 * 给post请求设值
		 */
		if (value != null && !value.trim().equals("")) {
			byte[] bypes = value.getBytes();
			OutputStream outputStream = connection.getOutputStream();// 输入参数
			outputStream.write(bypes);
			outputStream.flush();
			outputStream.close();
		}

		// System.out.println(connection.getHeaderFields());

		/**
		 * 输入流
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String line;
		StringBuffer sb = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		return sb.toString();
	}

	public String GET(String Url, String value) throws Exception {
		/**
		 * 新建连接，打开连接
		 * */
		URL url = new URL(Url);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		/**
		 * 设置输入输出流
		 * */
		connection.setDoOutput(true);
		connection.setDoInput(true);
		/**
		 * 设置GET方式
		 */
		connection.setRequestMethod("GET");
		/**
		 * 设置是否可以使用缓存
		 */
		connection.setUseCaches(false);
		/**
		 * 设置是否可以获取头域
		 */
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		/**
		 * 设置块大小
		 * */
		// connection.setChunkedStreamingMode(50);
		// System.out.println(connection.getRequestProperties());
		connection.connect();
		/**
		 * 给post请求设值
		 */
		if (value != null && !value.trim().equals("")) {
			byte[] bypes = value.getBytes();
			OutputStream outputStream = connection.getOutputStream();// 输入参数
			outputStream.write(bypes);
			outputStream.flush();
			outputStream.close();
		}

		// System.out.println(connection.getHeaderFields());

		/**
		 * 输入流
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String line;
		StringBuffer sb = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		return sb.toString();
	}

}
