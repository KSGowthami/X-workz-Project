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
     <style>
        body {
            background-color: rgb(240, 240, 240);
        }
        .captcha-container {
            position: relative;
            background-color: #f8f9fa; /* Background color for the CAPTCHA container */
            padding: 5px 10px; /* Adjust padding as needed */
            border-radius: 5px; /* Rounded corners */
        }
        .captcha-text {
            font-size: 20px; /* Adjust font size as needed */
            font-weight: bold; /* Make the CAPTCHA text bold */
            color: #333; /* Text color */
            font-family: Arial, sans-serif; /* Font family */
           margin-right: 70px;
           text-align: center;
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
                <a class="nav-link active  text-white me-4 fs-5" aria-current="page" href="index.jsp">Home</a>
            </li>
            <li class="nav-item ">
                <a class="nav-link active text-white me-4 fs-5" aria-current="page" href="SignIn.jsp">Sign In</a>
            </li>
        </ul>
    </div>
</nav>
<center>
    <b>
        <span style="color: green;font-weight: bold;font-size:25px">${message}</span>
    </b>
</center>
<div class="container mt-5">
                <div class="row justify-content-center">
                    <div class="card popup-card shadow col-md-4">
                        <h2 class="text-center mt-3">
                <c:choose>
                    <c:when test="${admin}">
                        <p>Admin Sign In</p>
                    </c:when>
                    <c:when test="${department}">
                        <p>Department Sign In</p>
                    </c:when>
                    <c:otherwise>
                        <p>User Sign In</p>
                    </c:otherwise>
                </c:choose>
            </h2>
            <form action="${admin ? 'admin/login' : department ? 'department/login' : 'login'}" method="post">
                <div class="card-body">
                    <div class="form-group mb-3">
                        <span style="color:red;text-align:center">
                            ${errors}<br>
                            <c:if test="${not empty attempts}">
                                Your login attempt: ${attempts}
                            </c:if>
                        </span>
                    </div>
                    <div class="form-group mb-3">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email"
                         value="${dto.email}">
                        <span class="text-danger" id="emailError"></span>
                    </div>
                    <div class="form-group mb-3">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password">
                        <span class="text-danger" id="passwordError"></span>
                    </div>
                    <div class="form-group mb-3">
                        <label>Enter Captcha</label>
                        <div class="captcha-container">
                            <input type="text" class="form-control captcha-input" id="captcha" name="captcha" placeholder="Enter Captcha">
                            <span class="captcha-text text-center" id="captchaText"></span>
                        </div>
                        <span class="text-danger" id="captchaError"></span>
                    </div>
                    <input type="hidden" id="correctCaptcha" name="correctCaptcha">
                    <input type="hidden" name="admin" value="${admin}">
                    <input type="hidden" name="department" value="${department}">
                    <div class="form-group mb-3">
                        <button class="btn btn-primary" id="submitBtn" type="submit">Sign In</button>
                        <c:if test="${not admin && not department}">
                            <a href="ForgotPassword.jsp">Forgot Password</a>
                        </c:if>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="/MyProject/javascript/Captcha.js"></script>
</body>
</html>
