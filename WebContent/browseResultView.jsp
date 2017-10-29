

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<link href="https://fonts.googleapis.com/css?family=Amatic+SC|Cinzel|Lato" rel="stylesheet">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Browse Results</title>
	<style>
	
		#content {
			margin-top: 80px;
		}
		.pagesAndSort {
			display: block;
			padding-bottom: 70px;
	font-family: 'Cinzel', serif;
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
		    font-family: 'Lato', sans-serif;
		}
		.sort {
			color: red;
			padding: 14px;
			float: right;
		}
		.sort form {
			display:inline
			
		}
		.sort button {
			margin-left: 12px;
	    	text-align: center;

		    background-color: gray;
		    color: white;
		    padding: 8px;
		    font-size: 16px;
		    border: none;
		    cursor: pointer;
		    outline: none;
		    font-family: 'Lato', sans-serif;
		
		}
		.pages {
			padding: 14px;
			margin-right: 36px;
			float: right;
		}
		
		.pages a {
			color: #d01717;
			text-decoration: none;
			font-size: 28px;
		}
		.pagesBottom a {
			color: #d01717;
			text-decoration: none;
			font-size: 28px;
		}
		.pagesBottom{
			text-align: center;
			margin: 32px 0px;
			}
		
		.movieGrid {
		clear: both;
			display: grid;
			grid-template-columns: auto auto auto auto auto auto;
	  		grid-gap: 15px;
	  		background-color: #383838;

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
			<span onclick="openNav()"><button class = "myB"> Categories &#8649</button></span>
		</div>
			
		<div class="pagesAndSort">
			<div class="sort">
				<span>Sort By </span>
		
				<form action="${pageContext.request.contextPath}/browse" method="get">
				  	<c:choose>
				  	<c:when test="${orderType eq 'DESC' && orderBy eq 'title'}">
						<button type="submit" name=orderBy value="title">Title &#8648;</button>
				  		<input type="hidden" name="orderType" value="ASC">
					</c:when>	  		
			  		<c:when test="${orderType eq 'ASC' && orderBy eq 'title'}">
				 		<button type="submit" name=orderBy value="title">Title &#8650;</button>
				  		<input type="hidden" name="orderType" value="DESC">
			 		</c:when>
			 		<c:otherwise>
			 			<button type="submit" name=orderBy value="title">Title &#8650;</button>
				  		<input type="hidden" name="orderType" value="DESC">
			 		</c:otherwise>
			 		</c:choose>
				</form>
				<form action="${pageContext.request.contextPath}/browse" method="get">
				  	<c:choose>
				  	<c:when test="${orderType eq 'DESC' && orderBy eq 'year'}">
				  		<button type="submit" name=orderBy value="year">Year &#8648;</button>
				  		<input type="hidden" name="orderType" value="ASC">
			  		</c:when>
					<c:when test="${orderType eq 'ASC' && orderBy eq 'year'}">
				  		<button type="submit" name=orderBy value="year">Year &#8650;</button>
				  		<input type="hidden" name="orderType" value="DESC">
			 		</c:when>
			 		<c:otherwise>
			 			<button type="submit" name=orderBy value="year">Year &#8650;</button>
				  		<input type="hidden" name="orderType" value="DESC">
			 		</c:otherwise>
			 		</c:choose>
				</form>
				
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
		
			<div class="pagesBottom">
				<a href="browse?page=<c:out value="${page - 1}"/>&orderBy=<c:out value="${orderBy}"/>&orderType=<c:out value="${orderType}"/>"> &#10096; Previous Page ... </a>	
				<a href="browse?page=<c:out value="${page + 1}"/>&orderBy=<c:out value="${orderBy}"/>&orderType=<c:out value="${orderType}"/>">Next Page &#10097;</a>
			</div>
	</div>
</body>
</html>