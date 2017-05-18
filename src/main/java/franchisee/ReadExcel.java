package franchisee;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import model.Franchisee;

public class ReadExcel {
	
	public static String excelIntPath = "C:\\Users\\1036225283\\Desktop\\swing\\excel\\franchisee\\";
	public static List<Franchisee> list = new ArrayList<Franchisee>();

	/**
	 * 读取excel文件
	 * 
	 * @throws IOException
	 * @throws
	 */
	public static List<Franchisee> getFranchiseeList(String className) throws Exception {
		/**
		 * 将excel文件转换为数据列表
		 */
		InputStream instream = new FileInputStream(excelIntPath + className + ".xls");
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
		return list;
	}

}
