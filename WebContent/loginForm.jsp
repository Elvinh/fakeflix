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
form
{
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

button
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

button:hover
{
	opacity: 0.8;
}

container
{
	padding: 16px;
	font: Tahoma;
	
}


</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action  = "/loginpage.php">
	<div class = "container">
	<label>Email</label>
	<input type = "text" placeholder = "Enter Email" name = "email" required>
	<label>Password</label>>
	<input type = "password" placeholder = "Enter password" name = "password" required>
	<button type = "Login"> Login </button>
	</div>
</form>

<% 
public void login(HttpServletResponse response)
{
	try
	{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "lilwizzard1");
		Statement select = connection.createStatement();
	    ResultSet result = select.executeQuery("Select *  from customers where customers.email = '"+ email + "' and customers.password = '" + password + "'");
	    
	    if(result.absolute(1))
	    {
	    	response.sendRedirect("main.jsp");
	    }
	    else
	    {
	    	System.out.println("Invalid username/password");
	    }
	}
	catch(Exception e)
	{
		System.out.println("Invalid username/passwor");
	}
}
%>
</body>
</html>