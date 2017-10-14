<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<style>
.page-head {
	background-color: rgb(54, 25, 25);
    background-color: rgba(17, 17, 17, 0.8);
    display: flex;
    align-items: center;
    width: 100%;
	position: fixed;
	z-index: 2;
	top: 0;
	left: 0;
	
}
.page-head h1 {
	flex-shrink: 0;
	margin: 15px;
	font-family: 'Cinzel', serif;
}

.page-head h1 a {
	text-decoration: none;
	color: #d01717;
}

/* Dropdown Button */
.dropbtn {
    background-color: #d01717;
    color: white;
    padding: 16px;
    font-size: 16px;
    border: none;
    cursor: pointer;
    flex-shrink: 0;
    outline: none;

}
/* The container <div> - needed to position the dropdown content */
.dropdown {
    position: relative;
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
    padding: 15px;
    width: 160px;
   	cursor: pointer;
    outline: none;
}


/* Change color of dropdown links on hover HEY */

.dropdown-content button:hover {background-color: #f1f1f1}
/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {
    display: block;
}

/* Change the background color of the dropdown button when the dropdown content is shown */
.dropdown:hover .dropbtn {
    background-color: #921010;
}

.searchBar {
	margin: 0px 15px;
	flex-grow: 1;
	min-width: 9rem; 
}
.searchBar 	input {
	box-sizing: border-box;
   	font-size: 13px;
   	border: none;
	border-radius:15px; 
	-moz-border-radius:15px; 
	-webkit-border-radius:15px; 
   	background-color: #282828;
   	background-image: url("./images/search.png");
   	background-position: 10px 10px; 
   	background-repeat: no-repeat;
   	width: 100%;
    padding: 12px 20px 12px 40px;
   	outline: none;
   	color: #b5b5b5;

}
.login {
font-family: 'Cinzel', serif;
  flex-shrink: 0;
	border-bottom: solid #d01717 3px;

 }
.login a {
    display: block;
    color: #d01717;
    text-align: center;
    padding: 25px 25px;
    text-decoration: none;
    
}

.login a:hover {
    background-color: #d01717;
    color: white;
}

.logout {
  flex-shrink: 0;
  font-family: 'Cinzel', serif;

 }
.logout a {
    display: block;
    color: #d01717;
    text-align: center;
    padding: 25px 25px;
    text-decoration: none;
}

.logout a:hover {
    background-color: #d01717;
    color: white;
}

.myCart
{
	font-family: 'Cinzel', serif;
  	flex-shrink: 0;
}

.myCart a
{

    color: #d01717;
    text-align: center;
    padding: 24px 24px;
    text-decoration: none;
}
.myCart i{

	background-color: gray;
	color: white;
	position: absolute;
				
	top: 13px;
	right: 17px;
	text-align: center;
    font-weight: bold;
    border-radius: 12px; /* one half of ( (border * 2) + height + padding ) */

    color: white;
    font: bold 15px/13px Helvetica, Verdana, Tahoma;
    height: 16px; 
    min-width: 14px;
    padding: 4px 3px 0 3px;
    text-align: center;
}



.advSearch
{
	font-family: 'Cinzel', serif;
  	flex-shrink: 0;

}

.advSearch a
{
	display: block;
    color: #d01717;
    text-align: center;
    padding: 25px 25px;
    text-decoration: none;
}

.advSearch a:hover
{
 	background-color: #d01717;
    color: white;
}

</style>
<head>
	<link href="https://fonts.googleapis.com/css?family=Amatic+SC|Cinzel" rel="stylesheet">
</head>

<body>
<header class="page-head">
		<h1><a href="home">FAKEFLIX</a></h1>
		<div class="dropdown">
			<button class="dropbtn">Browse</button>
			<div class="dropdown-content">
				<form method="get" action="${pageContext.request.contextPath}/browse" >
		    		<button class="link" name="browseBy" value="title">By Title</button>
					<!--<button class="link" name="browseBy" value="genre">By Genre</button>-->
				</form>
			</div>
		</div>
		
			<form class="searchBar" method="get" action="${pageContext.request.contextPath}/browse">
				<input type="hidden" name="browseBy" value="advSearch">
				<input type="text" name="advTitle" placeholder="Search for movie titles...">
			</form>
			
			
			<div class = "advSearch">
				<a href = "advSearch">Advanced Search</a>
			</div>
			
			<c:set var="id" scope="session" value="${user}"/>
			<c:if test="${empty id}">
			<div class="login">
					<a href="login">LOGIN</a>
			</div>
			</c:if>
			
			<c:set var="id" scope="session" value="${user}"/>
			<c:if test="${not empty id}">
				<div class="logout">
						<a href="logout">LOGOUT</a>	
				</div>
			</c:if>
			
			<div class = "myCart">
				<a href = "${pageContext.request.contextPath}/cart"><img src="images/cart-of-ecommerce.png"WIDTH="36" HEIGHT="36" BORDER="0"></a>
				<i>${sessionScope.cart.count}</i>
			</div>
		
		
</header>
</body>
