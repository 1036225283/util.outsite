package com._1036225283.util.outsite.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class UtilRequest {

	/**
	 * 提取收到的xml数据转换为map
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, Object> xmlToMap(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		SAXReader saxReader = new SAXReader();

		try {
			InputStream inputStream = request.getInputStream();
			Document document = saxReader.read(inputStream);
			Element root = document.getRootElement();

			@SuppressWarnings("unchecked")
			List<Element> elements = root.elements();
			for (Element element : elements) {
				map.put(element.getName(), element.getText());
			}
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 获取服务器内部的目录
	 * 
	 * @param request
	 * @param directory
	 * @return
	 */
	public static String getPath(HttpServletRequest request, String directory) {
		return request.getSession().getServletContext().getRealPath(directory);
	}

	/**
	 * 获取ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	/**
	 * 获取项目根目录
	 * 
	 * @param request
	 * @return
	 */
	public static String getRootUrl(HttpServletRequest request) {
		String url = request.getScheme() + "://" + request.getServerName()
				+ request.getContextPath();
		return url;
	}

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
