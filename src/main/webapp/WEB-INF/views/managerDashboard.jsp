<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Manager Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
</head>
<body>
<div class="container">
    <div class="card">
        <h1>Manager Dashboard</h1>
        <small>Welcome, ${displayName} (${displayUsername})</small>

        <div class="nav">
            <a class="btn primary" href="${pageContext.request.contextPath}/reservations/add">Add Reservation</a>
            <a class="btn" href="${pageContext.request.contextPath}/reservations">View All Reservations</a>
            <a class="btn" href="${pageContext.request.contextPath}/staff/add">Add Staff</a>
            <a class="btn" href="${pageContext.request.contextPath}/staff/manage">Manage Staff</a>
            <a class="btn" href="${pageContext.request.contextPath}/help">Help</a>
            <a class="btn danger" href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>

        <div class="footer">Ocean View Resort</div>
    </div>
</div>
</body>
</html>
