<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="../components/head-meta.jsp">
    <jsp:param name="title" value="${title}"/>
  </jsp:include>
</head>
<body>
<h1 class="title"><c:out value="${pageHeading}"/></h1>
<p>
  You are about to be redirected to the login page in
  <span id="timeout">5</span> seconds. If not click
  <a href="${pageContext.request.contextPath}/login" class="go-back-to-login">here</a>.
</p>

<script
 src="${pageContext.request.contextPath}/resources/js/logout.js"></script>

</body>
</html>
