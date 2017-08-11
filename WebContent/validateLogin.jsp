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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
	try
	{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_db", "root", "lilwizzard1");
		Statement select = connection.createStatement();
	    ResultSet result = select.executeQuery("Select *  from customers where customers.email = '"+ email + "' and customers.password = '" + password + "'");
	    
	    if(result.absolute(1))
	    {
	    	response.sendRedirect("main.jsp");
	    }
	    else
	    {
	    	response.sendRedirect("loginForm.jsp");
	    }
	  
	}
	catch(Exception e)
	{
		out.println("Invalid username/passwor");
	}

%>
</body>
</html>