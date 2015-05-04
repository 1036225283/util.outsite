package franchisee;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.nitian.util.string.UtilString;

import utilsystem.UtilFileString;

public class CreateClass {

	// 需要操作的类名，需要自己设定
	private String ClassName = "WareKey";

	public static void main(String[] args) {

		CreateClass fuck = new CreateClass();
		// 创建Service ServiceImpl Vo Controller ,修改Mapper
//		fuck.readMapperFile();
//		fuck.createServiceFile();
//		fuck.createServiceImplFile();
//		fuck.createControllerFile();
//		fuck.createVoFile();
		// 创建单独的gridFrame gridFrameController detailFrame detailFrameController
//		 fuck.createGridFrameFile();
//		 fuck.createGridFrameControllerFile();
//		 fuck.createDetailFrameFile();
//		 fuck.createDetailFrameControllerFile();

		// 创建frame，包含detailJpanel+controller+gridJpanel+gridJpanelController
//		fuck.createFrame();
//		fuck.createDetailJPanelFile();
//		fuck.createDetailJPanelControllerFile();
//		fuck.createGridJPanelFile();
//		fuck.createGridJPanelControllerFile();
		// 创建noSelectControllerFrame，包含formJpanel+gridJpanel+girdJpanelController
//		fuck.createNoSelectControllerFrame();
//		fuck.createFormJpanel();
//		fuck.createGridJPanelFile();
	}

	// 模板根目录
	private String templatePath = "C:\\Users\\1036225283\\Desktop\\swing\\template\\franchisee\\";

	// 生成文件的输出目录
	private String voOutPath = "C:\\Users\\1036225283\\Desktop\\franchisee\\common\\src\\main\\java\\com\\simonizchina\\franchisee\\vo\\";
	private String controllerOutPath = "C:\\Users\\1036225283\\Desktop\\franchisee\\server\\src\\main\\java\\com\\simonizchina\\franchisee\\server\\";
	private String clientOutPath = "C:\\Users\\1036225283\\Desktop\\franchisee\\client\\src\\main\\java\\com\\simonizchina\\franchisee\\client\\";
	private String serviceOutPath = "C:\\Users\\1036225283\\Desktop\\franchisee\\common\\src\\main\\java\\com\\simonizchina\\franchisee\\service\\";
	private String modelReadPath = "C:\\Users\\1036225283\\Desktop\\franchisee\\common\\src\\main\\java\\com\\simonizchina\\franchisee\\model\\";
	private String serviceImplOutPath = "C:\\Users\\1036225283\\Desktop\\franchisee\\service\\src\\main\\java\\com\\simonizchina\\franchisee\\service\\impl\\";
	private String mapperReadPath = "C:\\Users\\1036225283\\Desktop\\franchisee\\service\\src\\main\\java\\com\\simonizchina\\franchisee\\mapper\\";

	// 模板文件
	private String serviceTemplate = templatePath + "Service.java";
	private String serviceImplTemplate = templatePath + "ServiceImpl.java";
	private String voTemplate = templatePath + "VO.java";
	private String controllerTemplate = templatePath + "Controller.java";
	private String detailFrameTemplate = templatePath + "DetailFrame.java";
	private String detailFrameControllerTemplate = templatePath
			+ "DetailFrameController.java";
	private String gridFrameTemplate = templatePath + "GridFrame.java";
	private String gridFrameControllerTemplate = templatePath
			+ "GridFrameController.java";
	private String gridJPanelTemplate = templatePath + "GridJPanel.java";
	private String gridJPanelControllerTemplate = templatePath
			+ "GridJPanelController.java";
	private String detailJPanelTemplate = templatePath + "DetailJPanel.java";
	private String detailJPanelControllerTemplate = templatePath
			+ "DetailJPanelController.java";
	private String frameTemplate = templatePath + "Frame.java";
	private String noSelectControllerFrameTemplate = templatePath
			+ "NoSelectControllerFrame.java";
	private String formTemplate = templatePath + "FormJPanel.java";

