<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	body { margin: 0; padding: 0; }
	.starInfo { 		
		background-color: #2c2d30;
		width: 80%;
		margin: auto;
		height: 400px;
		position: absolute;
		top: 0;
		right: 0;
		bottom: 0;
		left: 0;
		box-shadow: 3px 6px 114px -17px rgba(0,0,0,1);
		overflow: hidden;
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
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#282828">
	<jsp:include page="_header.jsp"></jsp:include>
	<c:import url="sidebar" />

	<div class="starInfo">
			<%
			List star = new ArrayList();
			star = (ArrayList)request.getAttribute("requestedStar");
			List movies = new ArrayList();
			movies = (ArrayList)request.getAttribute("moviesByStar");
			String starName = (String) star.get(1) + " " + (String) star.get(2);
			%>
			<img src="<%=star.get(4)%>"  BORDER="0"/>
			<h1><%=starName%></h1>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas nec eleifend augue. Quisque luctus ultricies urna sit amet ultrices. Nullam iaculis tincidunt dui, quis ornare mi semper vel. Pellentesque malesuada felis vel consectetur pharetra. Etiam eleifend risus nec sem iaculis, non varius sapien aliquet. Nulla sit amet elit id dui sollicitudin finibus. Pellentesque id diam libero. Nulla mi leo, euismod non mi at, mollis ultricies ligula. In quis magna vel nisl cursus lobortis. Curabitur scelerisque ultrices magna eu varius. Duis vitae dolor purus. Cras nec metus accumsan, venenatis lorem ac, semper eros.

Curabitur quis dui est. Nulla facilisi. Maecenas venenatis libero lacus. Nullam sapien elit, scelerisque ac quam gravida, facilisis mattis ex. Nullam maximus ex scelerisque leo mollis, nec accumsan nunc tincidunt. Praesent quis fermentum ligula. Aenean quis dui a ex varius rhoncus sit amet nec nulla. Praesent maximus magna eu lorem commodo, at porta ligula accumsan. Nullam urna magna, aliquam id lorem at, placerat luctus lorem. Praesent aliquam consequat magna sed tempus. Donec non imperdiet nibh, ut lacinia ex. Suspendisse ut erat ut est gravida porta ut ac nisi. Duis ac fermentum erat. Proin neque lectus, mattis eu nunc maximus, pretium volutpat lacus. Donec feugiat orci vitae consectetur volutpat.</p>

		<table>
			<%for(int i = 0; i < movies.size(); i++) { %>
					<tr><td><a href="getMovie?selected=<%=movies.get(i)%>"><%=movies.get(i)%><a/></td></tr>
			<%} %>
		</table>
	</div>

</body>
</html>