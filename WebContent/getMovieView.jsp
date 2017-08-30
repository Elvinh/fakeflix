<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>

	.Buy
	{
		font-family: 'Cinzel', serif;
	  	flex-shrink: 0;
	}
	
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
<link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet">

<title>Insert title here</title>
</head>
<body bgcolor="#282828">
	<jsp:include page="_header.jsp"></jsp:include>

	<div class="movieInfo">
		<c:forEach var="movieAttributes" items="${requestedMovie}" >
				<h1><c:out value="${movieAttributes[0]}" /></h1>
				<img src="<c:out value="${movieAttributes[3]}" />"/>
				<div class="movieDesc">
					<p>Released on: <c:out value="${movieAttributes[1]}"/></p>
					<form action="<c:out value="${movieAttributes[4]}"/>">
    					<input type="submit" value="trailer" />
					</form>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas nec eleifend augue. Quisque luctus ultricies urna sit amet ultrices. Nullam iaculis tincidunt dui, quis ornare mi semper vel. Pellentesque malesuada felis vel consectetur pharetra. Etiam eleifend risus nec sem iaculis, non varius sapien aliquet. Nulla sit amet elit id dui sollicitudin finibus. Pellentesque id diam libero. Nulla mi leo, euismod non mi at, mollis ultricies ligula. In quis magna vel nisl cursus lobortis. Curabitur scelerisque ultrices magna eu varius. Duis vitae dolor purus. Cras nec metus accumsan, venenatis lorem ac, semper eros.
					Curabitur quis dui est. Nulla facilisi. Maecenas venenatis libero lacus. Nullam sapien elit, scelerisque ac quam gravida, facilisis mattis ex.</p>
					<a href="browse?browseBy=advSearch&advDirector=<c:out value="${movieAttributes[2]}"/>" >Directed by: <c:out value="${movieAttributes[2]}"/></a>
					<div class="Buy">
						<form method="get" action="${pageContext.request.contextPath}/shoppingCart" >
							<button class= "buyMovie" name = "addMovie" value = "${movieAttributes[0]}">Add to my cart</button>
						</form>
					</div>
				</div>
		</c:forEach>
		
		<div class="starList">
			<p>Staring: </p>
			<c:forEach var="star" items="${stars}">
				<a href="getStar?selected=<c:out value="${star[1]}"/>"><c:out value="${star[0]}"/></a>
			</c:forEach>
		</div>


	</div>
	
</body>
</html>