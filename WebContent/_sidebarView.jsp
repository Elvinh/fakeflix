<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<style>

	body
	{
		background-color:black;
	}
	
	.sidebar
	{
	
	}
	h3
	{
		font-family: 'Cinzel', serif;
		color: #d01717;
		
	}
	.buttonContainer
	{
		font-family: 'Cinzel', serif;
		font-size: 85%;
		font-color: white;
		list-style-type: none;
	}
	
	
	
</style>

<head>
	<link href="https://fonts.googleapis.com/css?family=Amatic+SC|Cinzel" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<ul>
	<div class = "sidebar" style = "width:25%; left;0">
	<h3>Genre List</h3>
		<%List list = new ArrayList(); 
		list = (ArrayList)request.getAttribute("genresList");%>
		
		
		<% for(int i = 0; i < list.size(); i++)
		{%>
			<div class = "buttonContainer">
				<a href = "browse?browseBy=genreName&genreName=<%=(String)list.get(i)%>&orderBy=title">
				<%=(String) list.get(i)%>
				</a>
			</div>
		<%}
			
		%>
	</div>
	</ul>
</body>
</html>