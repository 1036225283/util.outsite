package com.nitian.util.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 日志输出
 * 
 * @author 1036225283
 *
 */
public class Log {

	private FileOutputStream fileOutputStream;

	public Log(String logFileName) {
		// TODO Auto-generated constructor stub

		File file = new File(Log.class.getResource("/").getPath() + logFileName
				+ ".log");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fileOutputStream = new FileOutputStream(file, true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Log(String path, String name) {
		// TODO Auto-generated constructor stub
		File file = new File(path + File.separator + name + ".log");
		System.out.println("log file is " + file.getPath());
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fileOutputStream = new FileOutputStream(file, true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void info(String value) {
		try {
			byte[] b = value.getBytes("utf-8");
			fileOutputStream.write(b, 0, b.length);
			fileOutputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
