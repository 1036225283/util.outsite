package util;

import com.nitian.util.encrypt.UtilAES;
import com.nitian.util.file.UtilFile;
import com.nitian.util.string.UtilStringHex;

public class UtilEncryptFile {

	public static String key = "5EC67B8B67F2501C936A72E1242C4128";

	public static void main(String[] args) {
		// System.out.println(createKey());
		String fileName = "C:\\Users\\1036225283\\Desktop\\km\\age.csv";
		// UtilEncryptFile.encryptFile(fileName);

		String result = UtilEncryptFile.decryptFile(fileName);
		fileName = fileName + ".old";
		UtilFile.stringToFile(result, fileName);

	}

	public static String createKey() {
		return key = UtilAES.createKey();
	}

	/**
	 * 加密
	 * 
	 * @param fileName
	 */
	public static void encryptFile(String fileName) {
		String fileString = UtilFile.fileToString(fileName);
		byte[] fileByte = UtilAES.encrypt(key, fileString.getBytes());
		UtilFile.stringToFile(UtilStringHex.bytesHexStr(fileByte), fileName
				+ ".code");
	}

	public static String decryptFile(String fileName) {
		// 16进制数据
		String fileString = UtilFile.fileToString(fileName + ".code");
		System.out.println(fileString.length());

		byte[] fileByte = UtilStringHex.initByte(fileString);

		byte[] result = UtilAES.decrypt(key, fileByte);
		return new String(result);
	}
}
