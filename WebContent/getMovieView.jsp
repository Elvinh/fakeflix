<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	.movieInfo { 		
		background-color: #2c2d30;
		margin-top: 100px;
		height: 400px;
		width: 100%;
		overflow: hidden;
		padding: 15px 50px;
		color: white;
		box-shadow: 3px 6px 114px -17px rgba(0,0,0,1);

		
	}
	.movieInfo img {
		float: left;
		margin: 0 25px 0 0;
		width: 200px;
		height: 300px;
				box-shadow: 11px 13px 66px 1px rgba(8,8,8,1);
		
	}
	.movieDesc {
		font-size: 16px;
		width: 65%;
	}
	.starList a{
		display: inline;		
		color: #4cbbf7;
		 text-decoration:none;  
		
		
	}
	.starList p{
		display: inline;
		color: #d8d8d8;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#282828" margin: 0}>
	<jsp:include page="_header.jsp"></jsp:include>

	<div class="movieInfo">
		<%
		List movies = new ArrayList();
		movies = (ArrayList)request.getAttribute("requestedMovie");
		List stars = new ArrayList();
		stars = (ArrayList)request.getAttribute("stars");
		String type = (String)request.getAttribute("type");
		%>
		<%
		for(int i = 0; i < movies.size(); i++) { 
			if(type.equals("title")) {
				List movieTitle = (List) movies.get(i); 
		%>
				<h1><%=movieTitle.get(0)%></h1>
				<img src="<%=movieTitle.get(3)%>"/>
				<div class="movieDesc">
					<p>Released on: <%= movieTitle.get(1)%></p>
					<form action="<%=movieTitle.get(4)%>">
    					<input type="submit" value="trailer" />
					</form>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas nec eleifend augue. Quisque luctus ultricies urna sit amet ultrices. Nullam iaculis tincidunt dui, quis ornare mi semper vel. Pellentesque malesuada felis vel consectetur pharetra. Etiam eleifend risus nec sem iaculis, non varius sapien aliquet. Nulla sit amet elit id dui sollicitudin finibus. Pellentesque id diam libero. Nulla mi leo, euismod non mi at, mollis ultricies ligula. In quis magna vel nisl cursus lobortis. Curabitur scelerisque ultrices magna eu varius. Duis vitae dolor purus. Cras nec metus accumsan, venenatis lorem ac, semper eros.
					Curabitur quis dui est. Nulla facilisi. Maecenas venenatis libero lacus. Nullam sapien elit, scelerisque ac quam gravida, facilisis mattis ex.</p>
					<a href="browse?browseBy=advSearch&advDirector=<%=movieTitle.get(2)%>" >Directed by: <%= movieTitle.get(2)%></a>
	
				</div>

			<%} else { %>
				<a href="getMovie?selected=<%=movies.get(i)%>"><%=movies.get(i)%></a>
			<%}%>
		<%}%>
		<div class="starList">
			<p>Staring: </p>
			<% for(int j = 0; j < stars.size(); j++) { 
				ArrayList star = (ArrayList)stars.get(j); %>
				<a href="getStar?selected=<%=star.get(2)%>"><%=star.get(0) + " " + star.get(1)%></a>
			<%}%>
		</div>
		<div class="Buy">
		</div>
	</div>
	





</body>
</html>