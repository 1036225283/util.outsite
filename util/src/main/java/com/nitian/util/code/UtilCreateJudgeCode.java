package com.nitian.util.code;

import java.util.List;

import com.nitian.util.code.model.ClassModel;
import com.nitian.util.code.model.Code;

public class UtilCreateJudgeCode {

	public static void main(String[] args) {
		createJudgeCode(Code.c);
	}

	/**
	 * 创建java后台验证代码
	 * 
	 * @param c
	 */
	public static void createJudgeCode(Class<?> c) {
		List<ClassModel> classModels = Code.readClassModelInfo(c);

		StringBuffer sb = new StringBuffer();

		/**
		 * 创建后台验证代码
		 */
		for (ClassModel classModel : classModels) {
			if (classModel.getType().equals("java.lang.String")) {
				sb.append("if (UtilString.judgeIsNull(");
				sb.append(classModel.getFieldName());
				sb.append(") == null) {");
				sb.append("\n");
				sb.append("\t");
				sb.append("return UtilResult.returnResult(false, \"");
				sb.append(classModel.getNote());
				sb.append("不能为空\");");
				sb.append("\n");
				sb.append("}");
				sb.append("\n");
				sb.append("\t");
			} else {
				sb.append("if (");
				sb.append(classModel.getFieldName());
				sb.append(" == null) {");
				sb.append("\n");
				sb.append("\t");
				sb.append("return UtilResult.returnResult(false, \"");
				sb.append(classModel.getNote());
				sb.append("不能为空\");");
				sb.append("\n");
				sb.append("}");
				sb.append("\n");
				sb.append("\t");
			}
			System.out.println(classModel.getType());
		}

		System.out.println(sb.toString());
	}

}
