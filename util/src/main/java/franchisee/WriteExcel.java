package franchisee;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import model.Franchisee;

public class WriteExcel {

	public static Connection connection;
	public static String sql = "";
	public static ResultSetMetaData resultSetMetaData;
	public static int count = 0;
	public static DatabaseMetaData databaseMetaData;
	public static ResultSet tableSet;
	public static String tableName = "WareKey";
	public static ResultSet fieldSet;
	public static String fieldName;

	public static WritableWorkbook writableWorkbook = null;
	public static WritableSheet sheet = null;
	public static Workbook workbook;

	public static String excelOutPath = "C:\\Users\\1036225283\\Desktop\\swing\\excel\\franchisee\\";
	public static List<Franchisee> list = new ArrayList<Franchisee>();
	public static String templatePath = "C:\\Users\\1036225283\\Desktop\\swing\\template\\franchisee\\";

	public static File excelFile;

	public static void main(String[] args) throws Exception {
		 createExcel();
//		createExcelByClass(null);
	}

	/**
	 * 将数据库中的表字段读取出来生成excel文件
	 * 
	 * @throws Exception
	 */
	public static void createExcel() throws Exception {
		String url = "jdbc:mysql://192.168.1.99:3306/product";
		String username = "root";
		String password = "root";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		}

		try {
			connection = (Connection) DriverManager.getConnection(url,
					username, password);
		} catch (SQLException se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}

		sql = "select * from " + tableName;

		fieldSet = connection.createStatement().executeQuery(sql);
		resultSetMetaData = (ResultSetMetaData) fieldSet.getMetaData();

		createExcelFile();

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

	@SuppressWarnings("rawtypes")
	public static void createExcelByClass(Class clazz) throws Exception {
		createExcelFile();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Label field = new Label(0, i+1, fields[i].getName());
			sheet.addCell(field);
			sheet.setColumnView(0, 20);
		}
		writableWorkbook.write();
		writableWorkbook.close();
	}

	public static void createExcelFile() throws Exception {
		/**
		 * 新建excel文件
		 */
		excelFile = new File(excelOutPath + tableName + ".xls");
		if (excelFile.exists()) {
			throw new RuntimeException("文件已经存在");
		}
		writableWorkbook = Workbook.createWorkbook(excelFile);
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
	}

}
