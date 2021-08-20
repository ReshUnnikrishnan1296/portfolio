<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ludo main menu</title>
</head>
<body> 
<h2>Welcome to Ludo Game Application</h2>
<ol>
  <li>Player Login</li>
  <li>Player Register</li>
  <li>Exit</li>
</ol> 
	<form:form modelAttribute="Main_menu" method="POST" action="/d_menu">
		Please enter your menu selection:
		<form:input path="sel_menu" />
		<input type="submit" value="Submit">
	</form:form>
</body>
</html>