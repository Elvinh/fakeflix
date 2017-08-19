<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#282828">
	<jsp:include page="_header.jsp"></jsp:include>
	<link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet">
	<div class="AdvSearch">
		<form method = "post" action = "<%= request.getContextPath()%>/browse">
			<label>Movie Title</label>
				<input type = "mTitle" placeholder = "Enter in a movie title" name = "browseBy" value = "advTitle">
			<label>Year</label>
				<input type = "year" placeholder = "Enter in a genre" name = "browseBy" value = "advYear">
			<label>Director</label>
				<input type = "director" placeholder = "Enter in a director" name = "browseBy" value = "advDirector">
			<label>Genre</label>
				<input type = "genre" placeholder = "Enter in a genre" name = "browseBy" value = "advGenre">
			<label>Star</label>
				<input type = "star" placeholder = "Enter in a star name" name = "browseBy" value = "advStar">
			<button class="SearchBtn" type = "Search"> Search </button>
		</form>
	</div>
</body>
</html>