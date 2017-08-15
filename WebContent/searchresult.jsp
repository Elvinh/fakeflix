

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	table {

		border-color: black;
	}
	td {
		border: 2px solid #dddddd;
	}
</style>
</head>

<body>
	<table>
		<%
			List movieList = new ArrayList();
			movieList = (ArrayList)request.getAttribute("browseResult");
			for(int i = 0; i < movieList.size(); i++) { %>
				<tr><td> <%= movieList.get(i) %></td></tr>
			<% } %>
	</table>
</body>
</html>