package com.nitian.util.code.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注释注解
 * 
 * @author 1036225283
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Note {

	/**
	 * 注释
	 * 
	 * @return
	 */
	String value();

	/**
	 * 可为空
	 * 
	 * @return
	 */
	public boolean nullable() default true;
}
