package km;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.nitian.util.file.UtilFile;

public class ScanXws {

	private static Log yesLog = new Log("yes");
	private static Log noLog = new Log("no");

	private String directory = "/data/cloudfs/osss/instructions";

	public static void main(String[] args) {

		try {
			ScanXws byLinkedList = new ScanXws();
			List<String> list = byLinkedList.getListByExcel();
			byLinkedList.checkDir(list);
			System.out.println("this is end");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	public void checkDir(List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			String data = list.get(i);
			String[] strings = data.split("\t");
			String fileName = directory + "/" + strings[0] + "/" + strings[1]
					+ "/1.jpg";
			File file = new File(fileName);
			if (file.exists()) {
				yesLog.info(data + "\n");
				System.out.println("存在" + i);
			} else {
				noLog.info(data + "\n");
				System.out.println("不存在" + i);
			}
		}
		yesLog.info("end--------------------------");
		noLog.info("end--------------------------");
	}

	public List<String> getListByExcel() throws IOException {
		List<String> list = new ArrayList<String>();
		String[] strings = UtilFile.fileToString("/data/xws/test.java").split(
				"\r\n");
		for (int i = 0; i < strings.length; i++) {
			String value = strings[i];
			if (value.equals("")) {
				value = "------";
			}
			list.add(value);
		}
		return list;
	}

}
