package com.nitian.util.http;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

public class UtilHttp {

	/**
	 * get
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		StringBuffer sb = new StringBuffer();
		try {
			URL urlObject = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlObject
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(new String(line.getBytes(), "UTF-8"));
			}
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return sb.toString();
	}

	/**
	 * 没有session的post
	 * 
	 * @param Url
	 * @param value
	 * @return
	 */
	public static String noSessionPost(String Url, String value) {
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL(Url);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
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
			while ((line = reader.readLine()) != null) {
				sb.append(new String(line.getBytes(), "UTF-8"));
			}
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return sb.toString();
	}

	/**
	 * 上传图片
	 * 
	 * @param url
	 * @param accessToken
	 * @param filePath
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String uploadImage(String url, String filePath, String data) {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		post.setRequestHeader(
				"User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:30.0) Gecko/20100101 Firefox/30.0");
		post.setRequestHeader("Host", "file.api.weixin.qq.com");
		post.setRequestHeader("Connection", "Keep-Alive");
		post.setRequestHeader("Cache-Control", "no-cache");
		String result = null;
		try {
			if (filePath != null) {
				File file = new File(filePath);
				FilePart filepart = new FilePart("media", file, "image/jpeg",
						"UTF-8");
				Part[] parts = new Part[] { filepart };
				MultipartRequestEntity entity = new MultipartRequestEntity(
						parts, post.getParams());
				post.setRequestEntity(entity);
			}
			if (data != null) {
				post.setRequestBody(data);
			}
			int status = client.executeMethod(post);
			if (status == HttpStatus.SC_OK) {
				String responseContent = post.getResponseBodyAsString();
				JSONObject jsonObject = JSONObject.fromObject(responseContent);
				System.out.println(responseContent);
				if (jsonObject.get("errcode") == null) {
					result = jsonObject.getString("media_id");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 上传
	 * 
	 * @param url
	 * @param filePath
	 * @return
	 */
	public static String upLoad(String url, String filePath, String value) {

		String result = null;
		BufferedReader reader = null;

		try {
			File file = new File(filePath);
			if (!file.exists() || !file.isFile()) {
				throw new IOException("文件不存在");
			}

			/**
			 * 第一部分
			 */
			URL urlObj = new URL(url);
			// 连接
			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

			/**
			 * 设置关键值
			 */
			con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false); // post方式不能使用缓存

			// 设置请求头信息
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");

			// 设置边界
			String BOUNDARY = "----------" + System.currentTimeMillis();
			con.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);

			// 请求正文信息

			// 第一部分：
			StringBuilder sb = new StringBuilder();
			sb.append("--"); // 必须多两道线
			sb.append(BOUNDARY);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
					+ file.getName() + "\"\r\n");
			sb.append("Content-Type:application/octet-stream\r\n\r\n");

			byte[] head = sb.toString().getBytes("utf-8");

			// 获得输出流
			OutputStream out = new DataOutputStream(con.getOutputStream());
			// 输出表头
			out.write(head);

			if (value != null) {
				StringBuffer sb2 = new StringBuffer();
				sb2.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
				sb2.append("Content-Disposition: form-data; name=\"" + "duck"
						+ "\"\r\n\r\n");
				sb2.append(value);
				out.write(sb2.toString().getBytes());
			}

			// 文件正文部分
			// 把文件已流文件的方式 推入到url中
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			in.close();

			// 结尾部分
			byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

			out.write(foot);

			out.flush();
			out.close();

			StringBuffer buffer = new StringBuffer();

			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return result;
	}

	/**
	 * 下载文件
	 * 
	 * @param Url
	 * @param filePath
	 */
	@SuppressWarnings("resource")
	public static void downLoad(String url, String filePath) {
		try {
			URL thisUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) thisUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.connect();
			// if (value != null && !value.trim().equals("")) {
			// byte[] bypes = value.getBytes("utf-8");
			// OutputStream outputStream = connection.getOutputStream();// 输入参数
			// outputStream.write(bypes);
			// outputStream.flush();
			// outputStream.close();
			// }

			InputStream inputStreamReader = connection.getInputStream();
			FileOutputStream fileOutputStream = new FileOutputStream(filePath);
			byte[] buffer = new byte[1204];
			int byteread = 0;
			while ((byteread = inputStreamReader.read(buffer)) != -1) {
				fileOutputStream.write(buffer, 0, byteread);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param url
	 * @param data
	 * @param filePath
	 * @return
	 */
	public static void download(String url, String data, String filePath) {
		try {
			URL thisUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) thisUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.connect();
			if (data != null && !data.trim().equals("")) {
				byte[] bypes = data.getBytes("utf-8");
				OutputStream outputStream = connection.getOutputStream();// 输入参数
				outputStream.write(bypes);
				outputStream.flush();
				outputStream.close();
			}

			InputStream inputStreamReader = connection.getInputStream();
			@SuppressWarnings("resource")
			FileOutputStream fileOutputStream = new FileOutputStream(filePath);
			byte[] buffer = new byte[1204];
			int byteread = 0;
			while ((byteread = inputStreamReader.read(buffer)) != -1) {
				fileOutputStream.write(buffer, 0, byteread);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
