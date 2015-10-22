package com.nitian.util.java;

import java.util.List;

import com.nitian.util.file.UtilFile;

public class UtilClass {

	public static void main(String[] args) throws Exception {
		String path = UtilClass.class.getProtectionDomain().getCodeSource()
				.getLocation().getPath().trim();
		if (UtilFile.judgeJar(path)) {
			String filePath = UtilFile.getJarPath(path);
			UtilFile.stringToFile(path, filePath + "\\duck.text");
		}
		String filePath = UtilFile.getJarPath(path);
		System.out.println(filePath);
		System.out.println(path);
		System.out.println("d");
	}

	/**
	 * 对指定目录使用rsa加密
	 * 
	 * @param path
	 * @param rsaPrivateKey
	 */
	public void encryptClass(String path, String rsaPrivateKey) {
		List<String> list = UtilFile.getAllFileName(path);
		for (String string : list) {

		}
	}

	public void unEncryptClass(String path, String rasPublicKey) {

	}

}
