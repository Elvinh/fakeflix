<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<style>
	@import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
	.heading
	{
		padding-top: 10px;
	}
	h2
	{
		font-size: 20px;
	 	color: #fff;
	 	text-transform: uppercase;
	 	
	  	font-weight: 500;
	  	text-align: center;
	  	padding-top: 10px;
	  	margin-bottom: 15px;
	  	font-family: 'Roboto', sans-serif;	  	
	  	
	}
	
	table
	{
		width:100%;
		table-layout: fixed;
	}
	.heading
	{
		background-color: rgba(255,255,255,0.3);
		border-radius: 31px 31px 31px 31px;
-moz-border-radius: 31px 31px 31px 31px;
border: 2px solid #2c2d3;
		
	}
	
	.tbl-content
	{
		height:300px;
  		overflow-x:auto;
 		margin-top: 0px;
  		border: 1px solid rgba(255,255,255,0.3);
  		-webkit-box-shadow: 2px 4px 20px 3px rgba(0,0,0,0.75);
-moz-box-shadow: 2px 4px 20px 3px rgba(0,0,0,0.75);
box-shadow: 2px 4px 20px 3px rgba(0,0,0,0.75);
  		
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
	
	body
	{
	  background-color: #2c2d30;
	  font-family: 'Roboto', sans-serif;
	  
	}
	
	form
	{
		  font-family: 'Roboto', sans-serif;
		  
	
	}
	.scButton
	{
		  font-family: 'Roboto', sans-serif;
		  text-align: center;
	    background-color: #d01717;
	    color: white;
	    margin: 10px 10px 15px 10px;
	    padding:10px;
	    font-size: 13px;
	    border: none;
	    cursor: pointer;
	    outline: none;
		
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
			<h2> My Cart</h2>
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
		        		<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value="${item.value.price * item.value.quantity}"/></td>
		        	
		        		<td>
		        		<form method="get" action="${pageContext.request.contextPath}/shoppingCart">
			        			<input type="number" name="adjQuantity" min="1" max="99" value="${item.value.quantity}">
			        			<input type="hidden" name="adjMovieName" value="${item.key}">
  								<button class = "scButton" type="submit">Update</button>
			        		</form>
			        		
		        		</td>
		        		<td>
		        			<form method="get" action="${pageContext.request.contextPath}/removeFromCart">
								<button class = "scButton" type="submit"  name = "removeMovie" value = "${item.key}">Remove</button>
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