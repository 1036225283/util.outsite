package com.nitian.util.java;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.nitian.util.string.UtilString;

public class UtilJava {

	/**
	 * 对象转换成map
	 * 
	 * @return
	 */
	public static Map<String, String> objectToMap(Object object) {
		Map<String, String> map = new HashMap<String, String>();
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

}
