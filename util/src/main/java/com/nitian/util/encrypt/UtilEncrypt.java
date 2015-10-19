package com.nitian.util.encrypt;

public class UtilEncrypt {

	/**
	 * 对字符串进行首尾交换
	 * 
	 * @param value
	 */
	public static String swap1(String value) {
		StringBuffer sb = new StringBuffer();
		String lastChar = "";
		int length = value.length();
		int mod = length % 2;
		if (mod == 1) {
			lastChar = value.substring(length - 1);
			length--;
		}
		for (int i = 0; i < length / 2; i++) {
			sb.append(value.charAt(length - 1 - i));
		}
		for (int i = 0; i < length / 2; i++) {
			sb.append(value.charAt(i));
		}
		sb.append(lastChar);
		return sb.toString();
	}

	/**
	 * 对字符串进行邻邻居交换
	 * 
	 * @param value
	 * @return
	 */
	public static String swap2(String value) {
		StringBuffer sb = new StringBuffer();
		String lastChar = "";
		int length = value.length();
		int mod = length % 2;
		if (mod == 1) {
			lastChar = value.substring(length - 1);
			length--;
		}
		for (int i = 0; i < length / 2; i++) {
			sb.append(value.charAt(i * 2 + 1));
			sb.append(value.charAt(i * 2));
		}
		sb.append(lastChar);
		return sb.toString();
	}

	public static void main(String[] args) {
		String s = "1234567890";
		System.out.println(swap1(s));
		System.out.println(swap2(s));
		System.out.println(10 / 2);

		String a = "4";
		String b = "6";

		byte[] ba = a.getBytes();
		byte[] bb = b.getBytes();

		for (int i = 0; i < bb.length; i++) {
			byte c = 0;
		}

	}
}
