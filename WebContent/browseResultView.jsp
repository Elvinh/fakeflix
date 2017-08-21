

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<link href="https://fonts.googleapis.com/css?family=Amatic+SC|Cinzel" rel="stylesheet">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<style>
	
		.sort {
			display: inline-block;
			margin-top: 100px;
			padding-left: 40px;
		}
		form {
			display: inline-block;
		}
		
		.movieGrid {
			display: grid;
			grid-template-columns: auto auto auto auto auto auto;
	  		grid-gap: 15px;
	  		
		}
		.movieBanner {
		  padding: 20px;
		  position: relative;
		  width: 225px;
		  height: 350px;
		}
		.movieBanner a img{
			width:100%;
			height:100%;
			box-shadow: 11px 13px 66px 1px rgba(8,8,8,1);
		}
		.movieBanner .movieTitle{
			position: absolute;
			width: 225px;
			background: rgba(208, 23, 23, 1);
			top: 270px;
	  		left: 20px;
	  		vertical-align:middle;
	  		font-size: 20px;
	  		text-align: center;
	  		color: white;
			box-shadow: -1px 5px 91px 9px rgba(8,8,8,1);
	
	
		}
		
		.openButton
		{
			font-family: 'Cinzel', serif;
			display:inline-block;
		}
		.myB {
			margin-left: 20px;
	    	text-align: center;
	    	position: relative;
	    	float: center;
		    background-color: #d01717;
		    color: white;
		    padding: 16px;
		    font-size: 16px;
		    border: none;
		    cursor: pointer;
		    flex-shrink: 0;
		    outline: none;
		}
		
	</style>
</head>

<body bgcolor="#282828">
	<jsp:include page="_header.jsp"></jsp:include>
	<c:import url="sidebar" />
	
	<div id="content">
		<div class = "openButton">
			<span onclick="openNav()"><button class = "myB">>> Categories</button></span>
		</div>
			
		<p class="sort">Sort By: </p>
		
		<form action="/browse" method="get">
			<select name="orderBy">
		  		<option>Select</option>
		  		<option value="title">Title</option>
		  		<option value="opel">Opel</option>
		  		<option value="audi">Audi</option>
			</select>
			<input type="submit" value="Send">
		</form>
	
		<a href="browse?browseBy=title&orderBy=title&page=1">1</a>	
		<a href="browse?browseBy=title&orderBy=title&page=2">2</a>
		<a href="browse?browseBy=title&orderBy=title&page=3">3</a>
		<a href="browse?browseBy=title&orderBy=title&page=4">4</a>
	
		<div class="movieGrid">
			<c:forEach var="movie" items="${browseResult}">
				<div class="movieBanner">
					<a href="getMovie?selected=<c:out value="${movie[0]}"/>"><img src="<c:out value="${movie[1]}"/>" /></a>
					<div class="movieTitle">
						<c:out value="${movie[0]}"/>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>