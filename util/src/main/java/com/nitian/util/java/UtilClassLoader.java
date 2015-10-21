package com.nitian.util.java;

import com.nitian.util.file.UtilFile;

public class UtilClassLoader extends ClassLoader {

	/**
	 * file-->class
	 * 
	 * @param className
	 * @param fileName
	 * @return
	 * @throws ClassNotFoundException
	 */
	public Class<?> fileToClass(String className, String fileName)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Class<?> ctmp = this.findLoadedClass(className);
		if (ctmp == null) {
			byte[] bs = UtilFile.fileToHex(fileName);
			return this.defineClass(className, bs, 0, bs.length);
		} else {
			return ctmp;
		}
	}

	public synchronized Class<?> loadClass(String className, boolean resolve,
			String fileName) throws ClassNotFoundException {
		Class<?> loadClass = null;
		// loadClass = this.findLoadedClass(className);

		// try {
		// if(loadClass == null){
		// System.out.println("类没有找到");
		// }
		// } catch (Exception e) {
		// // TODO: handle exception
		// System.out.println("类没有找到");
		// }

		// try {
		// if (loadClass == null) {
		// loadClass = super.loadClass(className, false);
		// if (loadClass != null) {
		// System.out.println("系统加载成功：" + className);
		// }
		// }
		// } catch (ClassNotFoundException e) {
		// System.out.println("系统无法加载：" + className);
		// }

		try {
			if (loadClass == null) {
				loadClass = fileToClass(className, fileName);
				if (loadClass != null) {
					System.out.println("自定义加载成功：" + className);
				}
			}
		} catch (Exception e) {
			System.out.println("自定义无法加载：" + className);
		}
		if (resolve) {
			resolveClass(loadClass);
		}
		return loadClass;
	}

}
