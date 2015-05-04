package utilclass;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import utilfile.UtilFile;

/**
 * 获取所有的类
 * 
 * @author 1036225283
 * 
 */
public class UtilClass {

	/**
	 * 加载当前项目下面所有类
	 * 
	 * @return
	 */
	public List<Class<?>> getAllClass() {
		List<String> classNameList = classNameList();
		List<Class<?>> classList = new ArrayList<Class<?>>();
		for (int i = 0; i < classNameList.size(); i++) {
			try {
				Class<?> clasz = Class.forName(classNameList.get(i));
				classList.add(clasz);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return classList;
	}

	/**
	 * 获取当前项目下面的所有类
	 * 
	 * @param fileNameList
	 * @return
	 */
	public final static List<String> classNameList() {
		String rootPath = UtilClass.class.getResource("/").toString()
				.substring(6).replace("/", File.separator);
		List<String> fileNameList = UtilFile.getAllFileName(rootPath);
		List<String> classNameList = new ArrayList<String>();
		for (int i = 0; i < fileNameList.size(); i++) {
			String classPath = fileNameList.get(i).replace(rootPath, "");
			int lastIndex = classPath.lastIndexOf(".class");
			if (lastIndex == -1) {
				continue;
			}
			classPath = classPath.substring(0, lastIndex);
			classPath = classPath.replace(File.separator, ".");
			classNameList.add(classPath);
		}
		return classNameList;
	}
}
