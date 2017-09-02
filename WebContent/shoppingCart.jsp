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
	
	table
	{
		position: absolute;
		margin: auto;
		width: 600px;
		top:0;
		left:0;
		bottom:0;
		right: 0;
		height: 100px;
		padding-bottom: 200px;
	}
	
	th, td
	{
		text-align: left;
		padding: 8px;
		font-family: 'Open Sans Condensed', sans-serif;
	}
	
	tr:nth-child(even)
	{
		background-color: white;
	}
	
	th
	{
		background-color: white;
	}

</style>

<body bgcolor="#282828">
<jsp:include page="_header.jsp"></jsp:include>
	<link href="https://fonts.googleapis.com/css?family=Amatic+SC|Cinzel" rel="stylesheet">

	<h2>My shopping cart</h2>

	<table>
		<tr>
			<th>Movie Title</th>
			<th>Price</th>
			<th>Quantity</th>
		</tr>
		
		
		<%
		
		Cookie[] cookies =  request.getCookies();
		String name = "";
		int quantity = 0;
		int counter = 0;
		String tempName = "";
		String tempValue = "";
	
		// 1. SC works so far with user logging in and adding movies to cart. 
		// 1. Correctly takes out the entire list when user logs out
		// 2. When anon user add movies to list, correctly adds them in
		// 2. And leaves them in until cookie expires.
		// SITUATION 3. NEEDS WORK.
		// 3. Fix situation where user adds in movies to SC, but then logins.
		// Currently the indexing is messed up. The cookies list is out of order, will either
		// A. Need to find a formula to fix the indexing
		// B. When user logs in, place info in the correct slots, then add back the movies to the list.
		
		if(cookies != null)
		{
			for(int i = 0; i < cookies.length; i++)
			{
				System.out.println("Length of Cookies @ SC: " + cookies.length);
				tempName = cookies[i].getName();
				tempValue = cookies[i].getValue();
				
				%><p><%= tempName %></p>
				<p><%= tempValue %></p><%
			}
			if(cookies[0].getName().equals("loginedUser"))
			{
				counter = 4;
			}
			else if (cookies[1].getName().equals("loginedUser"))
			{
				counter = 4;
			}
			else
			{
				counter = 0;	
			}
			for(int i = counter; i < cookies.length; i++)
			{
					//name = cookies[i].getName();
					//quantity = Integer.parseInt(cookies[i].getValue());
					%>
					
					<tr>
						<td>
							<p> <%= name %></p>
						</td>
						<td>
							<p> <%= quantity %></p>
						</td>
						<td>
							<p> 1</p>
						</td>
					</tr><% 
			}
		}
			
		
		%>
		
		<tr>
			<td>
				<c:forEach var = "movieList" items = "${addedMovies}">
					<a href = "shoppingCart?"selected=<c:out value = "${movieList[0]}"/>">
						<c:out value = "${movieList[0]}"/>
					</a>
				</c:forEach>
			</td>
			<td>
				<c:forEach var = "movieList" items = "${addedMovies}">
					<p><c:out value = "${movieList[1]}"/><p>
				</c:forEach>
			</td>
			<td>
				<c:forEach var = "movieList" items = "${addedMovies}">
					<input type = "text" placeholder = "1" name = quantity" required>
				</c:forEach>
			</td>
		</tr>
	
	</table>
</body>
</html>