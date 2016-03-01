package com.nitian.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.nitian.util.string.UtilStringHex;

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
			int index = 0;
			while ((line = bufferedReader.readLine()) != null) {
				if (index == 0) {
					index = index + 1;
				} else {
					sb.append("\r\n");
				}
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 读取文件字节转换为16进制字符
	 * 
	 * @param fileName
	 * @return
	 */
	public static String fileToHexString(String fileName) {
		return UtilStringHex.bytesHexStr(fileToHex(fileName));
	}

	/**
	 * 读取文件的字节流
	 * 
	 * @param fileName
	 * @return
	 */
	public static byte[] fileToHex(String fileName) {
		File file = new File(fileName);
		FileInputStream fileInputStream;
		byte[] bs = new byte[(int) (file.length())];
		try {
			fileInputStream = new FileInputStream(file);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					fileInputStream);
			bufferedInputStream.read(bs);
			bufferedInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bs;
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

	/**
	 * 将16进制字符串转换为字节，写入文件
	 * 
	 * @param value
	 * @param fileName
	 */
	public static void hexStringToFile(String value, String fileName) {
		hexToFile(UtilStringHex.initByte(value), fileName);
	}

	/**
	 * 将字节流写入文件
	 * 
	 * @param value
	 * @param fileName
	 */
	public static void hexToFile(byte[] value, String fileName) {
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
			fileOutputStream.write(value, 0, value.length);
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断文件后缀是否.jar
	 * 
	 * @param path
	 * @return
	 */
	public static boolean judgeJar(String path) {
		String jar = path.substring(path.length() - 4);
		if (".jar".equals(jar)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取jar的目录
	 * 
	 * @param path
	 * @return
	 */
	public static String getJarPath(String path) {
		return path.substring(0, path.lastIndexOf("/"));
	}
}
