<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	.container { 		
		background-color: #2c2d30;
		margin-top: 100px;
		height: 400px;
		width: 100%;
		overflow: hidden;
		padding: 15px 50px;
		color: white;
		box-shadow: 3px 6px 114px -17px rgba(0,0,0,1);
	}
</style>
</head>
<body bgcolor="#282828">
	<jsp:include page="_header.jsp"></jsp:include>
	<div class="container">
		<h1><c:out value="${addedItem}" /> added to cart</h1>
		<a href="cart">View Cart</a>
	</div>
</body>
</html>