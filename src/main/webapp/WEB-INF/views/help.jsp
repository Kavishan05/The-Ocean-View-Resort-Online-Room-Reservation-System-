<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Help</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
</head>
<body>
<div class="container">
    <div class="card">
        <h1>Help</h1>
        <div class="row">
            <div class="col">
                <h2>Login</h2>
                <small>Use your username and password provided by the manager.</small>
            </div>
            <div class="col">
                <h2>Reservations</h2>
                <small>Add, view all, search by reservation number and generate bill.</small>
            </div>
        </div>

        <div class="nav">
            <a class="btn" href="${pageContext.request.contextPath}/dashboard">Back</a>
        </div>
    </div>
</div>
</body>
</html>
