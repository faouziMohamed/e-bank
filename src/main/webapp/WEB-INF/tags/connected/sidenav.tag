<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="main-header">
  <nav class="main-navbar">
    <div class="burger-container">
      <input type="checkbox" id="checkbox-input" class="checkbox-input" />
      <label class="menu-btn" for="checkbox-input"></label>
      <label for="checkbox-input" class="menu-btn-text">
        Available operations
      </label>
    </div>
    <ul class="main-op main-menu closed">
      <c:set var="contextPath" scope="page"
             value='${pageContext.request.contextPath}' />
      <li class="main-op-item">
        <a href="${contextPath}/dashboard" class="operation-link">
          <i class="fad fa-analytics" aria-hidden="true"></i>
          <span class="nav-link-text">Dashboard</span>
        </a>
      </li>
      <li class="main-op-item">
        <a href="${contextPath}/currentAccounts" class="operation-link">
          <i class="fad fa-coins" aria-hidden="true"></i>
          <span class="nav-link-text">Current accounts</span>
        </a>
      </li>
      <li class="main-op-item">
        <a href="${contextPath}/savingAccounts" class="operation-link">
          <i class="fad fa-piggy-bank" aria-hidden="true"></i>
          <span class="nav-link-text">Saving accounts</span>
        </a>
      </li>
      <li class="main-op-item">
        <a href="${contextPath}/transfer" class="operation-link">
          <i class="fad fa-envelope-open-dollar" aria-hidden="true"></i>
          <span class="nav-link-text">Make a transfer</span>
        </a>
      </li>
      <li class="main-op-item">
        <a href="${contextPath}/logout" class="operation-link logout">
          <i class="fad fa-sign-out-alt" aria-hidden="true"></i>
          <span class="nav-link-text">Logout</span>
        </a>
      </li>
    </ul>
  </nav>
</header>
