package utiltest;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

public class UtilClassType {

	private String f1;
	private Integer f2;
	private Double f3;
	private Float f4;
	private BigDecimal f5;
	private Date f6;
	private Byte f15;
	private Character f16;
	private Short f17;
	private Long f18;
	private Boolean f19;
	
	private int f7;
	private double f8;
	private byte f9;
	private long f10;
	private short f11;
	private boolean f12;
	private float f13;
	private char f14;
	
	public static void main(String[] args) {
		Class<?> clazz = UtilClassType.class;
		Field [] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i].getType());
		}
	}
}
