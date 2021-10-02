<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="allAccounts" scope="request" type="java.util.Collection" />
<jsp:useBean id="header1" scope="request" type="java.lang.String" />
<jsp:useBean id="header2" scope="request" type="java.lang.String" />
<jsp:useBean id="header3" scope="request" type="java.lang.String" />
<jsp:useBean id="header4" scope="request" type="java.lang.String" />
<jsp:useBean id="client" scope="request" type="com.mybank.model.Client" />
<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="src/main/webapp/WEB-INF/includes/head-meta.jsp" />
  <title>"${client.username}'s Saving accounts | Online Bank"</title>
</head>
<body>
<h1>Current Account</h1>

<c:choose>
  <c:when test="${allAccounts.size() > 0}">
    <table class="curr-acc">
      <tr class="row">
        <th class="table-header"><c:out value="${header1}" /></th>
        <th class="table-header"><c:out value="${header2} (MAD)" /></th>
        <th class="table-header"><c:out value="${header3} (MAD)" /></th>
        <th class="table-header"><c:out value="${header4} (MAD)" /></th>
      </tr>

      <c:forEach var="account" items="${allAccounts}">
        <tr class="row row-data">
          <td class="col-data"><c:out value="${account.wording}" /></td>
          <td class="col-data">
            <fmt:formatNumber
             value="${account.balance}"
             type="number" />
          </td>
          <td class="col-data">
            <fmt:formatNumber
             value="${account.interestRate}"
             type="number" />
          </td>
          <td class="col-data">
            <fmt:formatNumber
             value="${account.ceiling}"
             type="number" />
          </td>
        </tr>
      </c:forEach>
    </table>
  </c:when>
  <c:otherwise>
    <c:out value="You double not have Saving Accounts" />
  </c:otherwise>
</c:choose>
<a href="${pageContext.request.contextPath}/dashboard"
   class="go-to-dashboard">Return to the dashboard
</a>
</body>
</html>
