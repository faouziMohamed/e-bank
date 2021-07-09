<%-- Created by IntelliJ IDEA. User: faouzi Date: 7/7/21 Time: 5:08 PM --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Authentication page</title>
    <link
            rel="stylesheet"
            type="text/css"
            href="${pageContext.request.contextPath}/styles/login.css"
    />
</head>
<body>
<section class="body-content">
    <h1 class="title">Welcome to your online Bank</h1>
    <form
        action="${pageContext.request.contextPath}/login"
        method="POST"
        class="auth-form">
        <section class="form-content">
            <div class="row-content identifiant">
                <label for="name" class="label-auth">
                    Username :
                </label>
                <input
                        type="text"
                        id="name"
                        name="name"
                        class="input-field pass-auth"
                />
            </div>
            <div class="row-content password">
                <label for="pass" class="label-auth">
                    Password :
                </label>
                <input
                        type="password"
                        id="pass"
                        name="pass"
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
