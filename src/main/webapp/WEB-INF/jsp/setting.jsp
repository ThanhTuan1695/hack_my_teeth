<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
    
    <%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Setting</title>
<form method="POST" action="/settingVal">
<label><strong>Cookie Type</strong></label>
<select name="UseCookie" required>
<option value="Base64">Base64</option>
<option value="Secure">SecureRandom</option>
</select>
<label><strong>Cookie Param</strong></label>
<select name="CookieParam" required>
<option value="True">HttpOnly</option>
<option value="False">No HttpOnly</option>
</select>
<label><strong>Cookie Type</strong></label>
<select name="SessFix" required>
<option value="Yes">Yes</option>
<option value="No">No</option>
</select>
  <input type="submit" value="Submit">

</form>
</head>
<body>

</body>
</html>