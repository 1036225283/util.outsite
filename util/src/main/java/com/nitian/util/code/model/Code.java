package com.nitian.util.code.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Code {

	public static Class<?> c = null;

	/**
	 * 读取类的相关信息
	 * 
	 * @param c
	 * @return
	 */
	public static List<ClassModel> readClassModelInfo(Class<?> c) {
		List<ClassModel> list = new ArrayList<ClassModel>();

		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			ClassModel classModel = new ClassModel();
			classModel.setFieldName(field.getName());
			classModel.setType(field.getType().getName());
			// 获取字段上面的注解
			Note note = field.getAnnotation(Note.class);
			if (note != null) {
				classModel.setNote(note.value());
				classModel.setNullable(note.nullable());
			}
			list.add(classModel);
		}
		return list;
	}
}
