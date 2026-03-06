<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bill</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
</head>
<body>
<div class="container">
    <div class="card">
        <h1>Bill</h1>

        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>

        <c:if test="${not empty reservation}">
            <table class="table">
                <tr><th>Reservation #</th><td>${reservation.reservationNumber}</td></tr>
                <tr><th>Guest</th><td><c:out value='${reservation.guestName}'/></td></tr>
                <tr><th>Room Type</th><td><c:out value='${reservation.roomType}'/></td></tr>
                <tr><th>Check-in</th><td>${reservation.checkinDate}</td></tr>
                <tr><th>Check-out</th><td>${reservation.checkoutDate}</td></tr>
                <tr><th>Nights</th><td>${nights}</td></tr>
                <tr><th>Price / Night</th><td>${reservation.roomPrice}</td></tr>
                <tr><th>Total</th><td><b>${total}</b></td></tr>
            </table>
        </c:if>

        <div class="nav">
            <button class="btn primary" onclick="window.print()">Print Bill</button>
            <a class="btn" href="${pageContext.request.contextPath}/reservations">Back to Reservations</a>
            <a class="btn" href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
        </div>
    </div>
</div>
</body>
</html>
