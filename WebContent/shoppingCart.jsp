<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#282828">
<jsp:include page="_header.jsp"></jsp:include>
	<link href="https://fonts.googleapis.com/css?family=Amatic+SC|Cinzel" rel="stylesheet">

	<h2>My shopping cart</h2>

	<table>
		<tr>
			<td>Movie Title</td>
			<td>Price</td>
			<td>Quantity</td>
		</tr>
		<tr>
			<td>
				<c:forEach var = "movieList" items = "${addedMovies}">
					<a href = "shoppingCart?"selected=<c:out value = "${movieList[0]}"/>">
						<c:out value = "${movieList[0]}"/>
					</a>
				</c:forEach>
			</td>
			<td>
				<c:forEach var = "movieList" items = "${addedMovies}">
					<p><c:out value = "${movieList[1]}"/><p>
				</c:forEach>
			</td>
			<td>
				<c:forEach var = "movieList" items = "${addedMovies}">
					<input type = "text" placeholder = "1" name = quantity" required>
					<p>1</p>
				</c:forEach>
			</td>
		</tr>
		
	
		
		<c:forEach var="movie" items="${browseResult}">
				<div class="movieBanner">
					<a href="getMovie?selected=<c:out value="${movie[0]}"/>"><img src="<c:out value="${movie[1]}"/>" /></a>
					<div class="movieTitle">
						<c:out value="${movie[0]}"/>
					</div>
				</div>
		</c:forEach>
	
	
	</table>
</body>
</html>