package com.nitian.util.java;

import com.nitian.util.string.UtilStringHex;

/**
 * 字节操作api
 * 
 * @获取指定字节指定位置的值getByte(byte b,byte index)--<exist>
 * @设置指定字节指定位置的值setByte(byte b,byte index,byte value)
 * @设置指定字节指定位置的值为1#setByteOne(byte b,byte index)
 * @设置指定字节指定位置的值为0#setByteZero(byte b,byte index)
 * 
 * @author 1036225283
 *
 */
public class UtilByte {

	private byte[] value;

	private static char[] letter = new char[] { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static void main(String[] args) {
		// byte[] b = intToBytes(13);
		// System.out.println(UtilByte);
		byte b = -30;
		int duck = b;
		System.out.println("duck->" + duck);
		int test = 0;
		test = test | 0xff & b;
		System.out.println(test);
		System.out.println(toBin((byte) 226));
		// test1();
		// test2();
		// System.out.println(toBin((byte) 1));
	}

	/**
	 * 测试字节循环位移和非循环位移
	 */
	public static void test1() {
		byte b = 1;
		for (int i = 0; i < 15; i++) {
			b = (byte) (b >>> 1);
			System.out.println(toBin(b));
		}
	}

	/**
	 * 测试00000000-11111111的输出
	 */
	public static void test2() {
		for (int i = 0; i < 256; i++) {
			System.out.println(toBin((byte) i));
		}
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
	 * @首先，进行构造1或0，并位移
	 * @然后，与11111111进行或运算
	 * @最后，与传进来的值进行与运算
	 * 
	 * 
	 * @param b
	 * @param index
	 * @param value
	 * @return
	 */
	public static byte setByte(byte b, int index, int value) {
		/**
		 * 第一步，首次测试需要改变的位是1还是0
		 */
		int testValue = getBit(b, index);
		/**
		 * 如果是1，进行0的与运算
		 */
		if (testValue == value) {
			return b;
		} else if (testValue == 0 && value == 1) {
			value = value << (index - 1);
			b = (byte) (b | value);
			return b;
		} else if (testValue == 1 && value == 0) {
			value = value << (index - 1);
			value = ~value;
			b = (byte) (b & value);
			return b;
		}
		System.out.println("接受到的value->" + toBin((byte) value));
		byte full = (byte) 255;// 11111111
		System.out.println("full->" + toBin((byte) full));
		value = value << (index);
		System.out.println("value->" + toBin((byte) value));
		value = value ^ full;
		System.out.println("与11111111与运算后的value->" + toBin((byte) value));
		b = (byte) (b & value);
		return b;
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
