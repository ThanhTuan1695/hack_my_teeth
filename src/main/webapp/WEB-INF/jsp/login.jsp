<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login here</title>
<form action="/login" method="post">
	<h1>Login</h1>
	<label for="username">Username</label>
	<input type="text" name="username" placeholder="username" required>
	<label for="password">Password</label>
	<input type="password" name="password" placeholder="password" required>
	
 </form>
</head>
<body>

</body>
</html>