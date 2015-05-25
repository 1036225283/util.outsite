package code.spring_mvc_hibernate;

import com.nitian.util.file.UtilFile;
import com.nitian.util.string.UtilString;

public class CreateCode {

	private String entityName;

	private String outPath;

	public static void main(String[] args) {
		CreateCode code = new CreateCode("MemberCar",
				"F:\\spring-mvc\\spring-mvc\\src\\main\\java\\project\\");
		code.write();
	}

	public CreateCode(String entityName, String outPath) {
		// TODO Auto-generated constructor stub
		this.entityName = entityName;
		this.outPath = outPath;
	}

	public void write() {
		String daoPath = (CreateCode.class.getResource("").toString() + "template/daoTemplate.tlt")
				.replace("file:/", "");
		String servicePath = (CreateCode.class.getResource("").toString() + "template/serviceTemplate.tlt")
				.replace("file:/", "");

		String daoOutPath = outPath + entityName + "Dao.java";
		String serviceOutPath = outPath + entityName + "Service.java";

		String daoString = UtilFile.fileToString(daoPath);
		String serviceString = UtilFile.fileToString(servicePath);

		daoString = daoString.replace("#EntityName#", entityName);
		serviceString = serviceString.replace("#EntityName#", entityName);
		String lowerEntityName = UtilString.letterLower(entityName);
		serviceString = serviceString.replace("#entityName#", lowerEntityName);

		UtilFile.stringToFile(daoString, daoOutPath);
		UtilFile.stringToFile(serviceString, serviceOutPath);
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getOutPath() {
		return outPath;
	}

	public void setOutPath(String outPath) {
		this.outPath = outPath;
	}
}
