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
	<table>
		<%
			List movies = new ArrayList();
			movies = (ArrayList)request.getAttribute("requestedMovie");
			String type = (String)request.getAttribute("type");
			for(int i = 0; i < movies.size(); i++) { 
				if(type.equals("title")) {
					List movieTitle = (List) movies.get(i); %>
					<tr><td><%=movieTitle.get(0)%></td></tr>
				<%} else { %>
					<tr><td><%=movies.get(i)%></td></tr>
				<%} %>
			<%} %>
	</table>
</body>
</html>