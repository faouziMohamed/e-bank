<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="client" type='com.mybank.model.Client' required="true" %>

<header class="user-header top-center-header">
  <nav class="menu" aria-label="menu">
    <div class="scaled-profile-pic-wrapper">
      <img
        alt="User profile picture"
        class="user-profile-picture-scaled"
        src="${pageContext.request.contextPath}/public/images/coder.gif"
        title="${client.username}'s profile picture"
        width="250" />
      <i class="fas fa-sort-down sort-down"></i>
    </div>
    <div class="wrapper hidden">
      <div class="top-arrow"></div>
      <div class="user-profile">
        <figure class="user-figure">
          <div class="user-picture-wrapper">
            <img
              src="${pageContext.request.contextPath}/public/images/coder.gif"
              alt="User profile picture"
              class="user-picture"
              width="250"
              height="250"
            />
          </div>
          <figcaption class="user-name">
            <c:out value="${client.firstName} ${client.lastName}" />
          </figcaption>
        </figure>
      </div>
      <nav class="user-actions-wrapper">
        <ul class="action-wrapper">
          <li class="actions-item">
            <a href="${pageContext.request.contextPath}/dashboard"
               class="action-item-link">
              <i class="far fa-user-alt"></i>
              Dashboard
            </a>
          </li>
          <li class="actions-item">
            <a href="${pageContext.request.contextPath}/myaccount"
               class="action-item-link">
              <i class="far fa-user-alt"></i>
              Account
            </a>
          </li>
          <li class="actions-item">
            <a href="${pageContext.request.contextPath}/notifs"
               class="action-item-link">
              <i class="fas fa-bell"></i>
              Notifications
            </a>
          </li>
          <li class="actions-item">
            <a href="${pageContext.request.contextPath}/logout"
               class="action-item-link">
              <i class="fad fa-sign-out"></i>
              Logout
            </a>
          </li>
        </ul>
      </nav>
    </div>
  </nav>
</header>
