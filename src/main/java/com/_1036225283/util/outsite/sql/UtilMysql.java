package com._1036225283.util.outsite.sql;

import com._1036225283.util.self.time.TimeString;
import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UtilMysql {

	private Connection connection;

	public static ResultSetMetaData resultSetMetaData;
	public static DatabaseMetaData databaseMetaData;
	public static ResultSet tableSet;
	public static ResultSet fieldSet;

	public static void main(String[] args) {
		UtilMysql mysql = new UtilMysql();
		mysql.getConnect();
		UtilSql.getMapByTable("test", mysql.getConnect());
		// mysql.getHospitalList();
		// mysql.getList();

	}

	/**
	 * 获取connect
	 * 
	 * @throws Exception
	 */
	public Connection getConnect() {
		DBConfig config = new DBConfig();

		config.setUrl("jdbc:mysql://localhost:3306/test");
		config.setUsername("root");
		config.setPassword("root");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		}

		try {
			connection = (Connection) DriverManager
					.getConnection(config.getUrl(), config.getUsername(),
							config.getPassword());
		} catch (SQLException se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		return connection;
	}

	public List<Map<String, Object>> getHospitalList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from hospital  where client_id = '56d561f4203cf444cbff627a'";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Map<String, Object> map = UtilSql.resultSetToMap(resultSet);
				list.add(map);
				System.out.println(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("size  : " + list.size());
		return list;
	}

	public String getList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		String sql = "select h.area_code, h.hospital_id,h.hospital_name, count(DISTINCT p.pres_code) pres_cnt,count( DISTINCT IF(recheck_score>0,p.pres_code,NULL)) risk_cnt "
				+ "from kmrcs.hospital h left join prescription p on p.hospital_id=h.hospital_id "
				+ "where (p.date_created>=? and p.date_created<?) or p.pres_code is null and h.hospital_id not in ('56a5aa6c5a6ff444e074d632','56a5ab195a6ff444e474d632','56a5abd05a6ff444e674d632','56a5aea45a6ff444e874d632','5665814081802cadc80dc6a1','566aae788f86b0db80c98aa1','56a5aad05a6ff444e274d632','53102fb9bf1044ed8b0ba36d','566ab045cc45b0db454f220e','56975a1766bdf444f9f42dfb','566ab084cc45b0db494f220e','55e3bb252342b0fe95bb68c6','56a9c9ab66bdf44489f52dfb','5672ad7d0735b0db1372f075','567be6e34ad0f4443ab1efad','568f65eb4ad0f4444e901c5c','566aad928f86b0db76c98aa1','568f65eb4ad0f4444e901c5c','565ede485c7bda7d4ba98672') "
				+ "group by h.hospital_id order by h.area_code desc,pres_cnt desc";

		System.out.println(new Date().getTime());
		TimeString timeString = new TimeString(new Date());
		timeString.setHour("23");
		timeString.setMinute("59");
		timeString.setSecond("59");

		String endDate = timeString.toString();
		timeString.setHour("00");
		timeString.setMinute("00");
		timeString.setSecond("00");
		String startDate = timeString.toString();

		System.out.println(startDate);

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, startDate);
			statement.setString(2, endDate);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Map<String, Object> map = UtilSql.resultSetToMap(resultSet);
				list.add(map);
				System.out.println(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	public Map<String, Object> update(String table, Map<String, Object> map) {
		return null;
	}

	public Map<String, Object> insert(String table, Map<String, Object> map) {

		List<Object> params = new ArrayList<Object>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			params.add(entry.getValue());
		}

		return null;
	}

	public void setPreparedStatement(PreparedStatement preparedStatement,
			List<Object> params) {
		for (int i = 0; i < params.size(); i++) {
			try {
				preparedStatement.setObject(i, params.get(i));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setConnect(Connection connect) {
		this.connection = connect;
	}

}
