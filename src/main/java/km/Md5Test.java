package km;

import _1036225283.com.util.self.encrypt.UtilMd5;
import _1036225283.com.util.self.file.UtilFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Md5Test {

	private static Map<String, String> map = new HashMap<String, String>();

	private static String sourceRootDirectory = "";
	private static String distRootDirectory = "";
	private static String zhunzihaoFile = "";
	private static List<String> list = new ArrayList<String>();

	private static EncrypAES encryp = EncrypAES.getInstance();

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in));
		System.out.println("请输入需要扫描的目录：");
		sourceRootDirectory = bufferedReader.readLine();
		System.out.println("需要扫描的目录为：" + sourceRootDirectory);
		System.out.println("请输入结果存放的目录：");
		distRootDirectory = bufferedReader.readLine();
		System.out.println("存放结果的目录为：" + distRootDirectory);
		System.out.println("请输入准字号文件目录：");
		zhunzihaoFile = bufferedReader.readLine();
		System.out.println("准字号文件目录为：" + zhunzihaoFile);
		System.out.println("开始扫描：");
		readFile();
		makeDirectory();
		makeFile();
	}

	// 解析准字号文件
	public static List<String> readFile() throws IOException {
		String string = UtilFile.fileToString(zhunzihaoFile);
		String[] strings = string.split("\r\n");
		for (int i = 0; i < strings.length; i++) {
			list.add(strings[i]);
		}
		return list;
	}

	// 创建目录
	public static void makeDirectory() throws IOException {
		// 第一步：解析准字号文件
		// 第二步：进行循环，拼接准字号，形成目录
		for (int i = 0; i < list.size(); i++) {
			String zhunzihaoSourceDirectory = sourceRootDirectory
					+ File.separator + list.get(i);// 准字号目录
			File sourceFile = new File(zhunzihaoSourceDirectory);
			if (!sourceFile.exists()) {
				continue;
			}
			// 第三步：根据准字号目录生成对应的md5目录
			String md5Directory = UtilMd5.stringToMd5(list.get(i));
			// 第四步：判断md5目录是否存在，不存在就创建
			String zhunzihaoDistDirectory = distRootDirectory + File.separator
					+ md5Directory;// 准字号目录对应的md5目录
			File distFile = new File(zhunzihaoDistDirectory);
			if (!distFile.exists()) {
				distFile.mkdirs();
				System.out.println("++++创建目录：" + distFile.getAbsolutePath());
			}
			// 第五步：对源目录的子目录进行处理,同时复制config.json文件
			File[] files = new File(zhunzihaoSourceDirectory).listFiles();
			for (int j = 0; j < files.length; j++) {
				if (files[j].isDirectory()) {
					md5Directory = UtilMd5.stringToMd5(files[j].getName());
					// 将{来源日期文件夹：目标日期文件夹} 进行映射
					map.put(files[j].getAbsolutePath(), zhunzihaoDistDirectory
							+ File.separator + md5Directory);
					File dateDirectory = new File(zhunzihaoDistDirectory
							+ File.separator + md5Directory);
					if (!dateDirectory.exists()) {
						dateDirectory.mkdirs();
						System.out.println("----创建子级目录："
								+ dateDirectory.getAbsolutePath());
					}
				} else if (files[j].getName().equals("config.json")) {
					String jsonString = UtilFile.fileToString(files[j]
							.getAbsolutePath());
					String jsonFileName = zhunzihaoDistDirectory
							+ File.separator + "config.json";
					UtilFile.stringToFile(jsonString, jsonFileName);
					System.out.println(">>>拷贝文件" + jsonFileName);
				}
			}
		}
	}

	/**
	 * @throws Exception
	 *             创建文件
	 * 
	 * @throws
	 */
	public static void makeFile() throws Exception {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String sourceDirectory = entry.getKey();
			String distDirectory = entry.getValue();
			File file = new File(sourceDirectory);
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				// 获取文件后缀
				String name = files[i].getName();

				if (name.contains(".pdf")) {
					byte[] bytes = UtilFile.fileToHex(files[i]
							.getAbsolutePath());
					String outFile = distDirectory + File.separator + name;
					UtilFile.hexToFile(bytes, outFile);
					System.out.println(">>>拷贝文件：" + outFile);
				} else {
					String outFile = distDirectory + File.separator + name;
					encryp.encryptFile(files[i].getAbsolutePath(), outFile);
					System.out.println("<<<加密文件：" + outFile);
				}
			}
		}
	}

}
