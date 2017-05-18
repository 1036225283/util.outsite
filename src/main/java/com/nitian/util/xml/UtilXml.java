package com.nitian.util.xml;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

public class UtilXml {

	/**
	 * 将xml文件转换为map
	 * 
	 * @param xml
	 * @return
	 */
	public static Map<String, Object> xmlToMap(String xml) {
		Map<String, Object> map = new HashMap<String, Object>();
		SAXReader saxReader = new SAXReader();
		Document document;
		try {
			document = (Document) saxReader.read(new ByteArrayInputStream(xml
					.getBytes("utf-8")));
			Element root = document.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> elements = root.elements();
			for (Element element : elements) {
				map.put(element.getName(), element.getText());
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 将对象转换为xml字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String objectToXml(Object object) {
		XStream xStream = new XStream();
		xStream.alias("xml", object.getClass());
		return xStream.toXML(object);
	}
}
