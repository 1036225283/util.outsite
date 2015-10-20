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

	public static void main(String[] args) {
		byte[] a = new byte[5];
		byte[] b = new byte[4];
		compare(a, b);
	}
}
