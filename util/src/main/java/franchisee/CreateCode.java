package franchisee;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import model.Franchisee;
import cache.service.keyobject.KeyObjectService;

import com.nitian.util.file.UtilFile;
import com.nitian.util.string.UtilString;

@SuppressWarnings("unchecked")
public class CreateCode {

	private List<Franchisee> list = new ArrayList<Franchisee>();
	private List<String> fieldList;
	private String ClassName;

	public static void main(String[] args) {
		CreateCode code = new CreateCode("WareKey");
		 System.out.println(code.createGridInitCode());
//		 System.out.println(code.createResourceCode());
//		System.out.println(code.createFormInitCode());
//		 System.out.println(code.createSelectFormInitCode());
//		 code.createLookUpController();
		// System.out.println(code.createLookUpControllerResourceCode());
	}

	/**
	 * 构造函数
	 */
	public CreateCode(String className) {
		try {
			this.list = ReadExcel.getFranchiseeList(className);
			String url = "//localhost:8029/SAMPLE-SERVER";
			KeyObjectService service = (KeyObjectService) Naming.lookup(url);
			setFieldList((List<String>) service.get("list"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.ClassName = className;
	}

	public String createLookUpControllerResourceCode() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		sb.append("//----------------------设置显示字段---------------------------");
		sb.append("\n");
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getNote() != null
					&& !franchisee.getNote().equals("")) {
				if (franchisee.getFormType().equals("lookup")) {
					sb.append("setVisibleColumn(\"");
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append("\", true);");
					sb.append("\n");
				}
			}
		}
		sb.append("\n");
		sb.append("//----------------------添加字段注释---------------------------");
		sb.append("\n");
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getNote() != null
					&& !franchisee.getNote().equals("")) {
				if (franchisee.getFormType().equals("lookup")) {
					sb.append("setHeaderColumnName(\"");
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append("\", \"");
					sb.append(franchisee.getNote());
					sb.append("\");");
					sb.append("\n");
				}
			}
		}
		return sb.toString();
	}

	public String createFormInitCode() {
		/**
		 * 将list转换为文件
		 */
		StringBuffer sb = new StringBuffer();
		// 加入注释和label
		sb.append("\n");
		sb.append("//----------------------加入label---------------------------");
		sb.append("\n");
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				sb.append("//");
				sb.append(franchisee.getNote());
				sb.append("\n");
				sb.append("LabelControl ");
				sb.append("label");
				sb.append(franchisee.getFieldName());
				sb.append(" = new LabelControl();");
				sb.append("\n");
			}
		}
		sb.append("\n");
		sb.append("//----------------------加入control---------------------------");
		sb.append("\n");
		// 加入control
		/**
		 * 需要对formType进行判断
		 */
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				if (franchisee.getFormType().equals("text")) {
					getControl("TextControl", franchisee, sb);
				} else if (franchisee.getFormType().equals("lookup")) {
					getControl("CodLookupControl", franchisee, sb);
				} else if (franchisee.getFormType().equals("check")) {
					getControl("CheckBoxControl", franchisee, sb);
				} else if (franchisee.getFormType().equals("num")) {
					getControl("NumericControl", franchisee, sb);
				} else if (franchisee.getFormType().equals("bigdec")) {
					getControl("NumericControl", franchisee, sb);
				} else if (franchisee.getFormType().equals("date")) {
					getControl("DateControl", franchisee, sb);
				} else if (franchisee.getFormType().equals("combobox")) {
					getControl("ComboBoxControl", franchisee, sb);
				}
				sb.append("\n");
			}
		}
		sb.append("\n");
		sb.append("//----------------------设置label的text---------------------------");
		sb.append("\n");
		// 设置label的text
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				sb.append("label");
				sb.append(franchisee.getFieldName());
				sb.append(".setText(\"");
				sb.append(franchisee.getNote());
				sb.append("\");");
				sb.append("\n");
			}
		}

		sb.append("\n");
		sb.append("//----------------------设置布局---------------------------");
		sb.append("\n");
		// 设置布局
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				if (!franchisee.getFormLayout().equals("")) {
					sb.append("UtilGridLayout.add(formPanel, ");
					sb.append("label");
					sb.append(franchisee.getFieldName());
					sb.append(", \"");
					sb.append(franchisee.getFormLayout());
					sb.append("\");");
					sb.append("\n");
					sb.append("UtilGridLayout.add(formPanel, ");
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(", \"");
					sb.append(franchisee.getFormLayout());
					sb.append("\");");
					sb.append("\n");
				}
			}
		}
		sb.append("\n");
		sb.append("//----------------------设置lookupController---------------------------");
		sb.append("\n");
		// 设置lookupController
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				if (franchisee.getFormType().equals("lookup")) {
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(".setLookupController(new ");
					sb.append(UtilString.letterUpper(ClassName));
					sb.append(franchisee.getFieldName());
					sb.append("LookUpController());");
					sb.append("\n");
				}
			}
		}
		sb.append("\n");
		sb.append("//----------------------设置num小数点---------------------------");
		sb.append("\n");
		// 设置num小数点
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				if (franchisee.getFormType().equals("num")) {
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(".setDecimals(0);");
					sb.append("\n");
				} else if (franchisee.getFormType().equals("bigdec")) {
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(".setDecimals(2);");
					sb.append("\n");
				}
			}
		}
		sb.append("\n");
		sb.append("//----------------------设置combobox值---------------------------");
		sb.append("\n");
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				if (franchisee.getFormType().equals("combobox")) {
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(".setDomainId(\"" + franchisee.getFieldName()
							+ "Combobox" + "\");");
					sb.append("\n");
				}
			}
		}

		return sb.toString();
	}

	private void getControl(String controlName, Franchisee franchisee,
			StringBuffer sb) {
		sb.append(controlName);
		sb.append(" ");
		sb.append(UtilString.letterLower(franchisee.getFieldName()));
		// TextControl dH = (TextControl) UtilControlFactory.getControl("text",
		// "", false, false, false);
		sb.append(" = (");
		sb.append(controlName);
		sb.append(") UtilControlFactory.getControl(\"");
		sb.append(franchisee.getFormType());
		sb.append("\", \"");
		String fieldName = getField(franchisee.getFieldName());
		if (fieldName == null) {
			sb.append(UtilString.letterLower(franchisee.getFieldName()));
		} else {
			sb.append(fieldName);
		}
		sb.append("\", false, false, false);");
	}

	
	private void getColumn(String controlName, Franchisee franchisee,
			StringBuffer sb){
		sb.append(controlName);
		sb.append(" ");
		sb.append(UtilString.letterLower(franchisee.getFieldName()));
		sb.append(" = (");
		sb.append(controlName);
		sb.append(") UtilColumnFactory.getControl(\"");
		sb.append(franchisee.getFormType());
		sb.append("\", \"");
		String fieldName = getField(franchisee.getFieldName());
		if (fieldName == null) {
			sb.append(UtilString.letterLower(franchisee.getFieldName()));
		} else {
			sb.append(fieldName);
		}
		sb.append("\", \"");
		sb.append(franchisee.getNote());
		sb.append("\", false, false, false);");
		sb.append("\n");
	}
	/**
	 * 生成表格Init
	 * 
	 * @return
	 */
	public String createGridInitCode() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		sb.append("//----------------------生成字段---------------------------");
		sb.append("\n");
		// 生成TextColumn，也就是表格字段
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getNote() != null
					&& !franchisee.getNote().equals("")) {
				if (franchisee.getFormType().equals("text")) {
					getColumn("TextColumn", franchisee, sb);
				} else if (franchisee.getFormType().equals("bigdec")) {
					getColumn("DecimalColumn", franchisee, sb);
				} else if (franchisee.getFormType().equals("num")) {
					getColumn("DecimalColumn", franchisee, sb);
				} else if (franchisee.getFormType().equals("date")) {
					getColumn("DateColumn", franchisee, sb);
				} else if (franchisee.getFormType().equals("check")) {
					getColumn("CheckBoxColumn", franchisee, sb);
				} else if (franchisee.getFormType().equals("lookup")) {
					getColumn("CodLookupColumn", franchisee, sb);
				}

			}
		}

		sb.append("\n");
		sb.append("//----------------------设置小数点---------------------------");
		sb.append("\n");
		// 设置小数点
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getNote() != null
					&& !franchisee.getNote().equals("")) {
				if (franchisee.getFormType().equals("bigdec")) {
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(".setDecimals(2);");
					sb.append("\n");
				} else if (franchisee.getFormType().equals("num")) {
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(".setDecimals(0);");
					sb.append("\n");
				}
			}
		}


		sb.append("\n");
		sb.append("//----------------------设置宽度---------------------------");
		sb.append("\n");
		// 设置宽度
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getNote() != null
					&& !franchisee.getNote().equals("")) {
				sb.append(UtilString.letterLower(franchisee.getFieldName()));
				sb.append(".setPreferredWidth(200);");
				sb.append("\n");
			}
		}

		sb.append("\n");
		sb.append("//----------------------添加到表格---------------------------");
		sb.append("\n");
		// 设置宽度
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getNote() != null
					&& !franchisee.getNote().equals("")) {
				sb.append("grid.getColumnContainer().add(");
				sb.append(UtilString.letterLower(franchisee.getFieldName()));
				sb.append(", null);");
				sb.append("\n");
			}
		}

		sb.append("\n");
		sb.append("//----------------------添加map控制---------------------------");
		sb.append("\n");
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getNote() != null
					&& !franchisee.getNote().equals("")) {
				sb.append("// ");
				sb.append(franchisee.getNote());
				sb.append("\n");
				sb.append("columnVisibleMap.put(\"");
				sb.append(UtilString.letterLower(franchisee.getFieldName()));
				sb.append("\", true);");
				sb.append("\n");
			}
		}

		sb.append("\n");
		sb.append("//----------------------添加map判断---------------------------");
		sb.append("\n");
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getNote() != null
					&& !franchisee.getNote().equals("")) {
				sb.append("if (columnVisibleMap.get(\"");
				sb.append(UtilString.letterLower(franchisee.getFieldName()));
				sb.append("\") != null ");
				sb.append("&& columnVisibleMap.get(\"");
				sb.append(UtilString.letterLower(franchisee.getFieldName()));
				sb.append("\") == true) {");
				sb.append("\n");
				sb.append(UtilString.letterLower(franchisee.getFieldName()));
				sb.append(".setColumnVisible(true);");
				sb.append("\n");
				sb.append("} else {");
				sb.append("\n");
				sb.append(UtilString.letterLower(franchisee.getFieldName()));
				sb.append(".setColumnVisible(false);");
				sb.append("\n");
				sb.append("}");
				sb.append("\n");
				sb.append("\n");
			}
		}

		sb.append("\n");
		sb.append("//----------------------添加lookupcontroller---------------------------");
		sb.append("\n");
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getNote() != null
					&& !franchisee.getNote().equals("")) {
				if (franchisee.getFormType().equals("lookup")) {
					String className = UtilString.letterUpper(ClassName)
							+ franchisee.getFieldName()
							+ "LookUpController());";
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(".setLookupController(new ");
					sb.append(className);
					sb.append("\n");
				}
			}
		}
		return sb.toString();
	}

	public String createResourceCode() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		sb.append("//----------------------生成资源---------------------------");
		sb.append("\n");
		// 生成资源
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getNote() != null
					&& !franchisee.getNote().equals("")) {
				sb.append("<object key=\"");
				sb.append(UtilString.letterLower(ClassName));
				sb.append(franchisee.getFieldName());
				sb.append("\" value=\"");
				sb.append(franchisee.getNote());
				sb.append("\"/>");
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	/**
	 * 创建lookUpController文件
	 */
	public void createLookUpController() {
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getNote() != null
					&& !franchisee.getNote().equals("")) {
				if (franchisee.getFormType().equals("lookup")) {
					String className = UtilString.letterUpper(ClassName)
							+ franchisee.getFieldName() + "LookUpController";
					String template = UtilFile
							.fileToString("C:\\Users\\1036225283\\Desktop\\swing\\template\\franchisee\\lookUpController.java");
					template = template.replace("<ClassName>", className);
					UtilFile.stringToFile(template,
							"C:\\Users\\1036225283\\Desktop\\config\\"
									+ className + ".java");
				}
			}
		}
	}

	/**
	 * 比对字段，如果存在，则返回fieldList中对应的字段，否则返回null
	 */
	private String getField(String fieldName) {
		for (int i = 0; i < fieldList.size(); i++) {
			if (fieldName.toLowerCase().equals(fieldList.get(i).toLowerCase())) {
				return fieldList.get(i);
			}
		}
		return null;
	}

	/**
	 * 生成查询map表单
	 */
	public String createSelectFormInitCode() {
		/**
		 * 将list转换为文件
		 */
		StringBuffer sb = new StringBuffer();
		// 加入注释和label
		sb.append("\n");
		sb.append("//----------------------加入label---------------------------");
		sb.append("\n");
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				sb.append("//");
				sb.append(franchisee.getNote());
				sb.append("\n");
				sb.append("LabelControl ");
				sb.append("label");
				sb.append(franchisee.getFieldName());
				sb.append(" = new LabelControl();");
				sb.append("\n");
			}
		}
		sb.append("\n");
		sb.append("//----------------------加入control---------------------------");
		sb.append("\n");
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				if (franchisee.getFormType().equals("text")) {
					sb.append("TextControl ");
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(" = new TextControl();");
				} else if (franchisee.getFormType().equals("lookup")) {
					sb.append("CodLookupControl ");
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(" = new CodLookupControl();");
				} else if (franchisee.getFormType().equals("check")) {
					sb.append("CheckBoxControl ");
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(" = new CheckBoxControl();");
				} else if (franchisee.getFormType().equals("num")) {
					sb.append("NumericControl ");
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(" = new NumericControl();");
				} else if (franchisee.getFormType().equals("bigdec")) {
					sb.append("NumericControl ");
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(" = new NumericControl();");
				} else if (franchisee.getFormType().equals("date")) {
					sb.append("DateControl ");
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(" = new DateControl();");
				} else if (franchisee.getFormType().equals("combobox")) {
					sb.append("ComboBoxControl ");
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(" = new ComboBoxControl();");
				}
				sb.append("\n");
			}
		}
		sb.append("\n");
		sb.append("//----------------------设置label的text---------------------------");
		sb.append("\n");
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				sb.append("label");
				sb.append(franchisee.getFieldName());
				sb.append(".setText(\"");
				sb.append(franchisee.getNote());
				sb.append("\");");
				sb.append("\n");
			}
		}

		sb.append("\n");
		sb.append("//----------------------设置control的属性---------------------------");
		sb.append("\n");
		// 设置control的属性
		/**
		 * 需要对formType进行判断
		 */
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				sb.append(UtilString.letterLower(franchisee.getFieldName()));
				sb.append(".setAttributeName(\"");
				sb.append(UtilString.letterLower(franchisee.getFieldName()));
				sb.append("\");");
				sb.append("\n");
			}
		}

		sb.append("\n");
		sb.append("//----------------------设置必填属性---------------------------");
		sb.append("\n");
		// 设置必填属性
		/**
		 * 需要对formType进行判断
		 */
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				sb.append("// ");
				sb.append(franchisee.getNote());
				sb.append("\n");
				sb.append(UtilString.letterLower(franchisee.getFieldName()));
				sb.append(".setRequired(false);");
				sb.append("\n");
			}
		}
		sb.append("\n");
		sb.append("//----------------------设置编辑时编辑属性---------------------------");
		sb.append("\n");
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				sb.append("// ");
				sb.append(franchisee.getNote());
				sb.append("\n");
				sb.append(UtilString.letterLower(franchisee.getFieldName()));
				sb.append(".setEnabledOnEdit(false);");
				sb.append("\n");
			}
		}

		sb.append("\n");
		sb.append("//----------------------设置新增时编辑属性---------------------------");
		sb.append("\n");
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				sb.append("// ");
				sb.append(franchisee.getNote());
				sb.append("\n");
				sb.append(UtilString.letterLower(franchisee.getFieldName()));
				sb.append(".setEnabledOnInsert(false);");
				sb.append("\n");
			}
		}

		sb.append("\n");
		sb.append("//----------------------设置布局---------------------------");
		sb.append("\n");
		// 设置布局
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				if (!franchisee.getFormLayout().equals("")) {
					String[] strings = franchisee.getFormLayout().split(" ");
					Integer row = Integer.valueOf(strings[0]) - 1;
					Integer column = Integer.valueOf(strings[1]);
					Integer columnLabel = column * 2 - 2;
					Integer columnControl = column * 2 - 1;
					sb.append("formPanel.add(");
					sb.append("label");
					sb.append(franchisee.getFieldName());
					sb.append(", new GridBagConstraints(");
					sb.append(columnLabel);
					sb.append(", ");
					sb.append(row);
					sb.append(", 1, 1, 0.0, 0.0,");
					sb.append("\n");
					sb.append("GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,");
					sb.append("\n");
					sb.append("new Insets(5, 5, 5, 5), 0, 0));");
					sb.append("\n");
					//
					sb.append("formPanel.add(");
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(", new GridBagConstraints(");
					sb.append(columnControl);
					sb.append(", ");
					sb.append(row);
					sb.append(", 1, 1, 0.0, 0.0,");
					sb.append("\n");
					sb.append("GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,");
					sb.append("\n");
					sb.append("new Insets(5, 5, 5, 5), 0, 0));");
					sb.append("\n");
					sb.append("\n");
				}
			}
		}
		sb.append("\n");
		sb.append("//----------------------设置lookupController---------------------------");
		sb.append("\n");
		// 设置lookupController
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				if (franchisee.getFormType().equals("lookup")) {
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(".setLookupController(new ");
					sb.append(UtilString.letterUpper(ClassName));
					sb.append(franchisee.getFieldName());
					sb.append("LookUpController());");
					sb.append("\n");
				}
			}
		}
		sb.append("\n");
		sb.append("//----------------------设置num小数点---------------------------");
		sb.append("\n");
		// 设置num小数点
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				if (franchisee.getFormType().equals("num")) {
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(".setDecimals(0);");
					sb.append("\n");
				} else if (franchisee.getFormType().equals("bigdec")) {
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(".setDecimals(2);");
					sb.append("\n");
				}
			}
		}
		sb.append("\n");
		sb.append("//----------------------设置combobox值---------------------------");
		sb.append("\n");
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				if (franchisee.getFormType().equals("combobox")) {
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(".setDomainId(\"" + franchisee.getFieldName()
							+ "Combobox" + "\");");
					sb.append("\n");
				}
			}
		}

		sb.append("\n");
		sb.append("//----------------------生成map查询---------------------------");
		sb.append("\n");
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				if (franchisee.getFormType().equals("text")) {
					sb.append("if (");
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(".getValue() != null && !");
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(".getValue().toString().trim().equals(\"\")) {");
					sb.append("\n");
					sb.append("map.put(\"");
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append("\", ");
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(".getValue().toString().trim());");
					sb.append("\n");
					sb.append("}");
					sb.append("\n");
				} else {
					sb.append("if (");
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(".getValue() != null) {");
					sb.append("\n");
					sb.append("map.put(\"");
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append("\", ");
					sb.append(UtilString.letterLower(franchisee.getFieldName()));
					sb.append(".getValue());");
					sb.append("\n");
					sb.append("}");
					sb.append("\n");
				}
			}
		}

		return sb.toString();
	}

	public List<String> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<String> fieldList) {
		this.fieldList = fieldList;
	}

}
