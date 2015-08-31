package com.nitian.util.code;

import java.util.List;

import com.nitian.util.code.model.ClassModel;
import com.nitian.util.code.model.Code;

public class UtilCreateHtmlCode {

	public static void main(String[] args) {
		createHtmlCode(Code.c);
	}

	/**
	 * 生成html和jsp代码
	 */
	public static void createHtmlCode() {
		createHtmlCode(Code.c);
	}

	/**
	 * 生成html和jsp代码
	 * 
	 * @param c
	 */
	private static void createHtmlCode(Class<?> c) {
		List<ClassModel> classModels = Code.readClassModelInfo(c);

		StringBuffer sb = new StringBuffer();

		for (ClassModel classModel : classModels) {

			if (classModel.getType().equals("java.util.Date")) {
				sb.append("<input type=\"text\" class=\"form_datetime  form-control\" placeholder=\"");
				sb.append(classModel.getNote());
				sb.append("\" name=\"");
				sb.append(classModel.getFieldName());
				sb.append("value=\"${");
				sb.append(classModel.getFieldName());
				sb.append("}\" id=\"");
				sb.append(classModel.getFieldName());
				sb.append("\"> <br>");
				sb.append("\n");
				continue;
			}

			sb.append("<input type=\"text\" placeholder=\"");
			sb.append(classModel.getNote());
			sb.append("\" class=\"form-control\" name=\"");
			sb.append(classModel.getFieldName());
			sb.append("\"><br>");
			sb.append("\n");

			System.out.println(classModel.getType());
		}

		System.out
				.println("输出表单代码------------------------------------------------");
		System.out.println(sb.toString());

	}

}
