package utilsystem;

import java.io.BufferedReader;
import java.io.IOException;

public class UtilRequest {

	/**
	 * 读取request.getReader()的值
	 * @param reader
	 * @return
	 */
	public static String getReader(BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(reader);
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static String getString(BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(reader);
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
