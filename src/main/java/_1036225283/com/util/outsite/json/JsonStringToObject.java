package _1036225283.com.util.outsite.json;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 将jsonString转换为javaObject
 * 
 * @author 1036225283 主要有一下方法get("",Long.class) get("")
 * 
 */
public class JsonStringToObject {

	// 词法解析栈
	private Stack<String> stack = new Stack<String>();

	private Map<String, String> map = new HashMap<String, String>();

	private Map<String, Integer> integerMap = new HashMap<String, Integer>();

	private Integer i = 0;

	private Integer k = 0;

	private Character c;

	/**
	 * 将栈内数据解析成map 如：key = user.username value = lili
	 * 
	 * @param fatherKey
	 * @param fatherFlag
	 * @param fatherMark
	 */
	private void parse(String fatherKey, String fatherFlag, String fatherMark) {
		String key = "";
		for (; i < stack.size(); i++) {
			if (stack.get(i).equals("{")) {
				i++;
				if (fatherFlag.equals("[")) {
					key = fatherKey + "[" + integerMap.get(fatherKey) + "]";
				}
				parse(key, "{", "{");
			} else if (stack.get(i).equals("[")) {
				i++;
				if (fatherFlag.equals("[")) {
					key = fatherKey + "[" + integerMap.get(fatherKey) + "]";
				}
				integerMap.put(key, 0);
				parse(key, "[", "[");
			} else if (stack.get(i).equals(":")) {
				fatherMark = ":";
			} else if (stack.get(i).equals("}")) {
				return;
			} else if (stack.get(i).equals("]")) {
				return;
			} else if (stack.get(i).equals(",")) {
				if (fatherFlag.equals("{")) {
					fatherMark = "{";
				} else if (fatherFlag.equals("[")) {
					int value = integerMap.get(fatherKey);
					value++;
					integerMap.put(fatherKey, value);
					key = fatherKey + "[" + value + "]";
				}
			} else if (fatherMark.equals(":")) {
				String val = stack.get(i);
				map.put(key, val.substring(1, val.length() - 1));
			} else if (fatherMark.equals("{")) {
				if (fatherKey.equals("")) {
					key = stack.get(i);
					key = key.substring(1, key.length() - 1);
				} else {
					String k = stack.get(i);
					k = k.substring(1, k.length() - 1);
					key = fatherKey + "." + k;
				}
			} else if (fatherMark.equals("[")) {
				int value = integerMap.get(fatherKey);
				key = fatherKey + "[" + value + "]";
				String val = stack.get(i);
				map.put(key, val.substring(1, val.length() - 1));
			}

		}
	}

	/**
	 * 见jsonString处理成原始数据压入栈 如：{ + user + : + { + hello + : + world + } + }
	 * 
	 * @param json
	 */
	private void stackParse(String json) {
		int length = json.length();
		int address = 0;
		Boolean valueBoolean = false;
		for (; k < length; k++) {
			c = json.charAt(k);
			if (c == '{') {
				if (stack.empty()) {
					stack.push("{");
				} else if (stack.lastElement().equals("\"")) {
					continue;
				} else {
					stack.push("{");
				}
			} else if (c == '"') {
				valueBoolean = false;
				if (json.charAt(k - 1) == '\\') {
					continue;
				} else if (stack.lastElement().equals("\"")) {
					stack.pop();
					stack.push(json.substring(address, k + 1));
				} else {
					stack.push("\"");
					address = k;
				}
			} else if (c == ':') {
				if (stack.lastElement().equals("\"")) {
					continue;
				} else {
					stack.push(":");
					if (json.charAt(k + 1) != '"' && json.charAt(k + 1) != '['
							&& json.charAt(k + 1) != '{') {
						valueBoolean = true;
						address = k + 1;
					}
				}
			} else if (c == '[') {
				if (stack.lastElement().equals("\"")) {
					continue;
				} else {
					stack.push("[");
					if (json.charAt(k + 1) != '"' && json.charAt(k + 1) != '['
							&& json.charAt(k + 1) != '{') {
						valueBoolean = true;
						address = k + 1;
					}
				}
			} else if (c == ']') {
				if (stack.lastElement().equals("\"")) {
					continue;
				} else {
					if (valueBoolean == true) {
						stack.push("\"" + json.substring(address, k) + "\"");
						valueBoolean = false;
					}
					stack.push("]");
				}
			} else if (c == '}') {
				if (stack.lastElement().equals("\"")) {
					continue;
				} else {
					if (valueBoolean == true) {
						stack.push("\"" + json.substring(address, k) + "\"");
						valueBoolean = false;
					}
					stack.push("}");
				}
			} else if (c == ',') {
				if (stack.lastElement().equals("\"")) {
					continue;
				} else {
					if (valueBoolean == true) {
						stack.push("\"" + json.substring(address, k) + "\"");
						address = k + 1;
					}
					stack.push(",");
				}
			}
		}
	}

	public void goString(String json) {
		stackParse(json);
		parse("", "", "");
		// for (Entry<?, ?> entry : map.entrySet()) {
		// System.out.println(entry.getKey() + " = " + entry.getValue());
		// }
	}

