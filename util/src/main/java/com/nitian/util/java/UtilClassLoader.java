package com.nitian.util.java;

import java.io.IOException;

import com.nitian.util.encrypt.UtilAES;
import com.nitian.util.file.UtilFile;

public class UtilClassLoader extends ClassLoader {

	/**
	 * 文件转换为类
	 * 
	 * @param className
	 * @param fileName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Class<?> fileToClass(String className, String fileName)
			throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		Class<?> ctmp = this.findLoadedClass(className);
		if (ctmp == null) {
			byte[] bs = UtilFile.fileToHex(fileName);
			return this.defineClass(className, bs, 0, bs.length);
		} else {
			return ctmp;
		}
	}

	/**
	 * 字节流直接转换成类
	 * 
	 * @param className
	 * @param bs
	 * @return
	 */
	public Class<?> bytesToClass(String className, byte[] bs) {
		// TODO Auto-generated method stub
		Class<?> ctmp = this.findLoadedClass(className);
		if (ctmp == null) {
			return this.defineClass(className, bs, 0, bs.length);
		} else {
			return ctmp;
		}
	}

	/**
	 * 文件通过解密加载
	 * 
	 * @param className
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Class<?> fileToClassByDecrypt(String className, String fileName,
			String aseKey) throws IOException {
		// TODO Auto-generated method stub
		Class<?> ctmp = this.findLoadedClass(className);
		if (ctmp == null) {
			byte[] bs = UtilFile.fileToHex(fileName);
			bs = UtilAES.decrypt(aseKey, bs);
			return this.defineClass(className, bs, 0, bs.length);
		} else {
			return ctmp;
		}
	}

	/**
	 * 字节直接转
	 * 
	 * @param className
	 * @param bs
	 * @return
	 * @throws ClassNotFoundException
	 */
	public Class<?> byteToClass(String className, byte[] bs) {
		// TODO Auto-generated method stub
		Class<?> ctmp = this.findLoadedClass(className);
		if (ctmp == null) {
			return this.defineClass(className, bs, 0, bs.length);
		} else {
			return ctmp;
		}
	}

	public Class<?> loadClass(String className, boolean resolve, byte[] bs) {
		Class<?> loadClass = null;

		try {
			loadClass = this.findLoadedClass(className);
			if (loadClass == null) {
				System.out.println("类没有找到");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("类没有找到");
		}

		try {
			if (loadClass == null) {
				loadClass = super.loadClass(className, false);
				if (loadClass != null) {
					System.out.println("系统加载成功：" + className);
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("系统无法加载：" + className);
		}

		try {
			if (loadClass == null) {
				loadClass = defineClass(className, bs, 0, bs.length);
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

	public Class<?> loadClass(String className, boolean resolve, String fileName) {
		Class<?> loadClass = null;
		loadClass = this.findLoadedClass(className);

		try {
			if (loadClass == null) {
				System.out.println("类没有找到");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("类没有找到");
		}

		try {
			if (loadClass == null) {
				loadClass = super.loadClass(className, false);
				if (loadClass != null) {
					System.out.println("系统加载成功：" + className);
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("系统无法加载：" + className);
		}

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
