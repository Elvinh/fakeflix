<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<style>
	body { margin: 0; padding: 0; }
	.welcome {
		padding-top: 75px;
		padding-left: 40px;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main</title>
</head>
<body bgcolor="#282828">
	<jsp:include page="_header.jsp"></jsp:include>	
	
	<div class="welcome">
		<a href="sidebar">Sidebar</a>
		<%if(session.getAttribute("loginedU") == null)
		{%>
			<h3> Hello </h3>
		<%}
		else
		{
			%><h3>Hello <%= (String) session.getAttribute("loginedU")%></h3>
	<%}%>
	</div>


<body>
</html>