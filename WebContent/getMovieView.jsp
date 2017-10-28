<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
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
	.movieContent {
		float: left;
		width: 65%;
	}
	.movieContent h1{
		font-family:'Garamond', sans-serif;
	}
	.movieDesc {
		font-size: 16px;

	}
	.movieDesc a{
		display: inline;		
		color: #4cbbf7;
		text-decoration:none;  	
	}
	.movieDesc .watch{
	    text-align: center;
	    background-color: #666666;
	    color: white;
	    padding: 10px 10px 10px 35px;
	    font-size: 16px;
	    border: none;
	    cursor: pointer;
	    outline: none;
	    background-image: url("./images/play-button.png");
   		background-position: 8px 6px; 
   		background-repeat: no-repeat;
		box-shadow: 10px 10px 34px -8px rgba(0,0,0,0.75);
   		
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
	.Buy
	{
		height: 100%;
		width: 30%;
		float: right;
		font-family: 'Cinzel', serif;
	}
	.buyInfo{
		width: 50%;
		margin: 0 auto;
	}
	.Buy p{
		font-size: 36px;
		margin: 100px 0 0 0;
		color: #d01717;
	}
	.Buy .likeMovie {
		margin-bottom: 10px;
	}
	.Buy button {
    	text-align: center;
	    background-color: #d01717;
	    color: white;
	    padding: 16px;
	    font-size: 16px;
	    border: none;
	    cursor: pointer;
	    outline: none;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet">

<title>Insert title here</title>
</head>
<body bgcolor="#282828">
	<jsp:include page="_header.jsp"></jsp:include>

	<div class="movieInfo">
		<div class="movieContent">
					<h1><c:out value="${requestedMovie.title}" /></h1>
					<img src="<c:out value="${requestedMovie.banner_url}" />"/>
					<div class="movieDesc">
						<p>Released on: <c:out value="${requestedMovie.year}"/></p>
						<a class="watch" href="${requestedMovie.trailer_url}">Watch Trailer</a>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas nec eleifend augue. Quisque luctus ultricies urna sit amet ultrices. Nullam iaculis tincidunt dui, quis ornare mi semper vel. Pellentesque malesuada felis vel consectetur pharetra. Etiam eleifend risus nec sem iaculis, non varius sapien aliquet. Nulla sit amet elit id dui sollicitudin finibus. Pellentesque id diam libero. Nulla mi leo, euismod non mi at, mollis ultricies ligula. In quis magna vel nisl cursus lobortis. Curabitur scelerisque ultrices magna eu varius. Duis vitae dolor purus. Cras nec metus accumsan, venenatis lorem ac, semper eros.
						Curabitur quis dui est. Nulla facilisi. Maecenas venenatis libero lacus. Nullam sapien elit, scelerisque ac quam gravida, facilisis mattis ex.</p>
						<p style="display:inline">Directed by: </p>
						<a href="browse?browseBy=advSearch&advDirector=<c:out value="${requestedMovie.director}"/>" ><c:out value="${requestedMovie.director}"/></a>
					</div>
			
			<div class="starList">
				<p>Staring: </p>
				<c:forEach var="star" items="${stars}">
					<a href="getStar?selected=<c:out value="${star[1]}"/>"><c:out value="${star[0]}"/></a>
				</c:forEach>
			</div>
		</div>
		<div class="Buy">
			<div class="buyInfo">
				<p>$19.99</p>
				<form method="get" action="${pageContext.request.contextPath}/like" >
					<button  type="submit" class= "likeMovie" name = "likeMovie" value = "${requestedMovie.id}"><c:out value="${isLiked eq 'false' ? 'Like': 'Unlike'}"/></button>
				</form>
				
				<form method="get" action="${pageContext.request.contextPath}/addToCart" >
					<button type="submit" class= "buyMovie" name = "addMovie" value = "${requestedMovie.title}">Add To Cart</button>
				</form>
			</div>
		</div>


	</div>
	
</body>
</html>