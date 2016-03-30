package com.nitian.util.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

/**
 * 创建jar包
 * 
 * @author 1036225283
 *
 */
public class UtilJarMake {

	private JarOutputStream jarOutputStream;
	private JarEntry jarEntry;

	public UtilJarMake(String jarFileName) throws IOException {
		// TODO Auto-generated constructor stub
		FileOutputStream fileOutputStream = new FileOutputStream(jarFileName);
		jarOutputStream = new JarOutputStream(fileOutputStream);
	}

	/**
	 * 向jar中添加文件
	 * 
	 * @param name
	 * @param bs
	 * @throws IOException
	 */
	public void write(byte[] b, int off, int len) throws IOException {
		jarOutputStream.write(b, off, len);
	}

	/**
	 * 放入jarEntry
	 * 
	 * @param name
	 * @throws IOException
	 */
	public void putJarEntry(String name) throws IOException {
		jarEntry = new JarEntry(name);
		jarOutputStream.putNextEntry(jarEntry);
	}

	public void closeEntry() throws IOException {
		jarOutputStream.closeEntry();
	}
}
