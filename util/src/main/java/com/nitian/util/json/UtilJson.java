package com.nitian.util.json;

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
}
