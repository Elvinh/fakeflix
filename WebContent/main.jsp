<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<style>

	.trending { 		
		background-color: #2c2d30;
		width: 100%;
		height: 30%;
		position: absolute;
		top: 150px;
		right: 0;
		bottom: 0;
		left: 0;
		box-shadow: 3px 6px 114px -17px rgba(0,0,0,1);
		overflow: hidden;
	}
	.welcome {
		padding-top: 75px;
		padding-left: 40px;
		font-color: white;
		color: white;
	}
	
	h3
	{
		color: white;
		font-family: 'Open Sans Condensed', sans-serif;
		text-align: center;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main</title>
</head>
<body bgcolor="#282828">
	<jsp:include page="_header.jsp"></jsp:include>

	<div class="welcome">
		<c:choose>
		  <c:when test= "${empty sessionScope.loginedU}">
		  	<p>Hello. Please login.</p>
		  </c:when>
		  <c:otherwise>
		  	<p>Welcome back, <c:out value = "${loginedU}"/></p>
		  </c:otherwise>
		</c:choose>
	</div>

	<div class="trendingMovies">

	</div>

<body>
</html>