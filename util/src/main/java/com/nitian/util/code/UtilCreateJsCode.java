package com.nitian.util.code;

import java.util.List;

import com.nitian.util.code.model.ClassModel;
import com.nitian.util.code.model.Code;

public class UtilCreateJsCode {

	public static void main(String[] args) {
		createJsCode(Code.c);
	}

	/**
	 * 创建js代码
	 */
	public static void createJsCode() {
		createJsCode(Code.c);
	}

	private static void createJsCode(Class<?> c) {
		List<ClassModel> classModels = Code.readClassModelInfo(c);

		StringBuffer sb = new StringBuffer();

		for (ClassModel classModel : classModels) {

			if (classModel.getType().equals("java.util.Date")) {
				sb.append(" $('#");
				sb.append(classModel.getFieldName());
				sb.append("').datetimepicker({ ");
				sb.append("\n");
				sb.append("\t");
				sb.append("language:'zh-CN',");
				sb.append("\n");
				sb.append("\t");
				sb.append("format: 'yyyy-mm-dd hh:ii:ss', ");
				sb.append("\n");
				sb.append("\t");
				sb.append("autoclose: true, ");
				sb.append("\n");
				sb.append("\t");
				sb.append("todayBtn: true, ");
				sb.append("\n");
				sb.append("\t");
				sb.append("startView:2, ");
				sb.append("\n");
				sb.append("\t");
				sb.append("minView:1, ");
				sb.append("\n");
				sb.append("\t");
				sb.append("forceParse: 0,  ");
				sb.append("\n");
				sb.append("\t");
				sb.append("weekStart: 1 ");
				sb.append("\n");
				sb.append("\t");
				sb.append("  }); ");

				sb.append("\n");
				sb.append("\n");
				sb.append("\n");
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}

}
