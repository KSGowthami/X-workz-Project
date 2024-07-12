<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>X-Workz Project</title>
    <base href="http://localhost:8080/MyProject/">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <script src="/MyProject/javascript/SignUp.js"></script>
    <style>
        body {
            background-color: rgb(240, 240, 240); /* Light Gray */
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-dark">
    <a class="navbar-brand" href="#">
        <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" width="120" height="60" alt="Xworkz">
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end " id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item ">
                <a class="nav-link active text-white me-4 fs-5" aria-current="page" href="index.jsp">Home</a>
            </li>
            <li class="nav-item ">
                <a class="nav-link active text-white me-4 fs-5" aria-current="page" href="SignIn.jsp">Sign In</a>
            </li>
        </ul>
    </div>
</nav>
<center><b><span style="color: green; font-weight: bold; font-size: 25px">${message}</span></b></center>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="card popup-card shadow col-md-4">
            <h2 class="text-center mt-3">Reset Password</h2>
            <div class="card-body">
                <form action="resetPassword" method="post">
                    <div class="form-group mb-3">
                        <span style="color:red;text-align:center">
                            <c:choose>
                                <c:when test="${not empty errors and errors.errorCount > 0}">
                                    <c:forEach var="error" items="${errors.allErrors}">
                                        <span class="text-danger">${error.defaultMessage}</span><br>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${not empty customError}">
                                        <span class="text-danger">${customError}</span><br>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </div>

                    <div class="form-group mb-3">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email"
                               placeholder="Enter your email Id" value="${dto.email}" readonly>
                        <span class="text-danger" id="emailError"></span>
                    </div>

                    <div class="form-group mb-3">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="Enter your password">
                        <span class="text-danger" id="passwordError"></span>
                    </div>

                    <div class="form-group mb-3">
                        <label for="newPassword">New Password</label>
                        <input type="password" class="form-control" id="newPassword" name="newPassword"
                               placeholder="Enter your new password" value="${dto.newPassword}">
                        <span class="text-danger" id="newPasswordError"></span>
                    </div>

                    <div class="form-group mb-3">
                        <label for="confirmPassword">Confirm Password</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword"
                               placeholder="Confirm your new password" value="${dto.confirmPassword}">
                        <span class="text-danger" id="confirmPasswordError"></span>
                    </div>

                    <div class="form-group mb-3">
                        <button class="btn btn-primary" id="submitBtn" type="submit">Reset</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
