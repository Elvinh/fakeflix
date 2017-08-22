<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<style>
	
	table
	{
		position: absolute;
		margin: auto;
		width: 600px;
		top:0;
		left:0;
		bottom:0;
		right: 0;
		height: 100px;
		padding-bottom: 200px;
	}
	
	th, td
	{
		text-align: left;
		padding: 8px;
		font-family: 'Open Sans Condensed', sans-serif;
	}
	
	tr:nth-child(even)
	{
		background-color: blue;
	}
	
	th
	{
		background-color: green;
	}

</style>

<body bgcolor="#282828">
<jsp:include page="_header.jsp"></jsp:include>
	<link href="https://fonts.googleapis.com/css?family=Amatic+SC|Cinzel" rel="stylesheet">

	<h2>My shopping cart</h2>

	<table>
		<tr>
			<th>Movie Title</th>
			<th>Price</th>
			<th>Quantity</th>
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
				</c:forEach>
			</td>
		</tr>
	
	</table>
</body>
</html>