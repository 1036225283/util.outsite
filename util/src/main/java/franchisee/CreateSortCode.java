package franchisee;

import java.util.ArrayList;
import java.util.List;

import com.nitian.util.string.UtilString;

import model.Franchisee;

public class CreateSortCode {

	private List<Franchisee> list = new ArrayList<Franchisee>();

	public static void main(String[] args) {
		CreateSortCode createSortCode = new CreateSortCode("WareShopCount");
		createSortCode.createSortSqlCode();
		createSortCode.createClientCode();
		createSortCode.createColumnSortCode();
	}

	/**
	 * 生成排序sql代码
	 */
	public void createSortSqlCode() {
		StringBuffer sb = new StringBuffer();

		for (Franchisee franchisee : list) {
			String orderBy = "orderBy"
					+ UtilString.letterUpper(franchisee.getFieldName());
			sb.append("<if test=\"");
			sb.append(orderBy);
			sb.append(" != null and ASC != null\">");
			sb.append("\n");
			sb.append("order by ");
			sb.append(UtilString.letterLower(franchisee.getFieldName()));
			sb.append(" asc");
			sb.append("\n");
			sb.append("</if>");
			sb.append("\n");

			sb.append("<if test=\"");
			sb.append(orderBy);
			sb.append(" != null and DESC != null\">");
			sb.append("\n");
			sb.append("order by ");
			sb.append(UtilString.letterLower(franchisee.getFieldName()));
			sb.append(" desc");
			sb.append("\n");
			sb.append("</if>");
			sb.append("\n");
		}

		System.out.println(sb.toString());
		System.out
				.println("------------------------------------------------------传教sql排序代码----------------------------------------------");

	}

	/**
	 * 创建客户端代码
	 */
	public void createClientCode() {
		StringBuffer sb = new StringBuffer();

		for (Franchisee franchisee : list) {
			sb.append("valueMap.remove(\"");
			sb.append("orderBy");
			sb.append(UtilString.letterUpper(franchisee.getFieldName()));
			sb.append("\");");
			sb.append("\n");
		}

		sb.append("valueMap.remove(\"DESC\");");
		sb.append("\n");
		sb.append("valueMap.remove(\"ASC\");");

		sb.append("if (currentSortedColumns.size() != 0) {");
		sb.append("\n");
		sb.append("valueMap.put(\"orderBy\" + UtilString.letterUpper(currentSortedColumns.get(0)+\"\"),");
		sb.append("\n");
		sb.append("currentSortedColumns);");
		sb.append("}");
		sb.append("\n");
		sb.append("if (currentSortedVersusColumns.size() != 0) {");
		sb.append("\n");
		sb.append("valueMap.put(currentSortedVersusColumns.get(0) + \"\",");
		sb.append("\n");
		sb.append("currentSortedVersusColumns);");
		sb.append("\n");
		sb.append("}");
		System.out.println(sb.toString());
		System.out
				.println("-------------------------------------------------创建客户端代码----------------------------------------------");
	}

	/**
	 * 
	 */
	/**
	 * 创建字段排序代码
	 */
	public void createColumnSortCode() {
		StringBuffer sb = new StringBuffer();
		sb.append("// ----------------------设置排序---------------------------");
		sb.append("\n");

		for (Franchisee franchisee : list) {
			sb.append(UtilString.letterLower(franchisee.getFieldName()));
			sb.append(".setColumnSortable(true);");
			sb.append("\n");
		}

		System.out.println(sb.toString());
		System.out
				.println("-------------------------------------------字段添加排序属性-------------------------------------------");
	}

	public CreateSortCode(String className) {
		// TODO Auto-generated constructor stub
		try {
			this.list = ReadExcel.getFranchiseeList(className);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
