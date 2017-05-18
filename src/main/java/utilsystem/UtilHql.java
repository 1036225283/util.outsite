package utilsystem;

import java.lang.reflect.Field;

public class UtilHql {

	/**
	 * 专为hibernate准备的，且主键必须为id
	 * 
	 * @param object
	 * @return
	 */
	public static String createUpdateHql(Object object) {
		Class<?> c;
		StringBuffer sb = new StringBuffer();
		String hql = null;
		try {
			c = Class.forName(object.getClass().getName());
			sb.append("update ");
			sb.append(object.getClass().getSimpleName());
			sb.append(" set ");
			Field[] fields = c.getDeclaredFields();
			Field field = null;
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				if (fields[i].get(object) != null) {
					if (fields[i].getName().equals("id")) {
						field = fields[i];
						continue;
					}
					sb.append(fields[i].getName());
					sb.append("=");
					sb.append(fields[i].get(object));
					sb.append(",");
				}
			}
			hql = sb.substring(0, sb.lastIndexOf(","));
			field.setAccessible(true);
			hql = hql + " where id = " + field.get(object);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return hql;
	}
}
