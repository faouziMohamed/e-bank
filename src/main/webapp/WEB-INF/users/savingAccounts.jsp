<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="../components/head-meta.jsp">
    <jsp:param name="title"
               value="${client.username}'s Saving accounts | Online Bank"/>
  </jsp:include>
</head>
<body>
<h1>Current Account</h1>

<c:choose>
  <c:when test="${allAccounts.size() > 0}">
    <table class="curr-acc">
      <tr class="row">
        <th class="table-header"><c:out value="${header1}"/></th>
        <th class="table-header"><c:out value="${header2} (MAD)"/></th>
        <th class="table-header"><c:out value="${header3} (MAD)"/></th>
        <th class="table-header"><c:out value="${header4} (MAD)"/></th>
      </tr>

      <c:forEach var="account" items="${allAccounts}">
        <tr class="row row-data">
          <td class="col-data"><c:out value="${account.wording}"/></td>
          <td class="col-data">
            <fmt:formatNumber
             value="${account.balance}"
             type="number"/>
          </td>
          <td class="col-data">
            <fmt:formatNumber
             value="${account.interestRate}"
             type="number"/>
          </td>
          <td class="col-data">
            <fmt:formatNumber
             value="${account.ceiling}"
             type="number"/>
          </td>
        </tr>
      </c:forEach>
    </table>
  </c:when>
  <c:otherwise>
    <c:out value="You double not have Saving Accounts"/>
  </c:otherwise>
</c:choose>
<a href="${pageContext.request.contextPath}/dashboard"
   class="go-to-dashboard">Return to the dashboard
</a>
</body>
</html>
