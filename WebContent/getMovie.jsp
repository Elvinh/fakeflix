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
			List stars = new ArrayList();
			stars = (ArrayList)request.getAttribute("stars");
			
			String type = (String)request.getAttribute("type");
			for(int i = 0; i < movies.size(); i++) { 
				if(type.equals("title")) {
					List movieTitle = (List) movies.get(i); %>
					<tr><td><%=movieTitle.get(0)%></td></tr>
					<img src="<%=movieTitle.get(3)%>" HEIGHT="120" WIDTH="120" BORDER="0"/>
					<%  for(int j = 0; j < stars.size(); j++) { %>
						<tr><td><a href="getStar?selected=<%=stars.get(j)%>"><%=stars.get(j)%><a/></td></tr>
					<%  }%>
				<%} else { %>
					<tr><td>
					<a href="getMovie?selected=<%=movies.get(i)%>"><%=movies.get(i)%><a/>
					</td></tr>
				<%} %>
			<%} %>
	</table>
</body>
</html>