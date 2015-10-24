package com.nitian.util.encrypt;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.nitian.util.string.UtilStringHex;

public class UtilAES {

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
