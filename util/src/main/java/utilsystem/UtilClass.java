package utilsystem;

public class UtilClass {

	public String createGetters(String name,String type){
		String value = "";
		String head = name.substring(0, 1);
		String copy = name;
		copy = head.toUpperCase() + copy.substring(1);
		value = value + "\tpublic " + type + " get" + copy;
		value = value + "(){\r\n\t\treturn " + name + ";\r\n\t}\r\n";
		return value;
	}
	
	public String createSetters(String name,String type){
		String value = "";
		String head = name.substring(0, 1);
		String copy = name;
		copy = head.toUpperCase() + copy.substring(1);
		value = value + "\tpublic void set" + copy + "(";
		value = value + type + " " + name + "){\r\n\t\tthis.";
		value = value + name + " = " + name + ";\r\n\t}\r\n";
		return value;
	}
	
	public static boolean existMethod(Class<?> c,String methodName){
		try {
			c.getDeclaredMethod(methodName);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			return false;
		}
		return true;
	}
	
	public static boolean existField(Class<?> c,String fieldName){
		try {
			c.getDeclaredField(fieldName);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			return false;
		}
		return true;
	}
}
