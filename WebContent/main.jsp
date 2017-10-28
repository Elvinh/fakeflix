<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<style>
	.trending p {
		color: white;
		margin-left: 10px;
	}
	.ed { 		
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
		margin: auto;
		padding:  100px 0 0 0;
	}
	#list { 		
		background-color: #2c2d30;
		width: 100%;
		height: 310px;
    	overflow-x: auto;
    	overflow-y: hidden;
		margin-bottom: 20px;
		white-space: nowrap;
		padding: 10px;
	}
	#item {
	}
	.randomGenres a {	
	}
	.randomGenres p {
		color: white;
		margin-left: 10px;
	}
	.randomStars div { 		
		background-color: #2c2d30;
		width: 100%;
		height: 310px;
    	overflow-x: auto;
    	overflow-y: hidden;
		margin-bottom: 20px;
		white-space: nowrap;
		padding: 10px;
	}
	.randomStars a {
		margin: 0 5px;		
	}
	.randomStars p {
		color: white;
		margin-left: 10px;
	}
	
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main</title>
</head>
<body bgcolor="#282828">
	<jsp:include page="_header.jsp"></jsp:include>
		
		<%
		
			String userName = "";
	
			Cookie[] cookies = request.getCookies();
			
			if(cookies != null)
			{
				for(int i = 0; i < cookies.length; i++)
				{
					if(cookies[i].getName().equals("first_name"))
					{
						userName = cookies[i].getValue();
						
					}
					if(cookies[i].getName().equals("last_name"))
					{
						userName = userName + " " + cookies[i].getValue();
						
					}
					
					
				}
				
			%><h3><%= userName %></h3>

		  <%}%>
			
			<%
			if(userName == null)
			{
				response.sendRedirect("main.jsp");
			}
		
			%>
		
		
	</div>
	


	<div class="trending">
		<br>
		<p>Most Liked</p>
		<c:forEach items="${mostLiked}" var="movie">
		<a href="getMovie?selected=<c:out value="${movie.title}"/>&id=<c:out value="${movie.id}"/>" >
			<img src="<c:out value="${movie.banner_url}"/>"  WIDTH="200" HEIGHT="300" BORDER="0"/>
		</a>
		</c:forEach>
	</div>
	
	<div class="randomGenres">
		<c:set var="i" value="0"/>
		<c:forEach items="${moviesFromRandomGenres}" var="moviesFromAGenre">
			<br>
			<p>${genreNames[i]}</p>
			<c:set var="i" value="${i + 1}" scope="page"/>
			<div id="list">
					<c:forEach items="${moviesFromAGenre}" var="movie">
						<a href="getMovie?selected=<c:out value="${movie.title}"/>&id=<c:out value="${movie.id}"/>" >
							<img src="<c:out value="${movie.banner_url}"/>"  WIDTH="200" HEIGHT="300" BORDER="0"/>
						</a>
					</c:forEach>
			</div>
		</c:forEach>
	</div>

	<div class="randomStars">
		<c:set var="i" value="0"/>
		<c:forEach items="${moviesFromRandomStars}" var="moviesFromAStar">
			<br>
			<p>${starNames[i].fullName}</p>
			<c:set var="i" value="${i + 1}" scope="page"/>
			<div id="list">
					<c:forEach items="${moviesFromAStar}" var="movie">
						<div id="item">
							<a href="getMovie?selected=<c:out value="${movie.title}"/>&id=<c:out value="${movie.id}"/>"/>" >
								<img src="<c:out value="${movie.banner_url}"/>"  WIDTH="200" HEIGHT="300" BORDER="0"/>
							</a>
						</div>
					</c:forEach>
			</div>
		</c:forEach>
	</div>
	
<body>
</html>