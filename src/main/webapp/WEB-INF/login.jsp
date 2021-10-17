<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="title" scope="request" type="java.lang.String" />
<jsp:useBean id="pageHeading" scope="request" type="java.lang.String" />

<!DOCTYPE html>
<html lang="en">
  <head>
    <jsp:include page="./includes/head-meta.jsp" />
    <jsp:include page="./includes/head-links.jsp" />

    <link
      rel="stylesheet"
      type="text/css"
      href="${pageContext.request.contextPath}/public/style/forms/default.css"
    />

    <link
      rel="stylesheet"
      type="text/css"
      href="${pageContext.request.contextPath}/public/style/login.css"
    />
    <title>${title}</title>
  </head>
  <body class="main-body">
    <section class="body-content">
      <c:if test="${!empty error}">
        <section class="auth-msg">
          <p class="error-message">
            <c:out value="${error}" />
          </p>
        </section>
      </c:if>

      <form
        action="${pageContext.request.contextPath}/login"
        method="post"
        class="gentle-form"
        id="transfer-form"
      >
        <section class="form-content">
          <h1 class="form-title">Login to your account</h1>
          <fieldset class="form-fieldset">
            <label class="form-row">
              <input
                type="text"
                name="username"
                id="username"
                class="form-row--input"
                placeholder=" "
                autocomplete="off"
              />
              <span class="form-row--label">
                <i class="row-label--icon fas fa-user-unlock"></i>
                <span class="row-label--text">Username</span>
              </span>
              <span class="form-row--btn btn-no-pointer-event">
                <i class="fas fa-user"></i>
              </span>
            </label>
            <label class="form-row">
              <input
                type="password"
                name="password"
                id="password"
                class="form-row--input"
                placeholder=" "
              />
              <span class="form-row--label">
                <i class="row-label--icon fas fa-key"></i>
                <span class="row-label--text">Password</span>
              </span>
              <button class="form-row--btn btn-clickable">
                <i class="fas fa-eye-slash"></i>
              </button>
            </label>
            <div class="form-btn-group">
              <button class="btn btn-wide btn-submit" type="submit">
                Signin
              </button>
            </div>
          </fieldset>
        </section>
      </form>
    </section>
  </body>
</html>
