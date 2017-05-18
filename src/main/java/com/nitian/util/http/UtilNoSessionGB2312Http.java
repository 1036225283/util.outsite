package com.nitian.util.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UtilNoSessionGB2312Http {

	public String POST(String Url, String value) throws Exception {
		URL url = new URL(Url);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		connection.setRequestProperty("Accept-Charset", "GB2312");
		connection.connect();
		if (value != null && !value.trim().equals("")) {
			byte[] bypes = value.getBytes("utf-8");
			OutputStream outputStream = connection.getOutputStream();// 输入参数
			outputStream.write(bypes);
			outputStream.flush();
			outputStream.close();
		}

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
		URL url = new URL(Url);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("GET");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		connection.connect();
		if (value != null && !value.trim().equals("")) {
			byte[] bypes = value.getBytes();
			OutputStream outputStream = connection.getOutputStream();// 输入参数
			outputStream.write(bypes);
			outputStream.flush();
			outputStream.close();
		}
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
