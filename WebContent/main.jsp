<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main</title>
<style>
	h1 {
		text-align: left;
		display: inline-block
	}
	input {
		width: 1028px;
		box-sizing: border-box;
    	border: 2px solid #ccc;
   		border-radius: 4px;
    	font-size: 16px;
    	background-color: white;
    	background-position: 10px 10px; 
    	background-repeat: no-repeat;
    	padding: 12px 20px 12px 40px;
	}
	.search {
		padding-top: 150px;
	}
	/* Dropdown Button */
	.dropbtn {
	    background-color: #4CAF50;
	    color: white;
	    padding: 16px;
	    font-size: 16px;
	    border: none;
	    cursor: pointer;

	}
	/* The container <div> - needed to position the dropdown content */
	.dropdown {
	    position: relative;
	    display: inline-block;
	    float: center;
	}
	
	/* Dropdown Content (Hidden by Default) */
	.dropdown-content {
	    display: none;
	    position: absolute;
	    background-color: #f9f9f9;
	    min-width: 160px;
	    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
	    z-index: 1;
	}
	
	/* Links inside the dropdown */
	.dropdown-content button {
	    border:none; 
	    color: black;
	   	background-color: #f9f9f9;
	    padding: 12px 16px;
	    width: 160px;
	   	cursor: pointer;
	    
	}
	
	
	/* Change color of dropdown links on hover */
	
	.dropdown-content button:hover {background-color: #f1f1f1}
	/* Show the dropdown menu on hover */
	.dropdown:hover .dropdown-content {
	    display: block;
	}
	
	/* Change the background color of the dropdown button when the dropdown content is shown */
	.dropdown:hover .dropbtn {
	    background-color: #3e8e41;
	}
	
	.login
	{
		
	}
</style>
</head>
<body>
	
	<h1>FakeFlix</h1>
	<h3>Hello <%= (String) session.getAttribute("loginedU") %></h3>
	<div class = "login">
		<form method = "get" action = "<%= request.getContextPath() %>/login">
			<button class = "link" name = "Login" value = "Login"> Login here</button>
		</form>
	</div>
	<div class="dropdown">
		<button class="dropbtn">Browse</button>
		<div class="dropdown-content">
			<form method="get" action="<%= request.getContextPath() %>/browse" >
	    		<button class="link" name="browseBy" value="title">By Title</button>
				<button class="link" name="browseBy" value="genre">By Genre</button>
			</form>
		</div>
	</div>
	
	<form class="search" method="post" action="">
		<input type="text" name="search" placeholder="Search for movie titles...">
	</form>
<body>
</html>