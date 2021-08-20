<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Game Menu</title>
</head>
<body>
	<h2>Choose the Game type</h2>
	<ol>
  		<li>TWO PLAYER LUDO</li>
  		<li>THREE PLAYER LUDO</li>
  		<li>FOUR PLAYER LUDO</li>
  		<li>COMPUTER VS PLAYER LUDO</li>
  		<li>DASHBOARD MENU</li>
	</ol> 
	<form:form modelAttribute="Game_menu" method="POST" action="/game_menu">
		Please enter your menu selection:
		<form:input path="game_menu" />
		<input type="submit" value="Submit">
	</form:form>
</body>
</html>