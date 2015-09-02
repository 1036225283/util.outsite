package com.nitian.util.java;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nitian.util.string.UtilString;

public class UtilJava {

	/**
	 * 不管是string或者int都进行了转换
	 * 
	 * @param object
	 * @return
	 */
	public static String objectToXml2(Object object) {
		Class<?> objectClass = object.getClass();
		Field[] fields = objectClass.getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			String fieldName = fields[i].getName();
			if (existFieldAndMethod(objectClass, fieldName)) {
				try {
					sb.append("<" + fieldName + ">");
					sb.append("<![CDATA[" + field.get(object).toString()
							+ "]]>");
					sb.append("</" + fieldName + ">");
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * string加出来，int/integer不加
	 * 
	 * @param object
	 * @return
	 */
	public static String objectToXml(Object object) {
		Class<?> objectClass = object.getClass();
		Field[] fields = objectClass.getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			String fieldName = fields[i].getName();
			if (existFieldAndMethod(objectClass, fieldName)) {
				try {
					if (field.get(object) != null) {
						if (field.getType().getName()
								.equals("java.lang.Integer")) {
							sb.append("<" + fieldName + ">");
							sb.append(field.get(object).toString());
							sb.append("</" + fieldName + ">");
						} else if (field.getType().getName()
								.equals("java.lang.String")) {
							sb.append("<" + fieldName + ">");
							sb.append("<![CDATA["
									+ field.get(object).toString() + "]]>");
							sb.append("</" + fieldName + ">");
						} else {
							throw new RuntimeException("没有相应的类型");
						}
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * map转换成对象
	 * 
	 * @param map
	 * @return
	 */
	public static Object mapToObject(Map<String, Object> map, Class<?> clasz) {
		Field[] fields = clasz.getDeclaredFields();
		Object instance = null;
		try {
			instance = clasz.newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			String fieldName = fields[i].getName();
			if (existFieldAndMethod(clasz, fieldName)) {
				try {
					if (map.containsKey(fieldName)) {
						fields[i].set(instance, map.get(fieldName));
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return instance;
	}

	/**
	 * 对象转换成map
	 * 
	 * @return
	 */
	public static Map<String, Object> objectToMap(Object object) {
		Map<String, Object> map = new HashMap<String, Object>();
		Class<?> objectClass = object.getClass();
		Field[] fields = objectClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			String fieldName = fields[i].getName();
			if (existFieldAndMethod(objectClass, fieldName)) {
				try {
					if (fields[i].get(object) != null) {
						map.put(fieldName, fields[i].get(object).toString());
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return map;
	}

	/**
	 * 判断某个类中是否有某个方法存在
	 * 
	 * @param c
	 * @param methodName
	 * @return
	 */
	public static boolean existMethod(Class<?> c, String methodName) {
		try {
			c.getDeclaredMethod(methodName);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			return false;
		}
		return true;
	}

	/**
	 * 判断某个类中是否有某个字段存在
	 * 
	 * @param c
	 * @param fieldName
	 * @return
	 */
	public static boolean existField(Class<?> c, String fieldName) {
		try {
			c.getDeclaredField(fieldName);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			return false;
		}
		return true;
	}

	/**
	 * 判断某一个类是否同时存在字段和get方法 name getName()
	 * 
	 * @param c
	 * @param fieldName
	 * @return
	 */
	public static boolean existFieldAndMethod(Class<?> c, String fieldName) {
		String methodName = "get" + UtilString.letterUpper(fieldName);
		boolean field = existField(c, fieldName);
		boolean method = existMethod(c, methodName);
		if (field && method) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 将long int float double等字段转换为字符串
	 * 
	 * @param field
	 * @return
	 */

	public static String objectToString(Field field) {
		String type = field.getType().getName();
		if (type.equals("boolean")) {

		}
		if (type.equals("byte")) {

		}
		if (type.equals("char")) {

		}
		if (type.equals("short")) {

		}
		if (type.equals("int")) {

		}
		if (type.equals("long")) {

		}
		if (type.equals("float")) {

		}
		if (type.equals("double")) {

		}

		return null;
	}

	/**
	 * 创建get方法
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public String createGetters(String name, String type) {
		String value = "";
		String head = name.substring(0, 1);
		String copy = name;
		copy = head.toUpperCase() + copy.substring(1);
		value = value + "\tpublic " + type + " get" + copy;
		value = value + "(){\r\n\t\treturn " + name + ";\r\n\t}\r\n";
		return value;
	}

	/**
	 * 创建set方法
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public String createSetters(String name, String type) {
		String value = "";
		String head = name.substring(0, 1);
		String copy = name;
		copy = head.toUpperCase() + copy.substring(1);
		value = value + "\tpublic void set" + copy + "(";
		value = value + type + " " + name + "){\r\n\t\tthis.";
		value = value + name + " = " + name + ";\r\n\t}\r\n";
		return value;
	}

	/**
	 * 获取一个类中的所有字段--形成一个字符串集合
	 * 
	 * @param c
	 * @return
	 */
	public static List<String> getFieldNames(Class<?> c) {
		List<String> list = new ArrayList<String>();

		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			list.add(fields[i].getName());
		}

		return list;
	}

}
