package com.nitian.util.encrypt;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密/解密/进制转换
 * 
 * @author 1036225283
 *
 */
public class UtilMd5 {

	/**
	 * 二进制数组转换16进制字符
	 * 
	 * @param bs
	 * @return
	 */
	public static String bytesToHexString(byte[] bs) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bs) {
			String value = Integer.toHexString(b & 0xff);
			if (value.length() == 2) {
				sb.append(value);
			} else {
				sb.append("0" + value);
			}
		}
		return sb.toString();
	}

	/**
	 * string 进行md5校检
	 * 
	 * @param string
	 * @return
	 */
	public static String stringToMd5(String string) {
		byte[] bytes = string.getBytes();
		byte[] bs = null;
		try {
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(bytes);
			bs = mdInst.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return bytesToHexString(bs);
	}

	/**
	 * 文件进行md5校检
	 * 
	 * @param fileName
	 * @return
	 */
	public static String fileToMd5(String fileName) {
		File file = new File(fileName);
		InputStream inputStream;
		MessageDigest md5 = null;
		try {
			inputStream = new FileInputStream(file);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					inputStream);
			byte[] buffer = new byte[1024 * 1024 * 200];
			md5 = MessageDigest.getInstance("MD5");
			int len;
			while ((len = inputStream.read(buffer)) != -1) {
				md5.update(buffer, 0, len);
			}
			inputStream.close();
			bufferedInputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return DigestUtils.md5Hex(md5.digest());
	}

	public static void main(String[] args) {
		System.out.println(stringToMd5("呵"));
		System.out.println(stringToMd5("呵呵"));
		System.out.println(stringToMd5("呵呵呵"));
		System.out.println(stringToMd5("呵呵呵呵"));
		long start = System.currentTimeMillis();
		System.out.println("开始计算文件MD5值,请稍后...");
		String fileName = "D:\\cn_windows_7_ultimate_x86_dvd_x15-65907.iso";
		String hash = fileToMd5(fileName);
		System.out.println("MD5:" + hash);
		long end = System.currentTimeMillis();
		System.out.println("一共耗时:" + (end - start) + "毫秒");

	}
}
