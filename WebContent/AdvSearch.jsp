<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<style>
	.AdvSearch
	{
		margin: auto;
		position: absolute;
		display: inline-block;
		padding: 16px;
		border-style: solid;
		border-color: #d01717;
		box-shadow: 3px 6px 114px -17px rgba(0,0,0,1);
		background-color: #2c2d30;
		width: 500px;
		top:0;
		left:0;
		bottom:0;
		right: 0;
		height: 100px;
		margin-bottom: 400px;
	
	}
	label
	{
		color: white;
		font-family: 'Open Sans Condensed', sans-serif;
	}
	input[type=text]
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
	.SearchBtn
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
	.SearchBtn:hover
	{
		opacity: 0.8;
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
