<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>

<style>



	.sidebar
	{
		position:fixed;
		left: 0;
		top: 74px;
		background-color: rgba(17, 17, 17, 0.9);
		height: 100%;
		width: 0;
		z-index: 1;
		overflow-x: hidden;
		padding-top: 25px;
		transition: 0.5s;
		float: bottom;
	}
	
	.sidebar a
	{
		padding: 5px;
		text-decoration: none;
		font-size: 15px;
		color: #818181;
		display: block;
		transtion: 0.3s;
	}
	.categories {
		padding: 5px;
	}
	
	.sidebar a:hover, .offcanvas a:focus
	{
		color: #f1f1f1;
	}
	
	.sidebar .closebtn
	{
		position: absolute;
		top: 0;
		right: 20px;
		font-size:30px;
		margin-left: 50px;
	}
	h3
	{
		padding-left: 8px;
		font-family: 'Cinzel', serif;
		color: #d01717;
		
	}
	.buttonContainer
	{
		font-family: 'Cinzel', serif;
		font-size: 20px;
		font-color: white;
		list-style-type: none;
	}
	
	#content {
		transition: margin-left .5s;
		padding: 16px;
	}
	
</style>

<head>
	<link href="https://fonts.googleapis.com/css?family=Amatic+SC|Cinzel" rel="stylesheet">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title></title>
</head>

<div class = "sidebar" id = "sideNav">
	<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
	<h3>Genre List</h3>
	<ul class="categories">
		<%List list = new ArrayList(); 
		list = (ArrayList)request.getAttribute("genresList");%>
		<% for(int i = 0; i < list.size(); i++)
		{%>
			<div class = "buttonContainer">
				<a href = "browse?browseBy=genreName&genreName=<%=(String)list.get(i)%>&orderBy=title">
					<%=(String) list.get(i)%>
				</a>
			</div>
		<%}%>
	</ul>
</div>


<script>
	function openNav() {
	    document.getElementById("sideNav").style.width = "250px";
	    document.getElementById("content").style.marginLeft = "250px";
	}
	
	function closeNav() {
	    document.getElementById("sideNav").style.width = "0";
	    document.getElementById("content").style.marginLeft = "0";
	
	}
</script>