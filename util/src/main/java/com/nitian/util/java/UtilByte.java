package com.nitian.util.java;

import com.nitian.util.string.UtilStringHex;

public class UtilByte {

	private byte[] value;

	private char[] letter = new char[] { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 转换为00001111
	 * 
	 * @return
	 */
	public String toBin() {
		StringBuffer sb = new StringBuffer();
		int index = 0;
		for (int i = 0; i < value.length; i++) {
			if (index == 0) {
				index = index + 1;
			} else {
				sb.append("，");
			}
			sb.append(toBin(value[i]));
		}
		return sb.toString();
	}

	public String toBin(byte b) {
		String result = "";
		byte x = 1;
		for (int i = 0; i < 8; i++) {
			result = (x & b) + result;
			b = (byte) (b >> 1);
		}
		return result;
	}

	/**
	 * 转换为16进制
	 * 
	 * @return
	 */
	public String toHex() {
		StringBuffer sb = new StringBuffer();
		int index = 0;
		for (int i = 0; i < value.length; i++) {
			if (index == 0) {
				index = index + 1;
			} else {
				sb.append("，");
			}
			sb.append(toHex(value[i]));
		}
		return sb.toString();
	}

	public String toHex(byte b) {
		String result = "";
		byte x = 15;
		int var = b & x;
		result = letter[var] + result;
		b = (byte) (b >> 4);
		var = b & x;
		result = letter[var] + result;
		return result;
	}

	/**
	 * 转换为十进制
	 * 
	 * @return
	 */
	public String toDec() {
		StringBuffer sb = new StringBuffer();
		int index = 0;
		for (int i = 0; i < value.length; i++) {
			int x = value[i];
			if (index == 0) {
				index = index + 1;
			} else {
				sb.append("，");
			}
			sb.append(x);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		UtilByte byte1 = new UtilByte();
		byte1.setValue(new byte[] { 1, 2, 3, 45, 99, 100, (byte) 256 });
		System.out.println(byte1.toDec());
		System.out.println(byte1.toBin());
		byte b = 1;
		for (int i = 0; i < 256; i++) {
			b = (byte) i;
			System.out.println(byte1.toHex(b));
		}

	}

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

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}
}
