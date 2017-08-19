<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	body { margin: 0; padding: 0; }
	.movieInfo { 		padding-top: 75px;
		padding-left: 40px; }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#282828" margin: 0}>
	<jsp:include page="_header.jsp"></jsp:include>
	<c:import url="sidebar" />
	<div class="movieInfo">
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
					<tr><td><a href = "getMovie?selected=<%=movieTitle.get(0)%>">
						<img src="<%=movieTitle.get(3)%>" HEIGHT="300" WIDTH="200"/>
					</a></td></tr>
					
					<tr><td><%= movieTitle.get(1)%></td></tr>
					<tr><td><%= movieTitle.get(2)%></td></tr>
					<tr><td><%= movieTitle.get(4)%></td></tr>
					<%  for(int j = 0; j < stars.size(); j++) { 
						ArrayList star = (ArrayList)stars.get(j); %>
						<tr><td><a href="getStar?selected=<%=star.get(2)%>"><%=star.get(0) + " " + star.get(1)%><a/></td></tr>
					<%  }%>
				<%} else { %>
					<tr><td>
					<a href="getMovie?selected=<%=movies.get(i)%>"><%=movies.get(i)%><a/>
					</td></tr>
				<%} %>
			<%} %>
		</table>
	</div>

</body>
</html>