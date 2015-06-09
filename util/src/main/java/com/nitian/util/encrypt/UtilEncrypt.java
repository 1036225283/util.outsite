package com.nitian.util.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密/解密/进制转换
 * 
 * @author 1036225283
 *
 */
public class UtilEncrypt {

	/**
	 * 进行md5加密
	 * 
	 * @param string
	 * @return
	 */
	public static String toMd5(String string) {
		byte[] bytes = string.getBytes();
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(bytes);
			byte[] bs = mdInst.digest();
			for (byte b : bs) {
				String value = Integer.toHexString(b & 0xff);
				if (value.length() == 2) {
					sb.append(value);
				} else {
					sb.append("0" + value);
				}
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String a = null;
		try {
			a = new String(sb.toString().getBytes(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
}
