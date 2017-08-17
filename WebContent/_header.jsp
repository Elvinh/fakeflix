<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<style>
.page-head {
    background-color: #9933cc;
    display: flex;
    align-items: center;
}
.page-head h1 {
	flex-shrink: 0;
	margin: 15px
}

.page-head h1 a {
	text-decoration: none;
	color: white;
}

/* Dropdown Button */
.dropbtn {
    background-color: #4CAF50;
    color: white;
    padding: 16px;
    font-size: 16px;
    border: none;
    cursor: pointer;
    flex-shrink: 0;

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

.searchBar {
	margin: 0px 15px;
	flex-grow: 1;
	min-width: 18rem;
	
}
.searchBar 	input {
	box-sizing: border-box;
   	border: 2px solid #ccc;
  	border-radius: 4px;
   	font-size: 16px;
   	background-color: white;
   	background-position: 10px 10px; 
   	background-repeat: no-repeat;
   	width: 100%;
   	padding: .4rem;
   	

}
.login {
  flex-shrink: 0;
  margin: 0px 15px 0px 15px;
}

.logout {
  flex-shrink: 0;
  margin: 0px 15px 0px 15px;
}



</style>

<header class="page-head">
	<h1><a href="home">FakeFlix</a></h1>
	<div class="dropdown">
		<button class="dropbtn">Browse</button>
		<div class="dropdown-content">
			<form method="get" action="<%= request.getContextPath() %>/browse" >
	    		<button class="link" name="browseBy" value="title">By Title</button>
				<button class="link" name="browseBy" value="genre">By Genre</button>
			</form>
		</div>
	</div>
	
		<form class="searchBar" method="get" action="<%= request.getContextPath() %>/getMovie">
			<input type="text" name="selected" placeholder="Search for movie titles...">
		</form>
	
		<form method = "get" action = "<%= request.getContextPath() %>/login">
			<button class = "login" name = "Login" value = "Login"> Login</button>
		</form>

		<form method = "get" action = "<%= request.getContextPath() %>/logout">
			<button class = "logout" name = "Logout" value = "Logout"> Logout</button>
		</form>
	
</header>



