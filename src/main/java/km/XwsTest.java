package km;

import java.util.LinkedList;
import java.util.List;

public class XwsTest {

	public static void main(String[] args) {
		List<String> linkedList = new LinkedList<String>();
		for (int i = 0; i < 1000000; i++) {
			linkedList.add("index : " + i);
		}
		System.out.println("end");
	}
}
