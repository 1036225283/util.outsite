package com.nitian.util.encrypt;

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
				sb.append(Integer.toHexString(b & 0xff));
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
}
