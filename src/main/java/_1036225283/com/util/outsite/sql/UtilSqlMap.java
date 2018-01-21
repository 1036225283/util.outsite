package _1036225283.com.util.outsite.sql;

import java.util.Map;

/**
 * 进行sql到map的映射
 * 
 * @author 1036225283
 *
 */
public class UtilSqlMap {

	/**
	 * 插入数据
	 * 
	 * @param table
	 * @param map
	 */
	public void insert(String table, Map<String, Object> map) {

	}

	/**
	 * 更新数据
	 * 
	 * @param table
	 * @param map
	 */
	public void update(String table, String sql, Map<String, Object> map) {
		if (sql == null) {
			sql = "update ";
		}

	}

	/**
	 * 删除数据
	 * 
	 * @param table
	 * @param map
	 */
	public void delete(String table, Map<String, Object> map) {

	}

	public void select(String table, Map<String, Object> map) {

	}

	public void selectList(String table, Map<String, Object> map) {

	}
}
