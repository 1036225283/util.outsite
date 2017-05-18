package km;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ScanDirectoryByArrayList {

	private static String directory = "";
	private static String outDirectory = "";
	private static int total = 0;
	private static FileOutputStream fileOutputStream;

	private static List<String> list = new ArrayList<String>();
	private static File[] files;

	private static int index = 0;

	public static void main(String[] args) throws IOException {

		test();
		System.out.println("this is end");
	}

	public static void appendString(String value) throws IOException {
		byte[] b;
		b = value.getBytes("utf-8");
		fileOutputStream.write(b, 0, b.length);
		fileOutputStream.flush();

	}

	public static void init() throws IOException {
		File file = new File(outDirectory + "/xws.json");
		if (!file.exists()) {
			file.createNewFile();
		}
		fileOutputStream = new FileOutputStream(file, true);

	}

	public static void test() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in));
		System.out.println("请输入需要扫描的目录：");
		directory = bufferedReader.readLine();
		System.out.println("需要扫描的目录为：" + directory);
		System.out.println("请输入结果存放的目录：");
		outDirectory = bufferedReader.readLine();
		System.out.println("存放结果的目录为：" + outDirectory);
		System.out.println("开始扫描：");
		init();
		File file = new File(directory);
		list.add(file.toString());
		getAllFileName();
		appendString("--------------end----------------");
	}

	private final static void getAllFileName() throws IOException {
		while (list.size() != 0) {
			String name = list.remove(0);
			File file = new File(name);
			index = index + 1;
			System.out.println("index: " + index);
			System.out.println("^^^list size : " + list.size());
			if (file.isDirectory()) {
				directory = file.getAbsolutePath();
				System.out.println("+++" + file.getAbsolutePath());
				appendString(file.getAbsolutePath() + "\n");
				files = file.listFiles();
				String[] fileNames = file.list();
				if (files == null) {
					continue;
				}
				total = total + 1;
				System.out.println(total);
				for (int i = 0; i < fileNames.length; i++) {
					list.add(directory + File.separator + fileNames[i]);
				}
			}
		}
	}

}
