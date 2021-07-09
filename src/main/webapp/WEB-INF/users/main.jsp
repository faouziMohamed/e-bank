<%--
  Created by IntelliJ IDEA.
  User: faouzi
  Date: 7/8/21
  Time: 4:53 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>
        <c:out value="${!empty client? client.name:'User'}'s  Dashboard | Online Bank"/>
    </title>
</head>
<body>

<h1>
    Good
    <c:out value="${5 <= hour && hour < 12? 'Morning': 'Evening'}"/>
    <c:out value="${client.name}"/>
</h1>

<p>Available operations</p>
<ul class="main-op">
    <li><a href="#" class="operation-link">Currents accounts</a></li>
    <li><a href="#" class="operation-link">Savings accounts</a></li>
    <li><a href="#" class="operation-link">make a transfer</a></li>
    <li><a href="#" class="operation-link">Logout</a></li>
</ul>
</body>
</html>
