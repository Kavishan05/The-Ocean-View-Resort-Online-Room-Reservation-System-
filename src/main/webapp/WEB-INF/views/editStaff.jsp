<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Edit Staff</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
</head>
<body>
<div class="container">
    <div class="card">
        <h1>Edit Staff</h1>

        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/staff/edit">
            <input type="hidden" name="id" value="${staff.id}">

            <label class="label">Name</label>
            <input class="input" type="text" name="name" required maxlength="100" value="<c:out value='${staff.name}'/>">

            <label class="label">Username</label>
            <input class="input" type="text" name="username" required maxlength="50" value="<c:out value='${staff.username}'/>">

            <label class="label">Email</label>
            <input class="input" type="email" name="email" required maxlength="100" value="<c:out value='${staff.email}'/>">

            <div class="form-actions">
                <button class="btn primary" type="submit">Save</button>
                <a class="btn" href="${pageContext.request.contextPath}/staff/manage">Cancel</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
