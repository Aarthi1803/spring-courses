<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta charset="UTF-8">
<title>Result Page</title>
</head>
<body>
	<!-- Using session object to get result value -->
	<%-- <h2> Result is: <%= session.getAttribute("result") %> </h2> --%>
	
	<!-- Instead of above line we can also use JSTL - JSP Standard Tag Library. It allows to use certain tag to directly get value -->
	<%-- <h2> Result is: ${result} </h2> --%>
	
	<h2> Welcome To Alien </h2>
	<p> Alien Detail: ${alien} </p>
	<%-- <p> Alien Detail: ${alien1} </p> --%>
	
	<p> Welcome to the ${course} World </p>
</body>
</html>