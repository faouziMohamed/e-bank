<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "My very first land page using JEE" %>
</h1>
<br/>
<a href="${pageContext.request.contextPath}/login">Go to Authentication page</a>
</body>
</html>
