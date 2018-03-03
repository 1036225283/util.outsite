package com._1036225283.util.outsite.sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * sql
 * Created by xws on 6/18/17.
 */

public class UtilSql {

    private static Statement statement;
    private static Connection connection;

    /**
     * 获取map，填充key
     *
     * @param resultSet
     * @return
     */
    private static Map<String, Object> getMap(ResultSet resultSet) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int count = resultSetMetaData.getColumnCount();
            count = count + 1;
            for (int i = 1; i < count; i++) {
                // System.out.println(resultSetMetaData.getColumnName(i));
                // System.out.println(resultSetMetaData.getColumnClassName(i));
                // System.out.println(resultSetMetaData.getCatalogName(i));
                // System.out.println(resultSetMetaData.getColumnLabel(i));
                String label = resultSetMetaData.getColumnLabel(i);
                String name = resultSetMetaData.getColumnName(i);
                if (label != null) {
                    map.put(label, null);
                } else {
                    map.put(name, null);
                }

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
    private static void setMap(Map<String, Object> map, ResultSet resultSet) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            try {
                map.put(entry.getKey(), resultSet.getObject(entry.getKey()));
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

    /**
     * 获取表的所有字段
     *
     * @param table
     * @param connection
     * @return
     */
    public static Map<String, Object> getMapByTable(String table, Connection connection) {
        Map<String, Object> map = null;
        try {
            ResultSet resultSet = connection.getMetaData().getColumns(null, null, table, null);
            map = getMap(resultSet);
            // System.out.println(map);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取sql查询结果
     *
     * @param sql
     * @param dbHelper
     * @return
     * @throws SQLException
     */
    public static List<Map<String, Object>> getList(String sql, DBHelper dbHelper) throws SQLException {
        Statement statement = dbHelper.getConnection().createStatement();

        long start = System.nanoTime();

        ResultSet resultSet = statement.executeQuery(sql);// 执行语句，得到结果集
        long end = System.nanoTime();
        System.out.println("毫秒：" + (end - start) / 1000 / 1000 + "，微秒：" + (end - start) / 1000);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        while (resultSet.next()) {
            Map<String, Object> map = UtilSql.resultSetToMap(resultSet);
            list.add(map);
            // System.out.println(map);
        }
        return list;
    }

    public static Map<String, Object> getUnique(String sql, DBHelper dbHelper) throws SQLException {
        Statement statement = dbHelper.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);// 执行语句，得到结果集

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        while (resultSet.next()) {
            Map<String, Object> map = UtilSql.resultSetToMap(resultSet);
            list.add(map);
        }
        if (list.size() == 0) {
            return null;
        } else if (list.size() == 1) {
            return list.get(0);
        } else {
            throw new RuntimeException("查询的数据不唯一");
        }
    }

    public static boolean execute(String sql, DBHelper dbHelper) throws SQLException {
        Statement statement = dbHelper.getConnection().createStatement();
        boolean result = statement.execute(sql);// 执行语句，得到结果集
        return result;
    }

    public static boolean setAutoCommit(boolean value) throws SQLException {

        // if (statement == null) {
        // statement = dbHelper.getConnection().createStatement();
        // }
        // boolean result = statement.execute(sql);// 执行语句，得到结果集
        // return result;
        return false;
    }

    // conn.setAutoCommit(false);

}