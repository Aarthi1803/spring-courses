<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <!-- sometimes spring ignores EL - Expression Language like ${alien} so we need to tell spring don't ignore EL tags and converts those tags into values
    EL (Expression Language) is used in JSP files to access Java objects, variables, and methods inside the view.
	By default, some JSP containers might ignore EL expressions (${} syntax) and treat them as plain text.
	Setting isELIgnored="false" ensures that EL is enabled and processed correctly.
    
    isELIgnored="true" (default in some containers)	-> EL expressions (${}) are treated as plain text.
	isELIgnored="false"	-> Ensures EL expressions are evaluated and processed correctly.
    -->
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
	
	<!-- Instead of above line we can also use JSTL - JSP Standard Library. It allows to use certain tag to directly get value-->
	<%-- <h2> Result is: ${result} </h2> --%>
	
	<h2> Welcome To Alien </h2>
	<p> Alien Detail: ${alien} </p>
	<%-- <p> Alien Detail: ${alien1} </p> --%>
	
	<p> Welcome to the ${course} World </p>
</body>
</html>