package utilsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 文件和字符串互转
 * 
 * @author xiaoyang
 * 
 */
public class UtilFileString {

	/**
	 * 读取文件
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static String fileToString(String fileName) {
		File file = new File(fileName);
		FileInputStream fileInputStream;
		StringBuffer sb = new StringBuffer();
		try {
			fileInputStream = new FileInputStream(file);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(fileInputStream, "utf-8"));
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
				sb.append("\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 写入文件
	 * 
	 * @param value
	 * @param fileName
	 * @throws Exception
	 */
	public static void stringToFile(String value, String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream(file);
			byte[] b = value.getBytes("utf-8");
			fileOutputStream.write(b, 0, b.length);
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
