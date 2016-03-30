package com.nitian.util.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * java jar 读取
 * 
 * @author 1036225283
 *
 */
public class UtilJar {

	private JarFile jarFile;
	private List<String> list = new ArrayList<String>();

	public UtilJar(String jarFile) throws IOException {
		// TODO Auto-generated constructor stub
		this.jarFile = new JarFile(jarFile);
	}

	public static void main(String[] args) {
		// UtilJar jar = new UtilJar("");
	}

	public byte[] getFileContent(String fileName) throws IOException {
		JarEntry jarEntry = jarFile.getJarEntry(fileName);
		byte[] bs = null;
		if (jarEntry != null && !jarEntry.isDirectory()) {
			bs = new byte[(int) jarEntry.getSize()];
			InputStream inputStream = jarFile.getInputStream(jarEntry);
			inputStream.read(bs);
			return bs;
		} else {
			return bs;
		}
	}

	/**
	 * 获取输入流
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public InputStream getInputStream(String fileName) throws IOException {
		JarEntry jarEntry = jarFile.getJarEntry(fileName);
		if (jarEntry != null && !jarEntry.isDirectory()) {
			return jarFile.getInputStream(jarEntry);
		}
		return null;
	}

	/**
	 * 获取所有内容
	 * 
	 * @return
	 */
	public List<String> getAll() {
		if (list.size() == 0) {
			Enumeration<JarEntry> enumeration = jarFile.entries();
			while (enumeration.hasMoreElements()) {
				JarEntry jarEntry = enumeration.nextElement();
				list.add(jarEntry.getName());
			}
		}
		return list;
	}

	/**
	 * 获取jar运行时路径
	 * 
	 * @return
	 */
	public static String getJarPath() {
		return UtilJar.class.getProtectionDomain().getCodeSource()
				.getLocation().getPath().trim();
	}

}
