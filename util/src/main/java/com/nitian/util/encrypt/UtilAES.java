package com.nitian.util.encrypt;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.nitian.util.file.UtilFile;
import com.nitian.util.string.UtilStringHex;

public class UtilAES {

	public static void main(String[] args) {
		String path = "C:\\Users\\1036225283\\Desktop\\util\\Test.java";
		String aesKey = UtilAES.createKey();
		System.out.println(aesKey);
		byte[] file = UtilFile.fileToHex(path);
		byte[] result = UtilAES.encrypt(aesKey, file);
		System.out.println(UtilStringHex.bytesHexStr(result));
		result = UtilAES.decrypt(aesKey, result);
		System.out.println(UtilStringHex.bytesHexStr(result));
	}

	public static void test() {
		String path = "C:\\Users\\1036225283\\Desktop\\util\\Test.java";
		try {
			// 生成key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] keyBytes = secretKey.getEncoded();
			System.out.println(keyBytes.length);
			System.out.println("密钥："
					+ UtilStringHex.bytesHexStr(keyBytes).length());
			// key转换
			Key key = new SecretKeySpec(keyBytes, "AES");
			// 加密
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] file = UtilFile.fileToHex(path);
			byte[] result = cipher.doFinal(file);
			System.out.println(UtilStringHex.bytesHexStr(result));
			// 解密
			cipher.init(Cipher.DECRYPT_MODE, key);
			result = cipher.doFinal(result);
			System.out.println(UtilStringHex.bytesHexStr(result));
			System.out.println(new String(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 加密
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static byte[] encrypt(String aesKey, byte[] value) {
		byte[] result = null;
		try {
			Key key = new SecretKeySpec(UtilStringHex.initByte(aesKey), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			result = cipher.doFinal(value);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	/**
	 * 解密
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static byte[] decrypt(String aesKey, byte[] value) {
		byte[] result = null;
		try {
			Key key = new SecretKeySpec(UtilStringHex.initByte(aesKey), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			result = cipher.doFinal(value);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	/**
	 * 创建密钥
	 * 
	 * @return
	 */
	public static String createKey() {
		String result = null;
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] keyBytes = secretKey.getEncoded();
			result = UtilStringHex.bytesHexStr(keyBytes);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

}
