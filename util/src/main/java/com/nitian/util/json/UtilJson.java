package com.nitian.util.json;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UtilJson {

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
