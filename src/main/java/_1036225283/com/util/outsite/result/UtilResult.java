package _1036225283.com.util.outsite.result;

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
		json.putAll(resultMap);
		return json.toString();
	}

	/**
	 * 封装返回结果
	 * 
	 * @param result
	 * @param object
	 * @return
	 */
	public static String returnResult(Boolean result, Object object) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject json = new JSONObject();
		resultMap.put("result", result);
		resultMap.put("message", object);
		json.putAll(resultMap);
		return json.toString();
	}
}
