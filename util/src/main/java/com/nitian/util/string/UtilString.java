package com.nitian.util.string;

public class UtilString {

	/**
	 * 首字母大写
	 * 
	 * @param value
	 * @return
	 */
	public static String letterUpper(String value) {
		String head = value.substring(0, 1);
		String copy = value;
		copy = head.toUpperCase() + copy.substring(1);
		return copy;
	}

	/**
	 * 首字母小写
	 * 
	 * @param value
	 * @return
	 */
	public static String letterLower(String value) {
		String head = value.substring(0, 1);
		String copy = value;
		copy = head.toLowerCase() + copy.substring(1);
		return copy;
	}

	/**
	 * 截取value中从index到lastIndex中的值
	 * 
	 * @param value
	 * @param index
	 * @param lastIndex
	 * @return
	 */
	public static String substring(String value, String index, String lastIndex) {
		value = value.substring(value.indexOf(index) + 1,
				value.lastIndexOf(lastIndex));
		return value;
	}

	/**
	 * 截取value中从index1到index2中的值
	 * 
	 * @param value
	 * @param index1
	 * @param index2
	 * @return
	 */
	public static String substring_(String value, String index1, String index2) {
		value = value.substring(value.indexOf(index1) + 1,
				value.indexOf(index2));
		return value;
	}

	/**
	 * 判断是否为空
	 * 
	 * @param string
	 * @return
	 */
	public static String judgeIsNull(String string) {
		if (string == null) {
			return null;
		} else {
			string = string.trim();
			if (string.equals("")) {
				return null;
			} else {
				return string;
			}
		}
	}

	/**
	 * 判断是否是中文字符
	 * 
	 * @return
	 */
	public static boolean judgeIsChinese(char c) {
		if (c >= 19968 && c <= 171941) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断字符串是否全是中文
	 * 
	 * @param s
	 * @return
	 */
	public static boolean judgeStringIsChinese(String s) {
		if (s == null) {
			return false;
		}
		char[] cs = s.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			if (judgeIsChinese(cs[i]) == false) {
				return false;
			}
		}
		return true;
	}

}
