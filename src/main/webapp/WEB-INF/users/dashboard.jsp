<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="../components/head-meta.jsp">
    <jsp:param name="title"
               value="${client.username}'s  Dashboard | Online Bank"/>
  </jsp:include>
  <link rel="stylesheet"
        type="text/css"
        href="${pageContext.request.contextPath}/resources/styles/dashboard.css">
  <link
   rel="stylesheet"
   type="text/css"
   href="${pageContext.request.contextPath}/resources/styles/login.css"
  />
</head>
<body>
<main class="main-content">
  <div class="content-wrapper">
    <header class="main-header">
      <nav class="main-navbar">
        <p>Available operations</p>
        <ul class="main-op">
          <c:set var="contextPath" scope="page"/>
          <li class="main-op-item">
            <a href="${contextPath}/currentAccounts" class="operation-link">Current
              accounts
            </a>
          </li>
          <li class="main-op-item">
            <a href="${contextPath}/savingAccounts" class="operation-link">Saving
              accounts
            </a>
          </li>
          <li class="main-op-item">
            <a href="${contextPath}/transfer" class="operation-link">Make a
              transfer
            </a>
          </li>
          <li class="main-op-item">
            <a href="${contextPath}/logout" class="operation-link">Logout
            </a>
          </li>
        </ul>
      </nav>
    </header>
    <section class="main-section">
      <div class="main-div-content-wrapper"><h1>
        Good
        <c:out value="${5 <= hour && hour < 12? 'Morning': 'Evening'}"/>
        <c:out value="${client.firstName} ${client.lastName}"/>
      </h1></div>
    </section>
  </div>
</main>
</body>
</html>
