package com.nitian.util.sql;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nitian.util.time.TimeString;

public class UtilSqlBackup {
	private static String str = null;

	public static void main(String[] args) {
		backupAll();
	}

	public static void backupAll() {
		TimeString timeString = new TimeString(new Date());
		String date = timeString.getYear() + timeString.getMonth()
				+ timeString.getDay();
		String backupPath = "F:\\sql\\sql\\";

		String mysqlBinPath = "G:\\software\\MYSQL\\HOME\\bin\\";
		String basMysql = mysqlBinPath
				+ "mysqldump -h182.254.132.60 -uduck -pduck  franchisee>  "
				+ backupPath + date + "franchisee.sql";
		String weixinMysql = mysqlBinPath
				+ "mysqldump -h182.254.131.51 -uduck -pduck  weixin>  "
				+ backupPath + date + "weixin.sql";
		String yishiMysql = mysqlBinPath
				+ "mysqldump -h182.254.131.51 -uduck -pduck  yishi>  "
				+ backupPath + date + "yishi.sql";
		String ruleMysql = mysqlBinPath
				+ "mysqldump -h182.254.131.51 -uduck -pduck  rule>  "
				+ backupPath + date + "rule.sql";

		List<String> list = new ArrayList<String>();
		list.add(basMysql);
		list.add(weixinMysql);
		list.add(yishiMysql);
		list.add(ruleMysql);

		for (String string : list) {
			backup(string);
		}

	}

	public static void backup(String cmd) {
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec("cmd /c" + cmd);
			process.waitFor();
			System.out.println("successly!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void load() {
		str = "mysql -u root -proot j2603  <  d:/test.sql";
		// mysql命令可以实现数据库的还原。格式“mysql -u Username -pPassword database_name <
		// back_up_dir ”
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("cmd /c" + str);
			System.out.println("restore successly!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("restore fail!");
		}
	}
}