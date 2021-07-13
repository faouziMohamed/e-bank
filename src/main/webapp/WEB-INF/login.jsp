<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="components/head-meta.jsp">
    <jsp:param name="title" value="${title}"/>
  </jsp:include>
  <link
   rel="stylesheet"
   type="text/css"
   href="${pageContext.request.contextPath}/resources/styles/login.css"
  />
</head>
<body class="main-body">
<section class="body-content">
  <h1 class="title"><c:out value="${pageHeading}"/></h1>
  <c:if test="${!empty error}">
    <section class="error">
      <p class="error-message">
        <c:out value="${error}"/>
      </p>
    </section>
  </c:if>
  <form
   action="${pageContext.request.contextPath}/login"
   method="post"
   class="auth-form"
  >
    <section class="form-content">
      <div class="row-content identifiant">
        <label for="username" class="label-auth"> Username : </label>
        <input
         type="text"
         id="username"
         name="username"
         class="input-field pass-auth"
        />
      </div>
      <div class="row-content password">
        <label for="password" class="label-auth"> Password : </label>
        <input
         type="password"
         id="password"
         name="password"
         class="input-field pass-auth"
        />
      </div>
      <div class="row-content btn-row">
        <button class="reset-btn" type="reset">Reset</button>
        <button class="submit-btn" type="submit">Connect</button>
      </div>
    </section>
  </form>
</section>
</body>
</html>
