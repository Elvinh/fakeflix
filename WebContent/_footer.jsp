<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
html {
    position: relative;
    min-height: 100%;
}
body {
    margin: 0 0 100px; /* bottom = footer height */
    padding: 0 0 25px 0;
}
footer {
	background-color: rgb(54, 25, 25);
    background-color: rgba(17, 17, 17, 0.8);
    width: 100%;
	position: absolute;
	bottom: 0;
	left: 0;
	right: 0;
	color: white;
	padding: 1rem;
	height: 100px;
	
	
}

.browse {
	float:left
}
.info {
	float:left
}

</style>
</head>
<body>
<footer>
<div class="container">
	<div class="browse">
		<a>Browse By: </a>
		</br>
		<c:set var="alphabet" value="1,2,3,4,5,6,7,8,9" />
		<c:forTokens var="letter" items="${alphabet}" delims=",">
		    <a>${letter}</a>
		</c:forTokens>
		</br>
		<c:set var="alphabet" value="A,B,C,D,E,F,G,H,I,J,K,L,M" />
		<c:forTokens var="letter" items="${alphabet}" delims=",">
		    <a>${letter}</a>
		</c:forTokens>
		</br>
		<c:set var="alphabet" value="N,O,P,Q,R,S,T,U,V,W,X,Y,Z" />
		<c:forTokens var="letter" items="${alphabet}" delims=",">
		    <a>${letter}</a>
		</c:forTokens>
	</div>
	<div class="info">
		<p>About This Website</p>
	</div>
</div>





</footer>

</body>
</html>