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
			List star = new ArrayList();
			star = (ArrayList)request.getAttribute("requestedStar");
			
			List movies = new ArrayList();
			movies = (ArrayList)request.getAttribute("moviesByStar");
		%>
			<img src="<%=star.get(4)%>" HEIGHT="120" WIDTH="120" BORDER="0"/>
			<%for(int i = 0; i < star.size()-1; i++) { %>
					<tr><td><%=star.get(i)%></td></tr>
			<%} %>
			<%for(int i = 0; i < movies.size(); i++) { %>
					<tr><td><a href="getMovie?selected=<%=movies.get(i)%>"><%=movies.get(i)%><a/></td></tr>
			<%} %>
	</table>
</body>
</html>