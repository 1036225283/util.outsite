package com.nitian.util.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * db help
 * Created by xws on 6/18/17.
 */

public class DBHelper {

    public String url = "jdbc:mysql://www.1036225283.com/xws?zeroDateTimeBehavior=convertToNull&Unicode=true&amp;characterEncoding=utf8";
    public String name = "com.mysql.jdbc.Driver";
    public String user = "duck";
    public String password = "duck";
    private Connection conn = null;

    public DBHelper() {

    }

    public void close() {
        System.out.println("Close connection...");
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName(name);// 指定连接类型
                conn = DriverManager.getConnection(url, user, password);// 获取连接
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}