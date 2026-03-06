<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Ocean View Resort - Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
</head>
<body>
<div class="container">
    <div class="card" style="max-width:520px;margin:40px auto;">
        <h1>Ocean View Resort</h1>
        <small>Online Room Reservation System</small>

        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/login" autocomplete="off">
            <label class="label">Username</label>
            <input class="input" type="text" name="username" required minlength="3" maxlength="50">

            <label class="label">Password</label>
            <input class="input" type="password" name="password" required minlength="3" maxlength="80">

            <label class="label">Login as</label>
            <select class="input" name="role" required>
                <option value="MANAGER">Admin (Hotel Manager)</option>
                <option value="RECEPTIONIST">Staff (Receptionist)</option>
            </select>

            <div class="form-actions">
                <button class="btn primary" type="submit">Login</button>
                <a class="btn" href="${pageContext.request.contextPath}/help">Help</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
