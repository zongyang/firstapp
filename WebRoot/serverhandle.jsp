<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>

<jsp:useBean id="serverHandle" class="DLL.ServerHandle" scope="page"></jsp:useBean>
<%

	//获得请求参数名和值的HashTable传给DLL层处理
	
	Enumeration<String> enu = request.getParameterNames();
	Hashtable<String, String> paraHash = new Hashtable<String, String>();
	
	while (enu.hasMoreElements()) {
		String paraName = (String) enu.nextElement();
		String paraValue = request.getParameter(paraName);
		paraHash.put(paraName, paraValue);
	}
   
	response.getWriter().write(serverHandle.getTargetMehthod(paraHash));
%>