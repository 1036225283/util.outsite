package com.nitian.util.result;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class UtilResult {

	/**
	 * 封装返回结果
	 * 
	 * @param result
	 * @param message
	 * @return
	 */
	public static String returnResult(Boolean result, String message) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject json = new JSONObject();
		resultMap.put("result", result);
		resultMap.put("message", message);
		json.put("root", resultMap);
		return json.toString();
	}
}
