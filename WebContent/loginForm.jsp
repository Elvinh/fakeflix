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
.login {
	border: 3px solid #f1f1f1;

}


input[type=text], input[type = password]
{
	width: 100%;
	padding: 12px, 20px;
	margin: 8px;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
	font: Tahoma;
}

.loginBtn
{
	background-color: #4caf50;
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
	
}


</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#282828">
	<jsp:include page="_header.jsp"></jsp:include>
	
	<form class="login" method = "post" action = "<%= request.getContextPath() %>/user">
		<div class = "container">
			<label>Email</label>
			<input type = "text" placeholder = "Enter Email" name = "email" required>
			<label>Password</label>
			<input type = "password" placeholder = "Enter password" name = "password" required>
			<button class="loginBtn" type = "Login"> Login </button>
		</div>
	</form>
</body>
</html>