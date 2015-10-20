package com.nitian.util.java;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class UtilClass {

	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\1036225283\\Desktop");
		System.out.println(file.toURI().toURL());
		URL[] urls = new URL[] { new URL(file.toURI().toURL().toString()) };
		URLClassLoader loader = new URLClassLoader(urls, Thread.currentThread()
				.getContextClassLoader());
		// URLClassLoader myClassLoader = new URLClassLoader(
		// new URL[] { url }, Thread.currentThread().getContextClassLoader());
		// Class c = ul.loadClass("com.nitian.util.java.Test");
		Class c = Class.forName("com.nitian.util.java.Test", true, loader);
		System.out.println(c);

		Object object = c.newInstance();
		Method method = c.getDeclaredMethod("show", null);
		method.invoke(object, null);

	}

}
