package com.nitian.util.sql;

import java.util.HashMap;
import java.util.Map;

/**
 * 约定：table.insert table.update table.delete table.select table.getList
 * 
 * @author 1036225283
 *
 */
public class SqlFactory {

	public static SqlFactory sqlFactory = new SqlFactory();

	private Map<String, String> sqlMap = new HashMap<String, String>();

	private String username = "admin";

	private String password = "admin123";

	public static SqlFactory getInstance() {
		return sqlFactory;
	}

	/**
	 * 压入sql语句
	 * 
	 * @param key
	 * @param sql
	 */
	public void put(String key, String sql) {
		if (sqlMap.containsKey(key)) {
			throw new RuntimeException("sql key is registered");
		}
		sqlMap.put(key, sql);
	}

	/**
	 * 直接覆盖key-value
	 * 
	 * @param key
	 * @param sql
	 * @param username
	 * @param password
	 */
	public void put(String key, String sql, String username, String password) {
		if (this.username.equals(username) && this.password.equals(password)) {
			sqlMap.put(key, sql);
		} else {
			throw new RuntimeException("username or password is error");
		}
	}

	/**
	 * 获取sql语句
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return sqlMap.get(key);
	}

	/**
	 * 创建insert语句
	 * 
	 * @param table
	 * @param map
	 */
	public void createInsertSql(String table, Map<String, Object> map) {
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ").append(table).append("(");
		String key = "";
		String value = "";
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			key = key + "," + entry.getKey();
			value = value + "," + "?";
		}
		key = key.substring(1);
		value = value.substring(1);
		sb.append(key).append(")values(").append(value).append(")");
		this.put(table + "insert", sb.toString());
	}
}
