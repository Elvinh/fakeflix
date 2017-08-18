

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	body { margin: 0; padding: 0; }
	p {
		display: inline-block;
		margin-top: 100px;
		padding-left: 40px;
	}
	form {
		display: inline-block;
		
	}
	
	.movieGrid {
		display: grid;
		grid-template-columns: auto auto auto auto auto;
  		grid-gap: 10px;
  		
	}
	.box {
	  border-radius: 5px;
	  padding: 20px;
	}
	
</style>
</head>

<body bgcolor="#282828">
	<jsp:include page="_header.jsp"></jsp:include>
	
	<p>Sort By: </p>
	
	<form action="<%= request.getContextPath() %>/browse" method="get">
		<select name="orderBy">
	  		<option>Select</option>
	  		<option value="title">Title</option>
	  		<option value="opel">Opel</option>
	  		<option value="audi">Audi</option>
		</select>
		<input type="submit" value="Send"></td>
	</form>

	<a href="browse?browseBy=title&orderBy=title&page=1">1</a>	
	<a href="browse?browseBy=title&orderBy=title&page=2">2</a>
	<a href="browse?browseBy=title&orderBy=title&page=3">3</a>
	<a href="browse?browseBy=title&orderBy=title&page=4">4</a>

	<div class="movieGrid">
		<%
		List browseCategory = new ArrayList();
		browseCategory = (ArrayList)request.getAttribute("browseResult");	
		for(int i = 0; i < browseCategory.size(); i++) { 
			List item = (List) browseCategory.get(i);%>
	
					<div class="box <%=i%>">
						<a href="getMovie?selected=<%= item.get(0) %>"><img src="<%=item.get(1)%>" HEIGHT="300" WIDTH="200"/></a>
					</div>
	
		<% } %>
	</div>
</body>
</html>