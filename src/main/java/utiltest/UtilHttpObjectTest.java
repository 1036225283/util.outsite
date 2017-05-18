package utiltest;

import com.nitian.util.http.UtilHttpObject;

import model.User;

public class UtilHttpObjectTest {

	public static void main(String[] args) throws Exception {
		String sessionId = null;
		UtilHttpObject http = new UtilHttpObject();
		User user = new User();
		user.setUsername("xws");
		user.setPassword("110");
		User user2 = (User) http.POST("http://localhost:8080/frames/extjs?duck=duck",user);
		sessionId = http.getSessionId();
		System.out.println("第一次访问session"+sessionId);
		System.out.println(user2.getPassword());
		System.out.println(user2.getUsername());
		System.out.println("_______________________________________");
		User user3 = (User) http.POST("http://localhost:8080/frames/extjs?duck=duck&username=username&password=password",user);
		System.out.println(user3.getPassword());
		System.out.println(user3.getUsername());
		sessionId = http.getSessionId();
		System.out.println("第二次访问session"+sessionId);
		//System.out.println(Http.getHttp("http://www.baidu.com"));
	}
}
