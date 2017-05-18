package utilsystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import model.Franchisee;

import com.nitian.util.file.UtilFile;
import com.nitian.util.string.UtilString;

public class UtilSqlServerAndExcel {

	public static Connection connection;
	public static String sql = "";
	public static ResultSetMetaData resultSetMetaData;
	public static int count = 0;
	public static DatabaseMetaData databaseMetaData;
	public static ResultSet tableSet;
	public static String tableName = "item";
	public static ResultSet fieldSet;
	public static String fieldName;

	public static WritableWorkbook writableWorkbook = null;
	public static WritableSheet sheet = null;
	public static Workbook workbook;

	public static String path = "C:\\Users\\1036225283\\Desktop\\config\\";
	public static List<Franchisee> list = new ArrayList<Franchisee>();
	public static String templatePath = "C:\\Users\\1036225283\\Desktop\\config\\template\\";
	public static String lookupController = templatePath
			+ "lookUpController.java";

	/**
	 * 将数据库中的表字段读取出来生成excel文件
	 * 
	 * @throws Exception
	 */
	public static void createExcel() throws Exception {
		String url = "jdbc:sqlserver://192.168.2.11:1433;databaseName=DzwAuto4S9;"
				+ "user=sa;password=sa";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		}

		try {
			connection = (Connection) DriverManager.getConnection(url);
		} catch (SQLException se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}

		sql = "select * from " + tableName;

		fieldSet = connection.createStatement().executeQuery(sql);
		resultSetMetaData = (ResultSetMetaData) fieldSet.getMetaData();
		/**
		 * 新建excel文件
		 */
		File file = new File(path + tableName + ".xls");
		if (file.exists()) {
			throw new RuntimeException("文件已经存在");
		}
		writableWorkbook = Workbook.createWorkbook(file);
		sheet = writableWorkbook.createSheet(tableName, 0);
		/**
		 * 添加excel表头
		 */
		Label field0 = new Label(0, 0, "字段名");
		sheet.addCell(field0);
		sheet.setColumnView(0, 20);
		Label note = new Label(1, 0, "注释");
		sheet.addCell(note);
		sheet.setColumnView(1, 20);
		Label state = new Label(2, 0, "状态");
		sheet.addCell(state);
		sheet.setColumnView(2, 20);
		// 表单类型为：文本控制，lookup控制等
		Label formType = new Label(3, 0, "表单类型");
		sheet.addCell(formType);
		sheet.setColumnView(3, 20);
		// 表单布局
		Label formLayout = new Label(4, 0, "表单布局");
		sheet.addCell(formLayout);
		sheet.setColumnView(4, 20);
		// 是否必填
		Label required = new Label(5, 0, "是否必填");
		sheet.addCell(required);
		sheet.setColumnView(5, 20);

