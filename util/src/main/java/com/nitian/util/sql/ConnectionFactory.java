package com.nitian.util.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ConnectionFactory {

	private Set<Connection> connections = new HashSet<Connection>();

	/**
	 * 初始化连接池
	 * 
	 * @param size
	 * @return
	 */
	public ConnectionFactory init(int size) {
		for (int i = 0; i < size; i++) {
			connections.add(initConnect());
		}
		return this;
	}

	/**
	 * 初始化数据库连接
	 * 
	 * @return
	 */
	private Connection initConnect() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		}

		try {
			connection = (Connection) DriverManager.getConnection("", "", "");
		} catch (SQLException se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		return connection;
	}

	/**
	 * 获取连接
	 * 
	 * @return
	 */
	public Connection getConnection() {
		Connection connection = connections.iterator().next();
		connections.remove(connection);
		return connection;
	}

	/**
	 * 释放连接
	 * 
	 * @param connection
	 */
	public void release(Connection connection) {
		this.connections.add(connection);
	}
}
