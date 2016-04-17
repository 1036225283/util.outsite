package com.nitian.util.java;

import com.nitian.util.string.UtilStringHex;

public class UtilByte {

	private byte[] value;

	private static char[] letter = new char[] { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static void main(String[] args) {
		// byte[] b = intToBytes(13);
		// System.out.println(UtilByte);

	}

	public static void testToHex() {
		byte b = 1;
		for (int i = 0; i < 256; i++) {
			b = (byte) i;
			System.out.println(UtilByte.toHex(b));
			System.out.println(UtilByte.toBin(b));
		}
	}

	/**
	 * 字节数组[1,2]转换为[00000001,00000010]
	 * 
	 * @return
	 */
	public static String toBin(byte[] value) {
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

	/**
	 * 字节数组[1,2]转换为[00000001,00000010]
	 * 
	 * @return
	 */
	public static String[] toBinArray(byte[] value) {
		String[] strings = new String[value.length];
		for (int i = 0; i < value.length; i++) {
			strings[i] = toBin(value[i]);
		}
		return strings;
	}

	/**
	 * 字节2转换为0000 0010
	 * 
	 * @param b
	 * @return
	 */
	public static String toBin(byte b) {
		String result = "";
		byte x = 1;
		for (int i = 0; i < 8; i++) {
			result = (x & b) + result;
			b = (byte) (b >> 1);
		}
		return result;
	}

	public static byte fromBin(String bin) {
		char[] cs = bin.toCharArray();
		byte result = 0;
		for (int i = 0; i < cs.length; i++) {
			if (i != 0 && i != 1) {
				throw new RuntimeException("不是标准的二进制字符串");
			}
			result = (byte) (result << 1);
		}
		return 1;
	}

	/**
	 * 字节数组[1,2]转换为16进制[0x1,0x2]
	 * 
	 * @return
	 */
	public static String toHex(byte[] value) {
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

	/**
	 * 字节数组[1,2]转换为16进制[0x1,0x2]
	 * 
	 * @return
	 */
	public static String[] toHexArray(byte[] value) {
		String[] strings = new String[value.length];
		for (int i = 0; i < value.length; i++) {
			strings[i] = toHex(value[i]);
		}
		return strings;
	}

	/**
	 * 单个自己[1]转换为16进制[0x1]
	 * 
	 * @param b
	 * @return
	 */
	public static String toHex(byte b) {
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
	 * 字节数组[12,13]转换为十进制[12,13]
	 * 
	 * @return
	 */
	public static String toDec(byte[] value) {
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

	/**
	 * 字节数组[12,13]转换为十进制[12,13]
	 * 
	 * @return
	 */
	public static int[] toDecArray(byte[] value) {
		int[] is = new int[value.length];
		for (int i = 0; i < value.length; i++) {
			is[i] = value[i];
		}
		return is;
	}

	/**
	 * 获取某个字节[00001000]的第4位为1
	 * 
	 * @param b
	 * @param index
	 * @return
	 */
	public static byte getBit(byte b, int index) {
		byte one = 1;
		// System.out.println("one = " + toBin(one));
		// System.out.println("old b = " + toBin(b));
		b = (byte) (b >> (index - 1));
		// System.out.println("b>>" + index + "=" + toBin(b));
		b = (byte) (b & one);
		// System.out.println("b=" + toBin(b));
		return b;
	}

	/**
	 * 拷贝另外一个字节数组到到this.value[]数组
	 * 
	 * @param bs
	 */
	public static void copy(byte[] bs, byte[] value) {
		for (int i = 0; i < value.length; i++) {
			value[i] = bs[i];
		}
	}

	/**
	 * 比较两个字节数组相同和不同的部分
	 * 
	 * @param a
	 * @param b
	 */
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
