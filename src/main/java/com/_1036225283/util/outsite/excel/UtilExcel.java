package com._1036225283.util.outsite.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * 读取excel
 * 
 * @author 1036225283
 *
 */
public class UtilExcel {

	public static String excelPath;

	private Map<Integer, String> columnMap = new HashMap<Integer, String>();

	private Integer sheetNum = 1;

	private Excel readExcel = new Excel();
	private Excel writeExcel = new Excel();

	private String readExcelFilePath;

	private String writeExcelFilePath;

	public UtilExcel(String readExcelFilePath, String writeExcelFilePath) {
		// TODO Auto-generated constructor stub
		this.readExcelFilePath = readExcelFilePath;
		this.writeExcelFilePath = writeExcelFilePath;
	}

	/**
	 * 获取Workbook对象
	 * 
	 * @param fileName
	 * @return
	 */
	private Excel readExcel() {
		InputStream instream;
		Workbook workbook = null;
		try {
			instream = new FileInputStream(this.readExcelFilePath);
			workbook = Workbook.getWorkbook(instream);
			Sheet sheet = workbook.getSheet(sheetNum - 1);
			int columns = sheet.getColumns();
			int rows = sheet.getRows();

			readExcel.setWorkbook(workbook);
			readExcel.setSheet(sheet);
			readExcel.setRows(rows);
			readExcel.setColumns(columns);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return readExcel;
	}

	/**
	 * 根据行读取excel
	 * 
	 * @param fileName
	 * @return
	 */
	public List<Map<String, Object>> readExcelByRow() {
		readExcel();
		List<Map<String, Object>> rowList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < readExcel.getRows(); i++) {
			Map<String, Object> columnValueMap = new HashMap<String, Object>();
			for (int j = 0; j < readExcel.getColumns(); j++) {
				String key = columnMap.get(j);
				if (key == null) {
					key = String.valueOf(j);
				}
				String value = readExcel.getSheet().getCell(j, i).getContents();
				columnValueMap.put(key, value);
			}
			rowList.add(columnValueMap);
		}
		return rowList;
	}

	/**
	 * 根据列读取excel
	 * 
	 * @return
	 */
	public Map<String, List<String>> readExcelByColumn() {
		readExcel();
		Map<String, List<String>> columeValueMap = new HashMap<String, List<String>>();
		for (int i = 0; i < readExcel.getColumns(); i++) {
			List<String> columnList = new ArrayList<String>();

			for (int j = 0; j < readExcel.getRows(); j++) {
				String value = readExcel.getSheet().getCell(i, j).getContents();
				columnList.add(value);
			}
			String key = columnMap.get(i);
			if (key == null) {
				columeValueMap.put(String.valueOf(i), columnList);
			} else {
				columeValueMap.put(key, columnList);
			}
		}
		return columeValueMap;
	}

	public Map<Integer, String> getColumnMap() {
		return columnMap;
	}

	public void setColumnMap(Map<Integer, String> columnMap) {
		this.columnMap = columnMap;
	}

	public Integer getSheetNum() {
		return sheetNum;
	}

	public void setSheetNum(Integer sheetNum) {
		this.sheetNum = sheetNum;
	}

}
