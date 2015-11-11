package com.nitian.util.java;

import com.nitian.util.string.UtilStringHex;

public class UtilByte {

	public static void compare(byte[] a, byte[] b) {
		byte[] a0 = new byte[1];
		byte[] b0 = new byte[1];
		int length = (a.length > b.length) ? b.length : a.length;
		System.out.println(length);
		for (int i = 0; i < b.length; i++) {
			if (a[i] != b[i]) {
				System.out.println("i=" + i);
				a0[0] = a[i];
				b0[0] = b[i];
				System.out.println("a=" + UtilStringHex.bytesHexStr(a0));
				System.out.println("b=" + UtilStringHex.bytesHexStr(b0));
				System.out.println();
			}
		}
	}

	/**
	 * 整型-->bytes||65535-->0000FFFF
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] intToBytes(int value) {
		byte[] bs = new byte[4];
		for (int i = 3; i >= 0; i--) {
			bs[i] = (byte) (value % 256);
			value = value / 256;
		}
		return bs;
	}

	/**
	 * bytes-->整型||0000FFFF-->65535
	 * 
	 * @param bs
	 * @return
	 */
	public static Integer bytesToInt(byte[] bs) {
		String value = UtilStringHex.bytesHexStr(bs);
		return Integer.valueOf(value, 16);
	}

	/**
	 * 从index出填充拷贝数据
	 * 
	 * @param to
	 * @param from
	 * @param index
	 */
	public static void copy(byte[] to, byte[] from, int index) {
		for (int i = 0; i < from.length; i++) {
			to[i + index] = from[i];
		}
	}

	public static void main(String[] args) {
		byte[] a = new byte[5];
		byte[] b = new byte[4];
		compare(a, b);
	}
}
