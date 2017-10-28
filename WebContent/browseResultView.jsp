

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<link href="https://fonts.googleapis.com/css?family=Amatic+SC|Cinzel" rel="stylesheet">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Browse Results</title>
	<style>
	
		#content {
			margin-top: 80px;
		}
		.pagesAndSort {
			display: block;
		}
		
		.openButton
		{
			font-family: 'Cinzel', serif;
			float: left;
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
		.sort {
			color: red;
			padding: 14px;
			float: left;
		}
		.sort form {
			display:inline
			
		}
		.pages {
			padding: 14px;
			margin-right: 36px;
			float: right;
		}
		
		
		.movieGrid {
		clear: both;
			display: grid;
			grid-template-columns: auto auto auto auto auto auto;
	  		grid-gap: 15px;

		}
		.movieBanner {
		  padding: 20px;
		  position: relative;
		  width: 180px;
		  height: 265px;
		}
		.movieBanner a img{
			width:100%;
			height:100%;
			box-shadow: 11px 13px 66px 1px rgba(8,8,8,1);
		}
		.movieBanner .movieTitle{
			position: absolute;
			width: 180px;
			background: rgba(208, 23, 23, 1);
			top: 230px;
	  		left: 20px;
	  		vertical-align:middle;
	  		font-size: 20px;
	  		text-align: center;
	  		color: white;
			box-shadow: -1px 5px 91px 9px rgba(8,8,8,1);	
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
			
		<div class="pagesAndSort">
			<div class="sort">
				<span>Sort By: </span>
		
				<form action="${pageContext.request.contextPath}/browse" method="get">
					<select name="orderBy">
				  		<option>Select</option>
				  		<option value="title">Title</option>
				  		<option value="year">Year</option>
					</select>
					<input type="submit" value="Send">
				</form>
			</div>

			<div class="pages">
				<a href="browse?page=1">1</a>	
				<a href="browse?page=2">2</a>
				<a href="browse?page=3">3</a>
				<a href="browse?page=4">4</a>
			</div>
		</div>
		
		<div class="movieGrid">
			<c:forEach var="movie" items="${browseResult}">
				<div class="movieBanner">
					<c:choose>
						 <c:when test="${type eq 'genre'}">
							<a href="browse?browseBy=genreName&genreName=<c:out value="${movie.title}"/>"><img src="<c:out value="${movie.banner_url}"/>" /></a>
						</c:when>
						<c:otherwise>
							<a href="getMovie?selected=<c:out value="${movie.title}"/>&id=<c:out value="${movie.id}"/>"><img src="<c:out value="${movie.banner_url}"/>" /></a>
						</c:otherwise>
					</c:choose>
					<div class="movieTitle">
						<c:out value="${movie.title}"/>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>