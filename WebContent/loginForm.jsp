<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*,
 javax.sql.*,
 java.io.IOException,
 javax.servlet.http.*,
 javax.servlet.*,
 javax.servlet.annotation.WebServlet,
javax.servlet.http.HttpServlet,
javax.servlet.http.HttpServletRequest, 
 javax.servlet.http.HttpServletResponse"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	body { margin: 0; padding: 0; }

.loginBox 
{
	position: absolute;
	margin: auto;
	width: 500px;
	top:0;
	left:0;
	bottom:0;
	right: 0;
	height: 100px;
	padding-bottom: 200px;
	
	
}
input[type=text], input[type = password]
{
	width: 100%;
	padding: 12px, 20px;
	margin: 8px;
	display: inline-block;
	box-sizing: border-box;
	font: Tahoma;
	color: white;
	margin: 8px 0;
}

.loginBtn
{
	background-color: #d01717;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width:100%;
	font: Tahoma;
	
}

.loginBtn:hover
{
	opacity: 0.8;
}

.container
{
	padding: 16px;
	font: Tahoma;
	border-style: solid;
	border-color: #d01717;
	box-shadow: 3px 6px 114px -17px rgba(0,0,0,1);
	background-color: #2c2d30;
	
}

label
{
	color: white;
	font-family: 'Open Sans Condensed', sans-serif;
	margin-right: 10px;
}


</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body bgcolor="#282828">
	<jsp:include page="_header.jsp"></jsp:include>
	<link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet">
	<div class="loginBox">
		<form method = "post" action = "<%= request.getContextPath() %>/user">
		<div class = "container">
			<label>Email</label>
				<input type = "text" placeholder = "Enter Email" name = "email" required>
			<label>Password</label>
				<input type = "password" placeholder = "Enter password" name = "password" required>
			<button class="loginBtn" type = "Login"> Login </button>
		</div>
		</form>
	</div>

</body>
</html>