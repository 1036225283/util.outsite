package km;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class ScanDirectoryByLinkedList {

	private static Log outLog = new Log("log");
	private static Log resultLog = new Log("result");

	private String directory = "";
	private int total = 0;
	private int index = 0;

	private List<String> list = new LinkedList<String>();
	private File[] files;

	public static void main(String[] args) throws IOException {

		try {
			ScanDirectoryByLinkedList byLinkedList = new ScanDirectoryByLinkedList();
			byLinkedList.test();
			System.out.println("this is end");
			outLog.info("\n end -----------------------------------------------");
		} catch (Exception e) {
			// TODO: handle exception
			outLog.info(e.getMessage());
		}

	}

	public void test() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in));
		System.out.println("请输入需要扫描的目录：");
		directory = bufferedReader.readLine();
		System.out.println("需要扫描的目录为：" + directory);
		System.out.println("开始扫描：");
		File file = new File(directory);
		list.add(file.toString());
		getAllFileName();
	}

	private final void getAllFileName() throws IOException {
		while (list.size() != 0) {
			outLog.info("\ntaotal:" + total);
			String name = list.remove(list.size() - 1);
			outLog.info("\nremove:" + name);
			File file = new File(name);
			outLog.info("\nlist size : " + list.size());
			index = index + 1;
			if (index > 10000) {
				index = 0;
				try {
					Thread.sleep(5000);
					System.out
							.println("sleep---------------------------------------------------------");
					outLog.info("\nsleep-------------------------------------------------------------");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (file.isDirectory()) {
				directory = file.getAbsolutePath();
				resultLog.info(file.getAbsolutePath() + "\n");
				files = file.listFiles();
				String[] fileNames = file.list();
				if (files == null) {
					continue;
				}
				total = total + 1;
				for (int i = 0; i < fileNames.length; i++) {
					String path = directory + File.separator + fileNames[i];
					outLog.info("\nadd path:" + path);
					list.add(path);
				}
			}
		}
	}

}
