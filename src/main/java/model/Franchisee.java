package model;

/**
 * 新梦奇模型
 * @author 1036225283
 *
 */
public class Franchisee {

	private String fieldName;
	
	private String note;
	
	private Boolean state;
	
	private String formType;
	
	private String formLayout;
	
	private Boolean required;
	
	private Boolean edit;

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

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getFormLayout() {
		return formLayout;
	}

	public void setFormLayout(String formLayout) {
		this.formLayout = formLayout;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public Boolean getEdit() {
		return edit;
	}

	public void setEdit(Boolean edit) {
		this.edit = edit;
	}
	
}
