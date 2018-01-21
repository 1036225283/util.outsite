package utiltest;

import _1036225283.com.util.outsite.http.UtilHttpString;

public class UtilHttpStringTest {

	public static void main(String[] args) throws Exception {
		String sessionId = null;
		UtilHttpString http = new UtilHttpString();
		String json = http.POST("http://localhost:8080/frames/extjs?duck=duck",null);
		sessionId = http.getSessionId();
		System.out.println(sessionId);
		System.out.println(json);
		System.out.println("_______________________________________");
		json = http.POST("http://localhost:8080/frames/extjs?duck=duck&username=username&password=password",null);
		System.out.println(json);
		//System.out.println(Http.getHttp("http://www.baidu.com"));
	}
}
