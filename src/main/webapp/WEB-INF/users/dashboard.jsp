<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="client" scope="request" type="com.mybank.model.Client" />
<jsp:useBean id="hour" scope="request" type="java.lang.Integer" />


<%@taglib prefix="t" tagdir='/WEB-INF/tags/connected' %>
<t:layout
  title="${client.username}'s Dashboard | Online Bank"
  client='${client}'>
  <div class="main-section-wrapper">
    <h1>
      Good
      <c:out value="${5 <= hour && hour < 12? 'Morning': 'Evening'}" />
      <c:out value="${client.firstName} ${client.lastName}" />
    </h1>
    <div id="chart-container" class="chart-container">
      <div class="chart-item-wrapper">
        <div class="loading-wrapper">
          <div class="load-spinner"></div>
          <span class="loading-text">Loading data...</span>
        </div>
      </div>
    </div>
  </div>
  <script
    type="module"
    src="${pageContext.request.contextPath}/public/js/layout/layout.js">
  </script>

    <script
     type="module"
     async
     src="${pageContext.request.contextPath}/public/js/dashboard/dashboard.js">
    </script>

  <script
    type="module"
    async
    src="${pageContext.request.contextPath}/public/js/transfer/index.js">
  </script>


</t:layout>
