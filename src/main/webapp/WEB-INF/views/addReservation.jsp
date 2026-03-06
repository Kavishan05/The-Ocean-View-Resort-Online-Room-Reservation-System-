<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add Reservation</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
</head>
<body>
<div class="container">
    <div class="card">
        <h1>Add Reservation</h1>

        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/reservations/add">
            <label class="label">Guest Name</label>
            <input class="input" name="guestName" required maxlength="100" value="<c:out value='${reservation.guestName}'/>">

            <label class="label">Address</label>
            <input class="input" name="address" required maxlength="255" value="<c:out value='${reservation.address}'/>">

            <label class="label">Contact</label>
            <input class="input" name="contact" required maxlength="30" value="<c:out value='${reservation.contact}'/>">

            <label class="label">Room Type</label>
            <select class="input" name="roomType" required>
                <option value="">Select Room Type</option>
                <option value="Standard" ${'Standard' == reservation.roomType ? 'selected' : ''}>Standard</option>
                <option value="Deluxe" ${'Deluxe' == reservation.roomType ? 'selected' : ''}>Deluxe</option>
                <option value="Suite" ${'Suite' == reservation.roomType ? 'selected' : ''}>Suite</option>
                <option value="Penthouse" ${'Penthouse' == reservation.roomType ? 'selected' : ''}>Penthouse</option>
            </select>

            <div class="row">
                <div class="col">
                    <label class="label">Check-in Date</label>
                    <input class="input" type="date" name="checkinDate" required value="<c:out value='${reservation.checkinDate}'/>">
                </div>
                <div class="col">
                    <label class="label">Check-out Date</label>
                    <input class="input" type="date" name="checkoutDate" required value="<c:out value='${reservation.checkoutDate}'/>">
                </div>
            </div>

            <label class="label">Room Price (per night)</label>
            <input class="input" type="number" name="roomPrice" required min="1" step="0.01" value="<c:out value='${reservation.roomPrice}'/>">

            <div class="form-actions">
                <button class="btn primary" type="submit">Save</button>
                <a class="btn" href="${pageContext.request.contextPath}/dashboard">Back</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