	// 项目文件
	private File modelFile = new File(modelReadPath + ClassName + ".java");
	private File serviceFile = new File(serviceOutPath + ClassName
			+ "Service.java");
	private File serviceImplFile = new File(serviceImplOutPath + ClassName
			+ "ServiceImpl.java");
	private File voFile = new File(voOutPath + ClassName + "VO.java");
	private File mapperFile = new File(mapperReadPath + ClassName
			+ "Mapper.java");
	private File controllerFile = new File(controllerOutPath + ClassName
			+ "Controller.java");
	private File gridFrameFile = new File(clientOutPath + ClassName
			+ "GridFrame.java");
	private File gridFrameControllerFile = new File(clientOutPath + ClassName
			+ "GridFrameController.java");
	private File detailFrameFile = new File(clientOutPath + ClassName
			+ "DetailFrame.java");
	private File detailFrameControllerFile = new File(clientOutPath + ClassName
			+ "DetailFrameController.java");
	private File gridJPanelFile = new File(clientOutPath + ClassName
			+ "GridJPanel.java");
	private File gridJPanelControllerFile = new File(clientOutPath + ClassName
			+ "GridJPanelController.java");
	private File detailJPanelFile = new File(clientOutPath + ClassName
			+ "DetailJPanel.java");
	private File detailJPanelControllerFile = new File(clientOutPath
			+ ClassName + "DetailJPanelController.java");
	private File frameFile = new File(clientOutPath + ClassName + "Frame.java");
	private File formJPanelFile = new File(clientOutPath + ClassName
			+ "FormJPanel.java");
	// 模板流
	/**
	 * testModel.java文件内容
	 */
	private String modelContent;
	/**
	 * testService.java文件内容
	 */
	private String serviceContent;
	/**
	 * testMapper.java文件内容
	 */
	private String mapperContent;
	/**
	 * Service模板文件内容
	 */
	private String serviceTemplateContent;
	/**
	 * ServiceImpl模板文件内容
	 */
	private String serviceImplTemplateContent;
	/**
	 * Controller模板文件内容
	 */
	private String controllerTemplateContent;
	/**
	 * v o模板文件内容
	 */
	private String voTemplateContent;
	/**
	 * 主键参数类型
	 */
	private String stringParamType;
	/**
	 * 主键参数名称
	 */
	private String stringParamName;
	/**
	 * (Long id )
	 */
	private String stringParams;

	public CreateClass() {

		// ***************************************************************************************************************

		// *******************************************************************************************

	}

	/**
	 * 第一步，读取mapper文件
	 */
	public void readMapperFile() {
		if (!mapperFile.exists()) {
			throw new RuntimeException("第一步读取Mapper文件就出错了");
		}
		mapperContent = UtilFileString.fileToString(mapperFile
				.getAbsolutePath());
		String getAll = "List<" + ClassName + "> getAll();";
		String getListByMap = "List<" + ClassName
				+ "> getListByMap(Map<String, Object> map);";
		String getCount = "Integer getCountByMap(Map<String, Object> map);";
		if (mapperContent.indexOf(getAll) == -1) {
			mapperContent = mapperContent.substring(0,
					mapperContent.lastIndexOf("}"));
			mapperContent = mapperContent + "\n\t";
			mapperContent = mapperContent + getAll;
			mapperContent = mapperContent + "\n";
			mapperContent = mapperContent + "\n\t";
			mapperContent = mapperContent + getListByMap;
			mapperContent = mapperContent + "\n";
			mapperContent = mapperContent + "\n\t";
			mapperContent = mapperContent + getCount;
			mapperContent = mapperContent + "\n";
			mapperContent = mapperContent + "}";
		}
		mapperContent = mapperContent.replace("<ClassName>", ClassName);
		UtilFileString
				.stringToFile(mapperContent, mapperFile.getAbsolutePath());
		mapperContent = UtilString.substring(mapperContent, "{", "}");
		System.out.println("输出截取到的Mapper文件：");
		System.out.println(mapperContent);
	}

