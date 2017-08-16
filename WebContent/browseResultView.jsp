

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
</style>
</head>

<body>
	<form action="<%= request.getContextPath() %>/getMovie" method="get">
	<table>
		<%
			List browseCategory = new ArrayList();
			browseCategory = (ArrayList)request.getAttribute("browseResult");	
			for(int i = 0; i < browseCategory.size(); i++) { 
				List item = (List) browseCategory.get(i);%>
				<tr><td><button name="selected" value="<%= item.get(0) %>"><%= item.get(0) %></button></td></tr>
			<% } %>
	</table>
	</form>
</body>
</html>