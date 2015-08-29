package com.nitian.util.code;

import java.util.List;

import com.nitian.util.code.model.ClassModel;
import com.nitian.util.code.model.Code;

public class UtilCreateParamCode {

	public static void main(String[] args) {
		createParamCode(Code.c);
	}

	public static void createParamCode(Class<?> c) {
		List<ClassModel> classModels = Code.readClassModelInfo(c);
		StringBuffer sb = new StringBuffer();
		for (ClassModel classModel : classModels) {
			sb.append("\n");
			sb.append("@RequestParam(value = \"");
			sb.append(classModel.getFieldName());
			sb.append("\", required = false) ");
			if (classModel.getType().equals("java.lang.Long")) {
				sb.append("Long ");
			} else if (classModel.getType().equals("java.util.Date")) {
				sb.append("Date ");
			} else if (classModel.getType().equals("java.lang.Integer")) {
				sb.append("Integer ");
			} else if (classModel.getType().equals("java.lang.Boolean")) {
				sb.append("Boolean ");
			} else if (classModel.getType().equals("java.lang.String")) {
				sb.append("String ");
			}
			sb.append(classModel.getFieldName());
			sb.append(",");
		}

		System.out.println(sb.toString());
	}

}
