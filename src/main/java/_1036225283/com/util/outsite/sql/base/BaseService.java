package _1036225283.com.util.outsite.sql.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import _1036225283.com.util.outsite.sql.DBConfig;

public class BaseService {

	private Connection connection;

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
}
