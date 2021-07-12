<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="../components/head-meta.jsp">
    <jsp:param name="title"
               value="${client.username}'s  Dashboard | Online Bank"/>
  </jsp:include>
</head>
<body>

<h1>
  Good
  <c:out value="${5 <= hour && hour < 12? 'Morning': 'Evening'}"/>
  <c:out value="${client.firstName} ${client.lastName}"/>
</h1>

<p>Available operations</p>
<ul class="main-op">
  <c:set var="contextPath" scope="page"/>
  <li><a href="${contextPath}/currentAccounts" class="operation-link">Current
    accounts</a></li>
  <li><a href="${contextPath}/savingAccounts" class="operation-link">Saving accounts</a></li>
  <li><a href="#" class="operation-link">make a transfer</a></li>
  <li><a href="${contextPath}/logout" class="operation-link">Logout</a></li>
</ul>
</body>
</html>
