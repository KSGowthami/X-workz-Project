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
            background-color: (240,240,240)
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
                <a class="nav-link active text-white me-4 fs-5" aria-current="page" href="EmployeeLogin.jsp">Employee Login</a>
            </li>
        </ul>
    </div>
</nav>
<center><b><span style="color: green;font-weight: bold;font-size:25px">${message}</span></b></center>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="card popup-card shadow col-md-4">
            <h2 class="text-center mt-3">Employee SignIn</h2>
            <div class="card-body">
             <form action="employee/getOtp" method="post">
                 <div class="form-group mb-3">
                     <label for="email">Email</label>
                     <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email Id" required value="${employee.email}">
                     <span class="text-danger">${emailError}</span>
                 </div>
                 <div class="form-group mb-3 text-center">
                     <button class="btn btn-primary" id="getOtpBtn" type="submit">Get OTP</button>
                 </div>
             </form>

             <form action="employee/loginWithOtp" method="post">
                <input type="hidden" name="email" value="${employee.email}">
                  <div class="form-group mb-3">
                     <label for="otp">OTP</label>
                     <input type="text" class="form-control" id="otp" name="otp" placeholder="Enter your OTP" required>
                     <span class="text-danger">${otpError}</span>
                 </div>
                 <div class="form-group mb-3 text-center">
                     <button class="btn btn-primary" id="loginBtn" type="submit">Login</button>
                 </div>
             </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoD5TxZ7r5qBIeEjO9iZTfipwgHN4Da9GkVB86Y5YlY3k5f"
        crossorigin="anonymous"></script>
</body>
</html>
