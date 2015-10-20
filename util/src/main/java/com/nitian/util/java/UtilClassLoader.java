package com.nitian.util.java;

import java.io.File;

import com.nitian.util.file.UtilFile;

public class UtilClassLoader extends ClassLoader {

	public void test() {
		// this.defineClass(name, b, off, len)
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

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
		byte[] bs = UtilFile.fileToHex(fileName);
		return this.defineClass(className, bs, 0, bs.length);
	}

}
