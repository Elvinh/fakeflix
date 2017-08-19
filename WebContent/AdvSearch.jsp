<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<style>
	.AdvSearch
	{
		margin-top: 500px;
		display: inline-block;
	
	}
	label
	{
		color: white;
		font-family: 'Open Sans Condensed', sans-serif;
	}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#282828">
	<jsp:include page="_header.jsp"></jsp:include>
	<link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet">
	
	<div class="AdvSearch">
		<form action="<%= request.getContextPath() %>/browse" method="get">
			<label>Movie Title</label>
				<input type = "text" placeholder = "Enter in a movie title" name = "advTitle">
			<label>Year</label>
				<input type = "text" placeholder = "Enter in a genre" name = "advYear">
			<label>Director</label>
				<input type = "text" placeholder = "Enter in a director" name = "advDirector">
			<label>Genre</label>
				<input type = "text" placeholder = "Enter in a genre" name = "advGenre">
			<label>Star First Name</label>
				<input type = "text" placeholder = "Enter in a star first name" name = "advStarF">
			<label>Star Last Name</label>
				<input type = "text" placeholder = "Enter in a star last name" name = "advStarL">
			<button type="submit"class="SearchBtn" name = "browseBy" value = "advSearch"> Search </button>
		</form>
	</div>
</body>
</html>
