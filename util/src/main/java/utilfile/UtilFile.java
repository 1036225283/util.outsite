package utilfile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件辅助工具类
 * 
 * @author 1036225283
 * 
 */
public class UtilFile {

	/**
	 * 获取某一目录下所有文件名
	 * 
	 * @param path
	 * @return List
	 */
	public final static List<String> getAllFileName(String path) {
		File file = new File(path);
		List<String> list = new ArrayList<String>();
		getAllFileName(file, list);
		return list;
	}

	/**
	 * 迭代扫描文件夹
	 * 
	 * @param rootFile
	 * @param fileNameList
	 */
	private final static void getAllFileName(File rootFile,
			List<String> fileNameList) {
		File[] files = rootFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isFile()) {
				fileNameList.add(file.getAbsolutePath());
			} else {
				getAllFileName(file, fileNameList);
			}
		}
	}
}
