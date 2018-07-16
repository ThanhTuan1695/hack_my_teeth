<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display all records</title>
</head>
<body>
<table>
	<tr>
		<th>Customer ID</th>
		<th>First Name</th>
		<th>Last Name</th>
	</tr>
	<c:forEach items="${userList}" var="cus">
		<tr>
			<td><c:out value="{$cus.cusID}"></c:out></td>
			<td><c:out value="{$cus.firstName}"></c:out></td>
			<td><c:out value="{$cus.lastName}"></c:out></td>
			
		</tr>
	</c:forEach>
</table>
</body>
</html>