		/**
		 * 获取表的所有字段
		 */
		if (resultSetMetaData != null) {
			count = resultSetMetaData.getColumnCount();
		}
		for (int i = 1; i <= count; i++) {
			fieldName = resultSetMetaData.getColumnName(i);
			System.out.println(i);
			Label field = new Label(0, i, fieldName);
			sheet.addCell(field);
			sheet.setColumnView(0, 20);
			System.out.println("fieldName = " + fieldName);
		}
		writableWorkbook.write();
		writableWorkbook.close();
	}

	/**
	 * 读取excel文件
	 * 
	 * @throws IOException
	 * @throws
	 */
	public static void readExcel() throws Exception {
		/**
		 * 将excel文件转换为数据列表
		 */
		InputStream instream = new FileInputStream(path + tableName + ".xls");
		Workbook workbook = Workbook.getWorkbook(instream);
		Sheet sheet = workbook.getSheet(0);
		int columns = sheet.getColumns();
		int rows = sheet.getRows();
		for (int i = 1; i < rows; i++) {
			Franchisee franchisee = new Franchisee();
			for (int j = 0; j < columns; j++) {
				Cell cell = sheet.getCell(j, i);
				if (j == 0) {
					franchisee.setFieldName(cell.getContents());
				} else if (j == 1) {
					franchisee.setNote(cell.getContents());
				} else if (j == 2) {
					if (cell.getContents().equals("true")) {
						franchisee.setState(true);
					} else if (cell.getContents().equals("false")) {
						franchisee.setState(false);
					}
				} else if (j == 3) {
					franchisee.setFormType(cell.getContents());
				} else if (j == 4) {
					franchisee.setFormLayout(cell.getContents());
				} else if (j == 5) {
					if (cell.getContents() != null
							&& !cell.getContents().equals("")) {
						if (cell.getContents().equals("true")) {
							franchisee.setRequired(true);
						}
					}
				} else if (j == 6) {
					if (cell.getContents() != null
							&& !cell.getContents().equals("")) {
						if (cell.getContents().equals("true")) {
							franchisee.setEdit(true);
						}
					}
				}
			}
			list.add(franchisee);
		}
	}

	public static void createFormCode() throws IOException {
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
					sb.append("TextControl ");
					sb.append("textControl");
					sb.append(franchisee.getFieldName());
					sb.append(" = new TextControl();");
				} else if (franchisee.getFormType().equals("lookup")) {
					sb.append("CodLookupControl ");
					sb.append("lookup");
					sb.append(franchisee.getFieldName());
					sb.append(" = new CodLookupControl();");
				} else if (franchisee.getFormType().equals("check")) {
					sb.append("CheckBoxControl ");
					sb.append("check");
					sb.append(franchisee.getFieldName());
					sb.append(" = new CheckBoxControl();");
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
				sb.append(UtilString.letterLower(tableName));
				sb.append(franchisee.getFieldName());
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
				if (franchisee.getFormType().equals("text")) {
					sb.append("textControl");
					sb.append(franchisee.getFieldName());
				} else if (franchisee.getFormType().equals("lookup")) {
					sb.append("lookup");
					sb.append(franchisee.getFieldName());
				} else if (franchisee.getFormType().equals("check")) {
					sb.append("check");
					sb.append(franchisee.getFieldName());
				}
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
				if (franchisee.getRequired() != null
						&& franchisee.getRequired() == true) {
					if (franchisee.getFormType().equals("text")) {
						sb.append("textControl");
						sb.append(franchisee.getFieldName());
					} else if (franchisee.getFormType().equals("lookup")) {
						sb.append("lookup");
						sb.append(franchisee.getFieldName());
					} else if (franchisee.getFormType().equals("check")) {
						sb.append("check");
						sb.append(franchisee.getFieldName());
					}
					sb.append(".setRequired(true);");
					sb.append("\n");
				}
			}
		}
		sb.append("\n");
		sb.append("//----------------------设置编辑属性l---------------------------");
		sb.append("\n");
		// 设置编辑属性
		/**
		 * 需要对formType进行判断
		 */
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				if (franchisee.getEdit() != null
						&& franchisee.getEdit() == true) {
					if (franchisee.getFormType().equals("text")) {
						sb.append("textControl");
						sb.append(franchisee.getFieldName());
					} else if (franchisee.getFormType().equals("lookup")) {
						sb.append("lookup");
						sb.append(franchisee.getFieldName());
					} else if (franchisee.getFormType().equals("check")) {
						sb.append("check");
						sb.append(franchisee.getFieldName());
					}
					sb.append(".setEnabledOnEdit(false);");
					sb.append("\n");
				}
			}
		}
		sb.append("\n");
		sb.append("//----------------------设置布局---------------------------");
		sb.append("\n");
		// 设置布局
		/**
		 * 需要对formType进行判断
		 */
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
					if (franchisee.getFormType().equals("text")) {
						sb.append("textControl");
					} else if (franchisee.getFormType().equals("lookup")) {
						sb.append("lookup");
					} else if (franchisee.getFormType().equals("check")) {
						sb.append("check");
					}
					sb.append(franchisee.getFieldName());
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
					sb.append("lookup");
					sb.append(franchisee.getFieldName());
					sb.append(".setLookupController(new ");
					sb.append(UtilString.letterUpper(tableName));
					sb.append(franchisee.getFieldName());
					sb.append("LookUpController());");
					sb.append("\n");
				}
			}
		}
		sb.append("\n");
		sb.append("//----------------------生成资源---------------------------");
		sb.append("\n");
		// 生成资源
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				sb.append("<object key=\"");
				sb.append(UtilString.letterLower(tableName));
				sb.append(franchisee.getFieldName());
				sb.append("\" value=\"");
				sb.append(franchisee.getNote());
				sb.append("\"/>");
				sb.append("\n");
			}
		}
		// 输出文件
		UtilFile.stringToFile(sb.toString(), path + tableName + ".java");

		// System.out.println(sb.toString());
	}

	/**
	 * 创建lookUpController文件
	 * 
	 * @throws IOException
	 */
	public static void createLookUpController() throws IOException {
		for (int i = 0; i < list.size(); i++) {
			Franchisee franchisee = list.get(i);
			if (franchisee.getState() != null && franchisee.getState() == true) {
				if (franchisee.getFormType().equals("lookup")) {
					String className = UtilString.letterUpper(tableName)
							+ franchisee.getFieldName() + "LookUpController";
					String template = UtilFile.fileToString(lookupController);
					template = template.replace("<ClassName>", className);
					UtilFile.stringToFile(template, path + className + ".java");
				}
			}
		}
	}

	public static void createGridCode() {

	}

	public static void main(String[] args) throws Exception {
		// createExcel();
		readExcel();
		createFormCode();
		createLookUpController();
	}
}