	/**
	 * 第二步，创建Service.java文件
	 */
	public void createServiceFile() {
		if (serviceFile.exists()) {
			return;
		} else {
			try {
				serviceFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		serviceTemplateContent = UtilFileString.fileToString(serviceTemplate);
		serviceTemplateContent = serviceTemplateContent.replaceAll(
				"<ClassName>", ClassName);
		serviceTemplateContent = serviceTemplateContent.replace("<Content>",
				mapperContent);
		UtilFileString.stringToFile(serviceTemplateContent,
				serviceFile.getAbsolutePath());
		System.out.println("输出生成的Service文件：");
		System.out.println(serviceContent);
	}

	/**
	 * 第三步，创建ServiceImpl.java文件
	 */
	public void createServiceImplFile() {
		if (serviceImplFile.exists()) {
			return;
		} else {
			try {
				serviceImplFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 读取项目的Service.java文件,而不是模板文件
		serviceContent = UtilFileString.fileToString(serviceFile
				.getAbsolutePath());
		System.out.println(serviceContent);
		serviceContent = UtilString.substring(serviceContent, "{", "}");
		System.out.println("jie qu hou");
		System.out.println(serviceContent);
		String[] strings = serviceContent.split(";");
		for (int i = 0; i < strings.length; i++) {
			if (strings[i].indexOf("deleteByPrimaryKey") != -1) {
				stringParams = UtilString.substring(strings[i], "(", ")");
				break;
			}
		}
		String[] strings2 = stringParams.split(" ");
		String className = UtilString.letterLower(ClassName);
		stringParamType = strings2[0];
		stringParamName = strings2[1];
		serviceImplTemplateContent = UtilFileString
				.fileToString(serviceImplTemplate);
		serviceImplTemplateContent = serviceImplTemplateContent.replaceAll(
				"<ClassName>", ClassName);
		serviceImplTemplateContent = serviceImplTemplateContent.replaceAll(
				"<className>", className);
		serviceImplTemplateContent = serviceImplTemplateContent.replace(
				"<paramType>", stringParamType);
		serviceImplTemplateContent = serviceImplTemplateContent.replace(
				"<param>", stringParamName);
		UtilFileString.stringToFile(serviceImplTemplateContent,
				serviceImplFile.getAbsolutePath());
		System.out.println("输出生成的ServiceImpl文件：");
		System.out.println(serviceImplTemplateContent);
	}

	/**
	 * 创建v o文件
	 */
	public void createVoFile() {
		if (!modelFile.exists()) {
			throw new RuntimeException("Model文件没有找到");
		}
		if (voFile.exists()) {
			return;
		} else {
			try {
				voFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		modelContent = UtilFileString.fileToString(modelFile.getAbsolutePath());
		modelContent = UtilString.substring(modelContent, "{", "}");
		voTemplateContent = UtilFileString.fileToString(voTemplate);
		voTemplateContent = voTemplateContent.replaceAll("<ClassName>",
				ClassName);
		voTemplateContent = voTemplateContent.replaceAll("<Content>",
				modelContent);
		UtilFileString
				.stringToFile(voTemplateContent, voFile.getAbsolutePath());
		System.out.println("输出生成的Vo文件：");
		System.out.println(voTemplateContent);
	}

	/**
	 * 创建Controller文件
	 */
	public void createControllerFile() {
		if (controllerFile.exists()) {
			return;
		} else {
			try {
				controllerFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		controllerTemplateContent = UtilFileString
				.fileToString(controllerTemplate);
		String className = UtilString.letterLower(ClassName);
		controllerTemplateContent = controllerTemplateContent.replace(
				"<ClassName>", ClassName);
		controllerTemplateContent = controllerTemplateContent.replace(
				"<className>", className);
		UtilFileString.stringToFile(controllerTemplateContent,
				controllerFile.getAbsolutePath());
		System.out.println("输出生成的Controller文件：");
		System.out.println(controllerTemplateContent);
	}

	/**
	 * 创建gridFrame文件
	 */
	public void createGridFrameFile() {
		if (gridFrameFile.exists()) {
			return;
		} else {
			try {
				gridFrameFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String gridFrameTemplateContent = UtilFileString
				.fileToString(gridFrameTemplate);
		gridFrameTemplateContent = gridFrameTemplateContent.replace(
				"<ClassName>", ClassName);
		UtilFileString.stringToFile(gridFrameTemplateContent,
				gridFrameFile.getAbsolutePath());

	}

	/**
	 * 创建gridFrameController文件
	 */
	public void createGridFrameControllerFile() {
		if (gridFrameControllerFile.exists()) {
			return;
		} else {
			try {
				gridFrameControllerFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String gridFrameControllerTemplateContent = UtilFileString
				.fileToString(gridFrameControllerTemplate);
		String classname = ClassName.toLowerCase();
		gridFrameControllerTemplateContent = gridFrameControllerTemplateContent
				.replace("<ClassName>", ClassName);
		gridFrameControllerTemplateContent = gridFrameControllerTemplateContent
				.replace("<classname>", classname);
		UtilFileString.stringToFile(gridFrameControllerTemplateContent,
				gridFrameControllerFile.getAbsolutePath());

	}

	/**
	 * 创建DetailFrame文件
	 */
	public void createDetailFrameFile() {
		if (detailFrameFile.exists()) {
			return;
		} else {
			try {
				detailFrameFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String detailFrameTemplateContent = UtilFileString
				.fileToString(detailFrameTemplate);
		detailFrameTemplateContent = detailFrameTemplateContent.replace(
				"<ClassName>", ClassName);
		UtilFileString.stringToFile(detailFrameTemplateContent,
				detailFrameFile.getAbsolutePath());
	}

	/**
	 * 创建DetailFrameController文件
	 */
	public void createDetailFrameControllerFile() {
		if (detailFrameControllerFile.exists()) {
			return;
		} else {
			try {
				detailFrameControllerFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String detailFrameControllerTemplateContent = UtilFileString
				.fileToString(detailFrameControllerTemplate);
		String classname = ClassName.toLowerCase();
		detailFrameControllerTemplateContent = detailFrameControllerTemplateContent
				.replace("<ClassName>", ClassName);
		detailFrameControllerTemplateContent = detailFrameControllerTemplateContent
				.replace("<classname>", classname);
		UtilFileString.stringToFile(detailFrameControllerTemplateContent,
				detailFrameControllerFile.getAbsolutePath());
	}

	/**
	 * 创建GridJPanel文件
	 */
	public void createGridJPanelFile() {
		if (gridJPanelFile.exists()) {
			return;
		} else {
			try {
				gridJPanelFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String gridJPanelTemplateContent = UtilFileString
				.fileToString(gridJPanelTemplate);
		String classname = ClassName.toLowerCase();
		gridJPanelTemplateContent = gridJPanelTemplateContent.replace(
				"<ClassName>", ClassName);
		gridJPanelTemplateContent = gridJPanelTemplateContent.replace(
				"<className>", classname);
		UtilFileString.stringToFile(gridJPanelTemplateContent,
				gridJPanelFile.getAbsolutePath());
	}

	/**
	 * 创建GridJPanelController文件
	 */
	public void createGridJPanelControllerFile() {
		if (gridJPanelControllerFile.exists()) {
			return;
		} else {
			try {
				gridJPanelControllerFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String girdJPanelControllerTemplateContent = UtilFileString
				.fileToString(gridJPanelControllerTemplate);
		girdJPanelControllerTemplateContent = girdJPanelControllerTemplateContent
				.replace("<ClassName>", ClassName);
		String classname = ClassName.toLowerCase();
		girdJPanelControllerTemplateContent = girdJPanelControllerTemplateContent
				.replace("<className>", classname);
		UtilFileString.stringToFile(girdJPanelControllerTemplateContent,
				gridJPanelControllerFile.getAbsolutePath());
	}

	/**
	 * 创建DetailJPanel文件
	 */
	public void createDetailJPanelFile() {
		if (detailJPanelFile.exists()) {
			return;
		} else {
			try {
				detailJPanelFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String detailJPanelTemplateContent = UtilFileString
				.fileToString(detailJPanelTemplate);
		detailJPanelTemplateContent = detailJPanelTemplateContent.replace(
				"<ClassName>", ClassName);
		UtilFileString.stringToFile(detailJPanelTemplateContent,
				detailJPanelFile.getAbsolutePath());
	}

	/**
	 * 创建DetailJPanelController文件
	 */
	public void createDetailJPanelControllerFile() {
		if (detailJPanelControllerFile.exists()) {
			return;
		} else {
			try {
				detailJPanelControllerFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String detailJPanelControllerTemplateContent = UtilFileString
				.fileToString(detailJPanelControllerTemplate);
		detailJPanelControllerTemplateContent = detailJPanelControllerTemplateContent
				.replace("<ClassName>", ClassName);
		UtilFileString.stringToFile(detailJPanelControllerTemplateContent,
				detailJPanelControllerFile.getAbsolutePath());
	}

	// 创建没有Controller的表单
	private void createFormJpanel() {
		if (formJPanelFile.exists()) {
			return;
		} else {
			try {
				formJPanelFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String formJPanelTemplateContent = UtilFileString
				.fileToString(formTemplate);
		formJPanelTemplateContent = formJPanelTemplateContent.replace(
				"<ClassName>", ClassName);
		UtilFileString.stringToFile(formJPanelTemplateContent,
				formJPanelFile.getAbsolutePath());
	}

	// 创建只包含formJPanel gridJPanel girdJPanelController
	private void createNoSelectControllerFrame() {
		if (frameFile.exists()) {
			return;
		} else {
			try {
				frameFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String noSelectControllerFrameTemplateContent = UtilFileString
				.fileToString(noSelectControllerFrameTemplate);
		noSelectControllerFrameTemplateContent = noSelectControllerFrameTemplateContent
				.replace("<ClassName>", ClassName);
		UtilFileString.stringToFile(noSelectControllerFrameTemplateContent,
				frameFile.getAbsolutePath());
	}

	// 创建包含gridJPanel gridJPanelController detailJPanel detailJPanelController
	private void createFrame() {
		if (frameFile.exists()) {
			return;
		} else {
			try {
				frameFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String frameTemplateContent = UtilFileString
				.fileToString(frameTemplate);
		frameTemplateContent = frameTemplateContent.replace("<ClassName>",
				ClassName);
		UtilFileString.stringToFile(frameTemplateContent,
				frameFile.getAbsolutePath());
	}

}
