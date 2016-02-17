package com.nitian.util.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UtilSql {

	/**
	 * 获取map，填充key
	 * 
	 * @param resultSet
	 * @return
	 */
	public static Map<String, Object> getMap(ResultSet resultSet) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int count = resultSetMetaData.getColumnCount();
			count = count + 1;
			for (int i = 1; i < count; i++) {
				map.put(resultSetMetaData.getColumnName(i), null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 传入map，填入value
	 * 
	 * @param map
	 * @param resultSet
	 */
	public static void setMap(Map<String, Object> map, ResultSet resultSet) {
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			try {
				map.put(entry.getKey(), resultSet.getString(entry.getKey()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * resultSet转换为map
	 * 
	 * @param resultSet
	 * @return
	 */
	public static Map<String, Object> resultSetToMap(ResultSet resultSet) {
		Map<String, Object> map = getMap(resultSet);
		setMap(map, resultSet);
		return map;
	}

}
