package com.nitian.util.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class UtilJar {

	/**
	 * 获取jar包中所有文件实体
	 * 
	 * @param path
	 * @return
	 */
	public static List<JarEntry> getAllJar(String path) {
		List<JarEntry> jarEntries = new ArrayList<JarEntry>();
		try {
			@SuppressWarnings("resource")
			JarFile jarFile = new JarFile(path);
			Enumeration<JarEntry> enumeration = jarFile.entries();
			while (enumeration.hasMoreElements()) {
				jarEntries.add(enumeration.nextElement());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jarEntries;
	}

	/**
	 * 获取运行时jar的路径
	 * 
	 * @return
	 */
	public static String getJarPath() {
		return UtilJar.class.getProtectionDomain().getCodeSource()
				.getLocation().getPath().trim();
	}

	/**
	 * 获取jar包中某一指定path的内容
	 * 
	 * @param path
	 * @param jarPath
	 * @return
	 */
	public static void getAllJarEntry(String path, String jarPath) {
		try {
			@SuppressWarnings("resource")
			JarFile jarFile = new JarFile(jarPath);
			Enumeration<JarEntry> enums = jarFile.entries();
			while (enums.hasMoreElements()) {
				JarEntry jarEntry = enums.nextElement();
				String className = jarEntry.getName();
				if (jarEntry.isDirectory()) {
					continue;
				} else if (className.contains(path)
						&& className.indexOf(path) == 0) {
					className = className.replace("/", ".");
					className = className.substring(0, className.length() - 6);
					System.out.println(className);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
