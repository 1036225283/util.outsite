package utilsystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class UtilMysqlAndExcel {

	public static Connection connection;
	public static String sql = "";
	public static ResultSetMetaData resultSetMetaData;
	public static int count = 0;
	public static DatabaseMetaData databaseMetaData;
	public static ResultSet tableSet;
	public static String tableName = "config";
	public static ResultSet fieldSet;
	public static String fieldName;

	public static WritableWorkbook writableWorkbook = null;
	public static WritableSheet sheet = null;
	public static Workbook workbook;

	/**
	 * 将数据库中的表字段读取出来生成excel文件
	 * 
	 * @throws Exception
	 */
	public static void createExcel() throws Exception {
		String url = "jdbc:mysql://203.195.200.95:3306/j2ee";
		String username = "root";
		String password = "q@1036225283";

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
		/**
		 * 新建excel文件
		 */
		writableWorkbook = Workbook.createWorkbook(new File("f://" + tableName
				+ ".xls"));
		sheet = writableWorkbook.createSheet(tableName, 0);
		/**
		 * 获取表的所有字段
		 */
		if (resultSetMetaData != null) {
			count = resultSetMetaData.getColumnCount();
		}
		for (int i = 1; i <= count; i++) {
			fieldName = resultSetMetaData.getColumnName(i);
			System.out.println(i);
			Label field = new Label(0, i - 1, fieldName);
			sheet.addCell(field);
			sheet.setColumnView(0, 30);
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
		InputStream instream = new FileInputStream("F:/红楼人物.xls");

		Workbook workbook = Workbook.getWorkbook(instream);
		Sheet sheet = workbook.getSheet(0);
		int columns = sheet.getColumns();
		int rows = sheet.getRows();
		for(int i=1;i<rows;i++){
			for(int j=0;j<columns;j++){
				
			}
		}
		
	}

	public static void main(String[] args) throws Exception {
		createExcel();
	}
}
