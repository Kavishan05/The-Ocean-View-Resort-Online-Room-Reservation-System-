<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Manage Staff</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
</head>
<body>
<div class="container">
    <div class="card">
        <h1>Manage Staff</h1>

        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>

        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Username</th>
                <th>Email</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="s" items="${staffList}">
                <tr>
                    <td>${s.id}</td>
                    <td><c:out value='${s.name}'/></td>
                    <td><c:out value='${s.username}'/></td>
                    <td><c:out value='${s.email}'/></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/staff/edit?id=${s.id}">Edit</a>
                        |
                        <form style="display:inline" method="post" action="${pageContext.request.contextPath}/staff/delete" onsubmit="return confirm('Delete this staff member?');">
                            <input type="hidden" name="id" value="${s.id}">
                            <button class="btn danger" type="submit" style="padding:6px 10px;">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="nav">
            <a class="btn primary" href="${pageContext.request.contextPath}/staff/add">Add Staff</a>
            <a class="btn" href="${pageContext.request.contextPath}/dashboard">Back</a>
        </div>
    </div>
</div>
</body>
</html>
