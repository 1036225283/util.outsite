package com.nitian.util.code.model;

/**
 * 类的相关信息
 * 
 * @author 1036225283
 *
 */
public class ClassModel {

	private String fieldName;// 字段名

	private String note;// 注释

	private String type;// 数据类型

	private Boolean nullable;// 是否可以为空，默认为可以

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getNullable() {
		return nullable;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

}
