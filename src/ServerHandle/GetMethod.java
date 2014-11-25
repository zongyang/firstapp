package ServerHandle;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.LoginAction;

//入口函数处理类:通过url的参数找到对用的处理方法
public class GetMethod {
	
	public static String getRequestMehthod (HttpServletRequest request, HttpServletResponse response) throws Exception{
		Enumeration<String> enu = request.getParameterNames();
		Hashtable<String, String> paraHash = new Hashtable<String, String>();
		
		
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			String paraValue = request.getParameter(paraName);
			paraHash.put(paraName, paraValue);
		}
		
		if (!paraHash.containsKey("method")) {
			return "";
		}

		if (paraHash.get("method").endsWith("login")) {
			return LoginAction.login(paraHash.get("email"), paraHash.get("psw"));
		}

		return "";
	   
	}
	
	

}
