<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<p>Hello</p>
	<%  String name = "";
		name = (String) session.getAttribute("loginedU");
		//String fName = (String)name.get(0);
		//String lName = (String)name.get(1);%>
		
		<p>Welcome <%= name %></p>
		
		
</body>
</html>