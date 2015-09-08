package com.nitian.util.json;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 将java对象装换为JSON对象{"hello":"hello"}
 * 
 * @author 1036225283 首先，将一个个需要转换的对象压入map中去 第二，迭代map中的数据
 * 
 * 
 */
public class ObjectToJsonString {

	private Map<String, Object> maps = new HashMap<String, Object>();

	private StringBuffer sb = new StringBuffer();

	/**
	 * 将对象压入map中
	 */
	public void put(String key, Object value) {
		maps.put(key, value);
	}

	/**
	 * 判断一个数据是否是java基本数据类型，Long,Integer, Float,Double, Boolean,Character,
	 * String,Byte,Short
	 * 
	 * @param
	 * @return true or false
	 */
	private boolean isBasicDataType(Object o) {
		if (o instanceof Integer || o instanceof Long || o instanceof Float
				|| o instanceof Double || o instanceof Boolean
				|| o instanceof Byte || o instanceof Short) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断一个数据是否为string或者date
	 * 
	 * @param object
	 * @return
	 */
	private boolean isStringOrDate(Object object) {
		if (object instanceof String || object instanceof Date) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断一个数据是否为空
	 * 
	 * @return
	 */
	private boolean isNull(Object o) {
		if (o == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断一个数据是否是java List类型
	 * 
	 * @return
	 */
	private boolean isListDataType(Object o) {
		if (o instanceof List<?>) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断一个数据是否是java Map类型
	 * 
	 * @param o
	 * @return
	 */
	private boolean isMapDataType(Object o) {
		if (o instanceof Map<?, ?>) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断并控制数据的解析
	 */
	private void judgeControl(Object object) {
		if (isBasicDataType(object)) {
			iterateBasic(object);
		} else if (isListDataType(object)) {
			iterateList((List<?>) object);
		} else if (isMapDataType(object)) {
			iterateMap((Map<?, ?>) object);
		} else if (isStringOrDate(object)) {
			iterateStringOrDate(object);
		} else if (isNull(object)) {
			iterateNull(object);
		} else {
			try {
				iteratePojo(object);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 迭代string和Date
	 * 
	 * @param object
	 */
	private void iterateStringOrDate(Object object) {
		sb.append("\"");
		sb.append(object);
		sb.append("\"");
	}

	/**
	 * 迭代空型数据
	 */
	private void iterateNull(Object object) {
		sb.append(object);
	}

	/**
	 * 迭代基本数据
	 */
	private void iterateBasic(Object object) {
		sb.append(object);
	}

	/**
	 * 迭代map
	 */
	private void iterateMap(Map<?, ?> map) {
		sb.append("{");
		Boolean nextBoolean = false;
		for (Entry<?, ?> entry : map.entrySet()) {
			if (nextBoolean == false) {
				nextBoolean = true;
			} else {
				sb.append(",");
			}
			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\"");
			sb.append(":");
			judgeControl(entry.getValue());
		}
		sb.append("}");
	}

	/**
	 * 迭代list集合
	 * 
	 * @param list
	 */
	private void iterateList(List<?> list) {
		sb.append("[");
		Boolean nextBoolean = false;
		for (int i = 0; i < list.size(); i++) {
			if (nextBoolean == false) {
				nextBoolean = true;
			} else {
				sb.append(",");
			}
			judgeControl(list.get(i));
		}
		sb.append("]");
	}

	/**
	 * 迭代自定义java对象
	 * 
	 * @throws Exception
	 */
	private void iteratePojo(Object pojo) throws Exception {
		sb.append("{");
		Boolean nextBoolean = false;
		Class<?> c = pojo.getClass();
		Field[] field = c.getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			field[i].setAccessible(true);
			Object object = field[i].get(pojo);
			if (nextBoolean == false) {
				nextBoolean = true;
			} else {
				sb.append(",");
			}
			sb.append("\"");
			sb.append(field[i].getName());
			sb.append("\"");
			sb.append(":");
			if (object == null) {
				if (field[i].getType().toString().equals(
						"interface java.util.List")) {
					sb.append("[]");
				} else if (field[i].getType().toString().equals(
						"interface java.util.Map")) {
					sb.append("{}");
				} else if (field[i].getType().toString().equals(
						"class java.lang.String")) {
					sb.append("\"\"");
				} else {
					sb.append("null");
				}
			} else {
				judgeControl(object);
			}

		}
		sb.append("}");
	}

	public String toString() {
		judgeControl(maps);
		return sb.toString();
	}
}
