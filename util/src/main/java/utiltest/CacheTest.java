package utiltest;

import java.rmi.Naming;
import java.util.List;

import cache.service.keyobject.KeyObjectService;
import cache.service.keystring.KeyStringService;


public class CacheTest {

	public static void main(String[] args) throws Exception {
		String url = "//localhost:8029/SAMPLE-SERVER";
//		KeyStringService service = (KeyStringService) Naming.lookup(url);
		KeyObjectService service = (KeyObjectService) Naming.lookup(url);

		List<String> list = (List<String>) service.get("list");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		System.out.println(service.get("hello"));
	}
}
