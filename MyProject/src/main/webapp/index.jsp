<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>X-Workz Project</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
             <script src="/WebForms/javascript/furniture.js"></script>
             <style>
               body {
                          background-color:rgb(240, 240, 240);
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
    <div class="collapse navbar-collapse justify-content-end" id="navbarNavDropdown">
        <ul class="navbar-nav">
        <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle  text-white fs-5 me-4" href="#" id="clothsDropdown" role="button"
                     data-bs-toggle="dropdown" aria-expanded="false">
                        User
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="userDropDown">
                        <li><a class="dropdown-item" href="SignUp.jsp">User SignUp</a></li>
                        <li><a class="dropdown-item" href="SignIn.jsp">User SignIn</a></li>
                    </ul>
                </li>
                   <li class="nav-item">
                  <a class="nav-link active me-4 fs-5 text-white" aria-current="page" href="admin/login">Admin</a>
                  </li>
                     <li class="nav-item">
                   <a class="nav-link active me-4 fs-5 text-white" aria-current="page" href="department/login">Department Admin</a>
                   </li>
                     <li class="nav-item">
                       <a class="nav-link active me-4 fs-5 text-white" aria-current="page" href="EmployeeLogin.jsp">Employee Login</a>
                       </li>
            </ul>
    </div>
</nav>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="card popup-card shadow col-md-5">
            <div class="card-body">
                <h5>Tech Stack:</h5>
                <p>JSP, BootStrap, CSS, JavaScript, Java, Spring, Spring JPA/Hibernate</p>
                <h5>Start Date :</h5>
                <p> 6-11-2024<p>
                <h5>VCS<h5>
                <a href="https://github.com/KSGowthami/Project">Github</a><br><br>
                <h5>Description:</h5>
                <p>Currently Working on SignUp page, and saving data to database.</p>
            </div>
       </div>
   </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" crossorigin="anonymous"></script>
</body>
</html>