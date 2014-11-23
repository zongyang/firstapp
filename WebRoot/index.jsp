<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<jsp:useBean id="user" class="com.po.Users" scope="page"></jsp:useBean>
	<%
		user.setUsername("xx");
		out.println(user.getUsername());
	%>
</body>
</html>
