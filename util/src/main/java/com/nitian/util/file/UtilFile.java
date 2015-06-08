package com.nitian.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UtilFile {

	/**
	 * 获取某一目录下所有文件名
	 * 
	 * @param path
	 * @return List
	 */
	public final static List<String> getAllFileName(String path) {
		File file = new File(path);
		List<String> list = new ArrayList<String>();
		getAllFileName(file, list);
		return list;
	}

	/**
	 * 迭代扫描文件夹
	 * 
	 * @param rootFile
	 * @param fileNameList
	 */
	private final static void getAllFileName(File rootFile,
			List<String> fileNameList) {
		File[] files = rootFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isFile()) {
				fileNameList.add(file.getAbsolutePath());
			} else {
				getAllFileName(file, fileNameList);
			}
		}
	}

	/**
	 * 读取文件为字符串
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static String fileToString(String fileName) {
		File file = new File(fileName);
		FileInputStream fileInputStream;
		StringBuffer sb = new StringBuffer();
		try {
			fileInputStream = new FileInputStream(file);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(fileInputStream, "utf-8"));
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
				sb.append("\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 将字符串写入文件
	 * 
	 * @param value
	 * @param fileName
	 * @throws Exception
	 */
	public static void stringToFile(String value, String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream(file);
			byte[] b = value.getBytes("utf-8");
			fileOutputStream.write(b, 0, b.length);
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
