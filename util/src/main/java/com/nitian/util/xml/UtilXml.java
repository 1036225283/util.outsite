package com.nitian.util.xml;

import com.thoughtworks.xstream.XStream;

public class UtilXml {

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
