<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>

<style>

	.browsedContent	
	{
		transition: margin-left .5s;
		padding: 16px;
	}

	.sidebar
	{
		display: none;
		position:fixed;
		left: 0;
		background-color: rgba(17, 17, 17, 0.9);
		height: 100%;
		width: 0;
		z-index: 1;
		top: 90px;
		overflow-x: hidden;
<<<<<<< HEAD
		padding-top: 25px;
=======
		padding-top: 30px;
>>>>>>> cbe4ae2ff598e83b52c18dce1d1897b23f2a8d4c
		transition: 0.5s;
		float: bottom;
	}
	
	.sidebar a
	{
		padding: 5px 5px 5px 16px;
		text-decoration: none;
		font-size: 15px;
		color: #818181;
		display: block;
		transtion: 0.3s;
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
	
	
	
</style>

<head>
	<link href="https://fonts.googleapis.com/css?family=Amatic+SC|Cinzel" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>

	<ul>
	<div class = "sidebar" id = "sideNav" style = "width:25%; left;0">
	<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
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
		<%}%>
		
		
	</div>
	</ul>
	
	<script>
function openNav() {
	document.getElementById("sideNav").style.display = "block";
    document.getElementById("sideNav").style.width = "250px";
    document.getElementById("browsedContent").style.marginLeft = "250px";
}

function closeNav() {
	document.getElementById("sideNav").style.display = "none";
    document.getElementById("sideNav").style.width = "0";
    document.getElementById("browsedContent").style.marginLeft = "0";

}
</script>
