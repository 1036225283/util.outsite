package com.nitian.util.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UtilJson {

	public static void main(String[] args) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("duck", "duck");
			map.put("luck", "luck");
			list.add(map);
		}
		String s = UtilJson.objectToString(list);
		System.out.println(s);
	}

	/**
	 * string 转 object
	 * 
	 * @param json
	 * @param calssName
	 * @return
	 */
	public static Object stringToObject(String string, Class<?> entityClass) {
		JSONObject jsonObject = JSONObject.fromObject(string);
		return JSONObject.toBean(jsonObject, entityClass);
	}

	/**
	 * 
	 * @param string
	 * @param entityClass
	 * @return
	 */
	public static String[] stringToArray(String string, Class<?> entityClass) {
		JSONArray jsonArray = JSONArray.fromObject(string);
		Object[] objects = jsonArray.toArray();
		String[] strings = new String[objects.length];
		for (int i = 0; i < objects.length; i++) {
			strings[i] = objects[i].toString();
		}
		return strings;
	}

	/**
	 * object 转 string
	 * 
	 * @param object
	 * @return
	 */
	public static String objectToString(Object object) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("object", object);
		return jsonObject.getString("object");
	}

	/**
	 * json-->array
	 * 
	 * @param string
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> stringToList(String string, Class<?> clazz) {
		if (string == null) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		JSONArray array = JSONArray.fromObject(string);
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			list.add((T) JSONObject.toBean(jsonObject, clazz));
		}
		return list;
	}

}