	/**
	 * 简单的获取值,适用于获取String，Integer,Long等基本类型
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return map.get(key);
	}

	@SuppressWarnings("unused")
	private Object get(Object object, String key) throws Exception {
		Class<?> c = object.getClass();
		Field[] field = c.getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			field[i].setAccessible(true);
			String type = field[i].getType().getName();
			String name = key + field[i].getName();
			String value = map.get(name);
			if (type.equals("java.lang.String")) {
				if (value != null) {
					field[i].set(object, name);
				}
			} else if (type.equals("java.lang.Integer")) {
				if (value != null) {
					field[i].set(object, Integer.parseInt(value));
				}
			} else if (type.equals("java.lang.Short")) {
				if (value != null) {
					field[i].set(object, Short.parseShort(name));
				}
			} else if (type.equals("java.lang.Long")) {
				if (value != null) {
					field[i].set(object, Long.parseLong(name));
				}
			} else if (type.equals("java.lang.Double")) {
				if (value != null) {
					field[i].set(object, Double.parseDouble(name));
				}
			} else if (type.equals("java.lang.Float")) {
				if (value != null) {
					field[i].set(object, Float.parseFloat(name));
				}
			} else if (type.equals("java.lang.Character")) {
				if (value != null) {
					field[i].set(object, name.charAt(0));
				}
			} else if (type.equals("java.lang.Byte")) {
				if (value != null) {
					field[i].set(object, Byte.parseByte(name));
				}
			} else if (type.equals("java.lang.Boolean")) {
				if (value != null) {
					field[i].set(object, Boolean.parseBoolean(name));
				}
			} else if (type.equals("java.util.Date")) {
				if (value != null) {
					field[i].set(object, new Date(new Long(value)));
				}
			} else if (type.equals("java.util.List")) {
				List<Object> list = new ArrayList<Object>();
				field[i].set(object, list);
				String listType = field[i].getGenericType().toString();
				listType = listType.substring(listType.indexOf("<") + 1,
						listType.indexOf(">"));
				// Class<?> listC = Class.forName(listType +
				// "<java.lang.String>");

				System.out.println(listType);
				System.out.println(field[i].getType().getName());
			} else if (type.equals("java.util.Map")) {

			}
		}
		return object;
	}

	/**
	 * 判断是否是基本数据类型，如String,Float,Date,Integer,Long
	 */
	private boolean isBasic(Class<?> c) {
		if (c.getName().equals("java.lang.String")
				|| c.getName().equals("java.lang.Integer")
				|| c.getName().equals("java.lang.Long")
				|| c.getName().equals("java.lang.Double")
				|| c.getName().equals("java.lang.Float")
				|| c.getName().equals("java.lang.Boolean")
				|| c.getName().equals("java.util.Date")) {
			return true;
		} else {
			return false;
		}
	}
//	private String iterateBasic(String key){
//		if(map.get(key) != null){
//			if (type.equals("java.lang.String")) {
//				field[i].set(object, fieldValue);
//			} else if (type.equals("java.lang.Integer")) {
//				field[i].set(object, Integer.parseInt(fieldValue));
//			} else if (type.equals("java.lang.Short")) {
//				field[i].set(object, Short.parseShort(fieldValue));
//			} else if (type.equals("java.lang.Long")) {
//				field[i].set(object, Long.parseLong(fieldValue));
//			} else if (type.equals("java.lang.Double")) {
//				field[i].set(object, Double.parseDouble(fieldValue));
//			} else if (type.equals("java.lang.Float")) {
//				field[i].set(object, Float.parseFloat(fieldValue));
//			} else if (type.equals("java.lang.Character")) {
//				field[i].set(object, fieldValue.charAt(0));
//			} else if (type.equals("java.lang.Byte")) {
//				field[i].set(object, Byte.parseByte(fieldValue));
//			} else if (type.equals("java.lang.Boolean")) {
//				field[i].set(object, Boolean.parseBoolean(fieldValue));
//			} else if (type.equals("java.util.Date")) {
//				field[i].set(object, new Date(new Long(value)));
//			}
//		}
//		return map.get(key);
//	}
	/**
	 * 判断是否是map类型
	 * 
	 * @return
	 */
	private boolean isMap(Class<?> c) {
		if(c.getName().equals("java.util.Map")){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 判断是否是list类型
	 * 
	 * @return
	 */
	private boolean isList(Class<?> c) {
		if(c.getName().equals("java.util.List")){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 根据key获取对象 例：get("root.data.user",User.class) 总是生成一个对象,不管对象的属性是否有值
	 * 
	 * @param key
	 * @param c
	 * @return
	 * @throws Exception
	 */
	public Object get(String key, Class<?> c) throws Exception {
		String className = c.getName();
		String value = map.get(key);
		if (isBasic(c)) {
			
		} else if (isList(c)) {

		} else if (isMap(c)) {

		} else {
			Object object = c.newInstance();
			Field[] field = c.getDeclaredFields();
			for (int i = 0; i < field.length; i++) {
				field[i].setAccessible(true);
				String type = field[i].getType().getName();
				String thisKey = key + field[i].getName();
				String fieldValue = map.get(thisKey);
				if (fieldValue == null) {
					continue;
				} else if (type.equals("java.lang.String")) {
					field[i].set(object, fieldValue);
				} else if (type.equals("java.lang.Integer")) {
					field[i].set(object, Integer.parseInt(fieldValue));
				} else if (type.equals("java.lang.Long")) {
					field[i].set(object, Long.parseLong(fieldValue));
				} else if (type.equals("java.lang.Double")) {
					field[i].set(object, Double.parseDouble(fieldValue));
				} else if (type.equals("java.lang.Float")) {
					field[i].set(object, Float.parseFloat(fieldValue));
				} else if (type.equals("java.lang.Boolean")) {
					field[i].set(object, Boolean.parseBoolean(fieldValue));
				} else if (type.equals("java.util.Date")) {
					field[i].set(object, new SimpleDateFormat("yyyy-MM-dd").parse(value));
				} else {
					get(thisKey, Class.forName(type));
				}
			}
			return object;
		}
		return null;
	}

}
