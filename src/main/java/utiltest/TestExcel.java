package utiltest;

import java.util.List;
import java.util.Map;

import com._1036225283.util.outsite.excel.UtilExcel;

public class TestExcel {

	public static void main(String[] args) {
		UtilExcel utilExcel = new UtilExcel("C:\\Users\\1036225283\\Desktop\\excel\\会员.xls", "");
		List<Map<String, Object>> list = utilExcel.readExcelByRow();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
}
