package utilsystem;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UtilBean {

	/**
	 * 将model转换为vo，vo转换为model
	 * 
	 * @param object
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T map(Object oneObject, Class<?> twoClazz) {
		T value = null;
		try {
			Object twoObject = twoClazz.newInstance();
			value = (T) twoObject;
			Field[] twoFields = twoObject.getClass().getDeclaredFields();
			Class<?> oneClazz = oneObject.getClass();
			for (int i = 0; i < twoFields.length; i++) {
				Field twoField = twoFields[i];
				twoField.setAccessible(true);
				String name = twoField.getName();
				if (existTheField(name, oneClazz)) {
					Field oneField = oneClazz.getDeclaredField(name);
					oneField.setAccessible(true);
					if (twoField.getType().equals(oneField.getType())) {
						twoField.set(twoObject, oneField.get(oneObject));
					} else {
						System.out.println(oneField.get(oneObject));
						System.out.println(twoField.get(twoObject));
						if (twoField.getType().toString()
								.equals("class java.lang.String")) {
							if (oneField.getType().toString()
									.equals("class java.util.Date")) {
								twoField.set(
										twoObject,
										DateFormat.getDateInstance().format(
												oneField.get(oneObject)));
							} else {
								twoField.set(twoObject, oneField.get(oneObject)
										.toString());
							}
						} else if (oneField.getType().toString()
								.equals("class java.lang.String")) {
							if (twoField.getType().toString()
									.equals("class java.lang.Byte")) {
								twoField.set(twoObject, Byte.valueOf(oneField
										.get(oneObject).toString()));
							} else if (twoField.getType().toString()
									.equals("class java.lang.Short")) {
								twoField.set(twoObject, Short.valueOf(oneField
										.get(oneObject).toString()));
							} else if (twoField.getType().toString()
									.equals("class java.lang.Integer")) {
								twoField.set(twoObject, Integer
										.valueOf(oneField.get(oneObject)
												.toString()));
							} else if (twoField.getType().toString()
									.equals("class java.lang.Long")) {
								twoField.set(twoObject, Long.valueOf(oneField
										.get(oneObject).toString()));
							} else if (twoField.getType().toString()
									.equals("class java.lang.Boolean")) {
								twoField.set(twoObject, Boolean
										.valueOf(oneField.get(oneObject)
												.toString()));
							} else if (twoField.getType().toString()
									.equals("class java.lang.Float")) {
								twoField.set(twoObject, Float.valueOf(oneField
										.get(oneObject).toString()));
							} else if (twoField.getType().toString()
									.equals("class java.lang.Double")) {
								twoField.set(twoObject, Double.valueOf(oneField
										.get(oneObject).toString()));
							} else if (twoField.getType().toString()
									.equals("class java.util.Date")) {
								SimpleDateFormat sdf = new SimpleDateFormat(
										"yyyy-MM-dd");
								try {
									twoField.set(twoObject, sdf.parse(oneField
											.get(oneObject).toString()));
								} catch (ParseException e) {
									e.printStackTrace();
								}
							} else if (twoField.getType().toString()
									.equals("class java.math.BigDecimal")) {
								twoField.set(twoObject, new BigDecimal(oneField
										.get(oneObject).toString()));
							}
						}
					}

				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> mapList(List<?> list, Class<?> twoClazz) {
		List<Object> twoList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Object oneObject = list.get(i);
			Object twoObject = map(oneObject, twoClazz);
			twoList.add(twoObject);
		}
		return (List<T>) twoList;
	}

	public static Boolean existTheField(String name, Class<?> clazz) {
		try {
			clazz.getDeclaredField(name);
		} catch (SecurityException e) {
			e.printStackTrace();
			return false;
		} catch (NoSuchFieldException e) {
			return false;
		}
		return true;
	}

	/**
	 * 给一个类赋值
	 * 
	 * @throws SQLException
	 */
	public static Object getInstanceByClass(ResultSet resultSet)
			throws SQLException {
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		int count = resultSetMetaData.getColumnCount();
		
		return null;
	}
	/**
	 * 给一个类的实例赋值
	 */
	/**
	 * 给一个map赋值
	 */
	/**
	 * 给一个类的集合赋值
	 */
	/**
	 * 给一个类的实例集合赋值
	 */
	/**
	 * 给一个list<Map>赋值
	 */
}
