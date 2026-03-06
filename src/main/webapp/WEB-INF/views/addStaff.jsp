<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add Staff</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
</head>
<body>
<div class="container">
    <div class="card">
        <h1>Add Staff (Receptionist)</h1>

        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/staff/add">
            <label class="label">Name</label>
            <input class="input" type="text" name="name" required maxlength="100">

            <label class="label">Username</label>
            <input class="input" type="text" name="username" required maxlength="50">

            <label class="label">Password</label>
            <input class="input" type="password" name="password" required minlength="4" maxlength="80">

            <label class="label">Email</label>
            <input class="input" type="email" name="email" required maxlength="100">

            <div class="form-actions">
                <button class="btn primary" type="submit">Create Staff</button>
                <a class="btn" href="${pageContext.request.contextPath}/dashboard">Back</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
