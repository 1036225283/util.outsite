package km;

import java.io.File;
import java.net.URL;

public class Teset2 {

	public static void main(String[] args) {
		String[] strings = new File("C:/Users/1036225283/Desktop/doc").list();
		for (int i = 0; i < strings.length; i++) {
			System.out.println(strings[i]);
		}

		File[] files = new File("C:/Users/1036225283/Desktop/doc").listFiles();
		for (int i = 0; i < files.length; i++) {
			System.out.println(files[i].toURI());
			
		}
	}
}
