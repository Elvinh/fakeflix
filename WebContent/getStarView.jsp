<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	.starInfo { 		
		background-color: #2c2d30;
		width: 80%;
		margin: auto;
		height: 400px;
		box-shadow: 3px 6px 114px -17px rgba(0,0,0,1);
		overflow: auto;
		margin-top: 100px;
	}
	.starInfo img {
		float: left;
		margin: 0 25px 0 0;
		max-width:100%;
		max-height:100%;
	}
	.starInfo h1 {
		color: white;
	}
	.starInfo p {
		color: white;
		padding: 15px;
	}
	.moviesByStar {
		height: 0;
		width: 100%;
		transition: 0.5s;
		overflow-y: hidden;
		z-index: 1;
		position: fixed;
		bottom: 0;
		left: 0;
		background: rgba(17, 17, 17, 0.8);
		
	}
	.moviesByStar img {
		width: 225px;
	 	height: 350px;
	}
	.moviesByStar table {
		border-spacing: 15px 0;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#282828">
	<c:import url="_header.jsp" />

	<div class="starInfo">
			<img src="<c:out value="${requestedStar[4]}"/>"  BORDER="0"/>
			<h1><c:out value="${requestedStar[1]}"/></h1>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas nec eleifend augue. Quisque luctus ultricies urna sit amet ultrices. Nullam iaculis tincidunt dui, quis ornare mi semper vel. Pellentesque malesuada felis vel consectetur pharetra. Etiam eleifend risus nec sem iaculis, non varius sapien aliquet. Nulla sit amet elit id dui sollicitudin finibus. Pellentesque id diam libero. Nulla mi leo, euismod non mi at, mollis ultricies ligula. In quis magna vel nisl cursus lobortis. Curabitur scelerisque ultrices magna eu varius. Duis vitae dolor purus. Cras nec metus accumsan, venenatis lorem ac, semper eros.
				Curabitur quis dui est. Nulla facilisi. Maecenas venenatis libero lacus. Nullam sapien elit, scelerisque ac quam gravida, facilisis mattis ex. Nullam maximus ex scelerisque leo mollis, nec accumsan nunc tincidunt. Praesent quis fermentum ligula. Aenean quis dui a ex varius rhoncus sit amet nec nulla. Praesent maximus magna eu lorem commodo, at porta ligula accumsan. Nullam urna magna, aliquam id lorem at, placerat luctus lorem. Praesent aliquam consequat magna sed tempus. Donec non imperdiet nibh, ut lacinia ex. Suspendisse ut erat ut est gravida porta ut ac nisi. Duis ac fermentum erat. Proin neque lectus, mattis eu nunc maximus, pretium volutpat lacus. Donec feugiat orci vitae consectetur volutpat.</p>
			<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; Movies by Star</span>
			
	</div>
	
	<div id="movieList" class="moviesByStar">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
		<table>
			<tr>
			<c:forEach var="movieAttributes" items="${moviesByStar}">
				<td><a href="getMovie?selected=<c:out value="${movieAttributes[0]}"/>" >
						<img src="<c:out value="${movieAttributes[1]}"/>"  BORDER="0"/>
					</a>
				</td>
			</c:forEach>
			</tr>
		</table>
	</div>
	
	<script>
		function openNav() {
		    document.getElementById("movieList").style.height = "350px";
		}
		
		function closeNav() {
		    document.getElementById("movieList").style.height = "0";
		}
	</script>

</body>
</html>