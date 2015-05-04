package com.nitian.util.weixin.verify;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class VerifyMessage {

	public static Boolean checkSignature(String token, String timestamp,
			String nonce, String signature) {
		String[] array = { token, timestamp, nonce };
		Arrays.sort(array);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
		}
		String pwd = Encrypt(sb.toString());
		if (signature.equals(pwd)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 用SHA-1算法加密字符串并返回16进制串
	 * 
	 * @param strSrc
	 * @return
	 */
	private static String Encrypt(String strSrc) {
		MessageDigest md = null;
		String strDes = null;
		byte[] bt = strSrc.getBytes();
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(bt);
			strDes = bytes2Hex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			System.out.println("错误");
			return null;
		}
		return strDes;
	}

	private static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}
}
