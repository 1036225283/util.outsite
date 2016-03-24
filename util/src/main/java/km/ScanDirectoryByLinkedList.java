package km;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

public class ScanDirectoryByLinkedList {

	private static String directory = "";
	private static String outDirectory = "";
	private static int total = 0;
	private static FileOutputStream fileOutputStream;

	private static List<File> list = new LinkedList<File>();
	private static File[] files;
	private static File file;

	public static void main(String[] args) {
		test();
		System.out.println("this is end");
	}

	public static void appendString(String value) {
		byte[] b;
		try {
			b = value.getBytes("utf-8");
			fileOutputStream.write(b, 0, b.length);
			fileOutputStream.flush();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void init() {
		File file = new File(outDirectory + "/xws.json");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			fileOutputStream = new FileOutputStream(file, true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void test() {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in));
		try {
			System.out.println("请输入需要扫描的目录：");
			directory = bufferedReader.readLine();
			System.out.println("需要扫描的目录为：" + directory);
			System.out.println("请输入结果存放的目录：");
			outDirectory = bufferedReader.readLine();
			System.out.println("存放结果的目录为：" + outDirectory);
			System.out.println("开始扫描：");
			init();
			File file = new File(directory);
			list.add(file);
			getAllFileName();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		appendString("--------------end----------------");
	}

	private final static void getAllFileName() {
		while (list.size() != 0) {
			file = list.remove(list.size() - 1);
			if (file.isDirectory()) {
				System.out.println("+++" + file.getAbsolutePath());
				appendString(file.getAbsolutePath() + "\n");
				files = file.listFiles();
				System.out.println("000files size : " + files.length);
				total = total + 1;
				System.out.println(total);
				for (int i = 0; i < files.length; i++) {
					list.add(files[i]);
				}
			}
		}
	}

}
