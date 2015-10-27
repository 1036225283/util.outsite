package com.nitian.util.string;

public class UtilStringHex {

	/**
	 * 16进制字符串初始化为字节数组
	 * 
	 * @param bs
	 * @param string
	 */
	public static void initByte(byte[] bs, String string) {
		int length = string.length() / 2;
		String[] strings = new String[length];
		for (int i = 0; i < length; i++) {
			strings[i] = string.substring(i * 2, i * 2 + 2);
		}
		for (int i = 0; i < strings.length; i++) {
			int value = Integer.parseInt(strings[i], 16);
			bs[i] = (byte) value;
		}
	}

	/**
	 * 返回16进制字符串初始化为字节数组
	 * 
	 * @param string
	 * @return
	 */
	public static byte[] initByte(String string) {
		byte[] bytes = new byte[string.length() / 2];
		initByte(bytes, string);
		return bytes;
	}

	/**
	 * 字符串转换成十六进制字符串
	 */

	public static String str2HexStr(String str) {

		char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuilder sb = new StringBuilder("");
		byte[] bs = str.getBytes();
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(chars[bit]);
			bit = bs[i] & 0x0f;
			sb.append(chars[bit]);
		}
		return sb.toString();
	}

	/**
	 * 
	 * 十六进制转换字符串
	 */

	public static String hexStr2Str(String hexStr) {
		String str = "0123456789ABCDEF";
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;
		for (int i = 0; i < bytes.length; i++) {
			n = str.indexOf(hexs[2 * i]) * 16;
			n += str.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		return new String(bytes);
	}

	/**
	 * bytes转换成十六进制字符串
	 */

	public static String bytesHexStr(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			// if (n<b.length-1) hs=hs+":";
		}
		return hs.toUpperCase();
	}

	private static byte uniteBytes(String src0, String src1) {
		byte b0 = Byte.decode("0x" + src0).byteValue();
		b0 = (byte) (b0 << 4);
		byte b1 = Byte.decode("0x" + src1).byteValue();
		byte ret = (byte) (b0 | b1);
		return ret;
	}

	/**
	 * bytes转换成十六进制字符串
	 */
	public static byte[] hexStr2Bytes(String src) {
		int m = 0, n = 0;
		int l = src.length() / 2;
		byte[] ret = new byte[l];
		for (int i = 0; i < l; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = uniteBytes(src.substring(i * 2, m), src.substring(m, n));
		}
		return ret;
	}

	/**
	 * String的字符串转换成unicode的String
	 */
	public static String str2Unicode(String strText) throws Exception {
		char c;
		String strRet = "";
		int intAsc;
		String strHex;
		for (int i = 0; i < strText.length(); i++) {
			c = strText.charAt(i);
			intAsc = (int) c;
			strHex = Integer.toHexString(intAsc);
			if (intAsc > 128) {
				strRet += "//u" + strHex;
			} else {
				// 低位在前面补00
				strRet += "//u00" + strHex;
			}
		}
		return strRet;
	}

	/**
	 * unicode的String转换成String的字符串
	 */
	public static String unicode2Str(String hex) {
		int t = hex.length() / 6;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < t; i++) {
			String s = hex.substring(i * 6, (i + 1) * 6);
			// 高位需要补上00再转
			String s1 = s.substring(2, 4) + "00";
			// 低位直接转
			String s2 = s.substring(4);
			// 将16进制的string转为int
			int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
			// 将int转换为字符
			char[] chars = Character.toChars(n);
			str.append(new String(chars));
		}
		return str.toString();
	}

	/**
	 * 字符直接转字节
	 * 
	 * @param chars
	 * @return
	 */
	public static byte[] charsToBytes(char[] chars) {
		byte[] bytes = new byte[chars.length];
		for (int i = 0; i < chars.length; i++) {
			bytes[i] = (byte) chars[i];
		}
		return bytes;
	}

	/**
	 * 将string分解成16进制string
	 * 
	 * @param string
	 * @return
	 */
	public static final String stringToHexString(String string) {
		return bytesHexStr(string.getBytes());
	}
}
