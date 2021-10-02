<%@ taglib prefix="connected" tagdir="/WEB-INF/tags/connected" %>
<%@tag description="User connected layout page" pageEncoding='UTF-8' %>
<%@attribute name="title" required="true" %>
<%@attribute name="client" type='com.mybank.model.Client' required="true" %>

<html>
<head>
  <jsp:include page="../includes/head-meta.jsp" />
  <jsp:include page="../includes/head-links.jsp" />
  <title>${title}</title>
</head>
<body>
<main class="main-content">
  <div class="content-wrapper">

    <connected:sidenav />
    <section class="main-section">
      <connected:topnav client='${client}' />
      <jsp:doBody />
    </section>
  </div>
</main>

</body>
</html>
