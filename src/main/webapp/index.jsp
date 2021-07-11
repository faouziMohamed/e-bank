<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="/WEB-INF/components/head-meta.jsp">
    <jsp:param name="title" value="Home | Online Bank"/>
  </jsp:include>
</head>
<body>
<h1>My very first land page using JEE</h1>
<br/>
<a href="${pageContext.request.contextPath}/login"
>Go to Authentication page</a
>
</body>
</html>
