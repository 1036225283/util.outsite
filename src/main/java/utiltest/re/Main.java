package utiltest.re;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Main {
	
	public void test1(@Test(id = 0, age = 0) String a){
		
	}
	
	
	public void test2(@Test(id = 0, age = 0,name="yyl") String b){
		
	}
	
	
	public void test3(@Test(id = 10, age = 10,name="yyl") int c){
		
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Method[] m = Main.class.getDeclaredMethods();
		Annotation[][] an = null;
		for(Method method:m){
				 an =  method.getParameterAnnotations();
				if(an.length>0){
					for(int i=0;i<an.length;i++){
						for(int j=0;j<an[i].length;j++){
							Test t = (Test) an[i][j];
							System.out.println(t.age());
//							System.out.println(method.getName()+","+t.age()+","+t.id()+","+t.name()+","+t.test2());
						}
					}
				}
				
		}
	}
}

