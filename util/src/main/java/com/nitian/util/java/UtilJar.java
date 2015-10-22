package com.nitian.util.java;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class UtilJar {

	public static void main(String[] args) throws Exception {
		JarFile jarFile = new JarFile("C:\\Users\\1036225283\\Desktop\\xws.jar");
		Enumeration enums = jarFile.entries();
		while (enums.hasMoreElements()) {
			process(enums.nextElement());
		}
	}

	public List<JarEntry> getAllJar(String path) {
		try {
			JarFile jarFile = new JarFile(path);
			Enumeration enums = jarFile.entries();
			while (enums.hasMoreElements()) {
				process(enums.nextElement());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static void process(Object obj) {
		JarEntry entry = (JarEntry) obj;
		String name = entry.getName();
		long size = entry.getSize();
		long compressedSize = entry.getCompressedSize();
		System.out.println(name + " " + size + " " + compressedSize);
	}
}
