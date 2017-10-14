<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<style>
	
	h1
	{
		font-size: 30px;
	 	color: #fff;
	 	text-transform: uppercase;
	  	font-weight: 300;
	  	text-align: center;
	  	padding-top: 10px;
	  	margin-bottom: 15px;
	}
	
	table
	{
		width:100%;
		table-layout: fixed;
	}
	.heading
	{
		background-color: rgba(255,255,255,0.3);
	}
	
	.tbl-content
	{
		height:300px;
  		overflow-x:auto;
 		margin-top: 0px;
  		border: 1px solid rgba(255,255,255,0.3);
	}
	
	th
	{
		padding: 20px 15px;
  		text-align: left;
  		font-weight: 500;
  		font-size: 16px;
  		color: #fff;
  		text-transform: uppercase;
	}
	td
	{
		padding: 15px;
  		text-align: left;
  		vertical-align:middle;
  		font-weight: 300;
  		font-size: 12px;
  		color: #fff;
  		border-bottom: solid 1px rgba(255,255,255,0.1);
	}
	
	@import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
	body
	{
	  background: -webkit-linear-gradient(left, #2C3E50, #4CA1AF);
	  background: linear-gradient(to right, #2C3E50, #4CA1AF);
	  font-family: 'Roboto', sans-serif;
	}
	section
	{
	  margin: 50px;
	  padding-top: 100px;
	}
	
	::-webkit-scrollbar {
    width: 6px;
} 
::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); 
} 
::-webkit-scrollbar-thumb {
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); 
}
$(window).on("load resize ", function() {
  var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
  $('.tbl-header').css({'padding-right':scrollWidth});
}).resize();
</style>

<body bgcolor="#282828">
<jsp:include page="_header.jsp"></jsp:include>
<body>
	<section>
		<div class = "heading">
			<h1> My Cart</h1>
		<table>
			<thread>
				<tr>
					<th>Movie Title</th>
					<th>Price</th>
					<th>Quantity</th>
				</tr>
			</thread>
		</div>
		</div class = "tbl-content">
			<tbody>
				<c:forEach var="item" items="${shoppingCart}">
		        	<tr>
		        		<td><c:out value="${item.key}"/></td>
		        		<td><c:out value="${item.value.price * item.value.quantity}"/></td>
		        		<td>
		        		<form method="get" action="${pageContext.request.contextPath}/shoppingCart">
			        			<input type="number" name="adjQuantity" min="1" max="99" value="${item.value.quantity}">
			        			<input type="hidden" name="adjMovieName" value="${item.key}">
  								<button type="submit">Update</button>
			        		</form>
			        		
		        		</td>
		        		<td>
		        			<form method="get" action="${pageContext.request.contextPath}/removeFromCart">
								<button type="submit"  name = "removeMovie" value = "${item.key}">Remove</button>
							</form>
						</td>
		        	</tr>
		  		</c:forEach>
		  		
	  		</tbody>
	  		
	  		
		</table>
		</div>
	</section>

</body>
</html>