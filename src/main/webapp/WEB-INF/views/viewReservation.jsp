<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Reservations</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
</head>
<body>
<div class="container">
    <div class="card">
        <h1>Reservations</h1>

        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>

        <form method="get" action="${pageContext.request.contextPath}/reservations/search" class="row">
            <div class="col">
                <label class="label">Search by Reservation Number</label>
                <input class="input" type="number" name="resNumber" min="1" placeholder="e.g. 1000">
            </div>
            <div class="col" style="align-self:end;">
                <div class="form-actions">
                    <button class="btn primary" type="submit">Search</button>
                    <a class="btn" href="${pageContext.request.contextPath}/reservations">Clear</a>
                </div>
            </div>
        </form>

        <c:if test="${not empty reservation}">
            <div class="alert ok">
                Found reservation <b>#${reservation.reservationNumber}</b> for <b><c:out value='${reservation.guestName}'/></b>
                - <a href="${pageContext.request.contextPath}/reservations/bill?resNumber=${reservation.reservationNumber}">Generate Bill</a>
            </div>
        </c:if>

        <table class="table" style="margin-top:14px;">
            <thead>
            <tr>
                <th>Res #</th>
                <th>Guest</th>
                <th>Room</th>
                <th>Check-in</th>
                <th>Check-out</th>
                <th>Price</th>
                <th>Bill</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="r" items="${reservations}">
                <tr>
                    <td>${r.reservationNumber}</td>
                    <td><c:out value='${r.guestName}'/></td>
                    <td><c:out value='${r.roomType}'/></td>
                    <td>${r.checkinDate}</td>
                    <td>${r.checkoutDate}</td>
                    <td>${r.roomPrice}</td>
                    <td><a href="${pageContext.request.contextPath}/reservations/bill?resNumber=${r.reservationNumber}">Bill</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="nav">
            <a class="btn primary" href="${pageContext.request.contextPath}/reservations/add">Add Reservation</a>
            <a class="btn" href="${pageContext.request.contextPath}/dashboard">Back</a>
        </div>
    </div>
</div>
</body>
</html>
