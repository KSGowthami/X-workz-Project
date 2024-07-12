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
            background-color: rgb(240, 240, 240)
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
       <li class="nav-item">
                      <a class="nav-link text-white me-4 fs-5" aria-current="page" href="#">Welcome ${sessionScope.firstName} ${sessionScope.lastName}</a>
                  </li>
            <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle  text-white fs-5 me-4" href="#" id="clothsDropdown" role="button"
                         data-bs-toggle="dropdown" aria-expanded="false">Menu
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="userDropDown">
                            <li><a class="dropdown-item" href="Home.jsp">Home</a></li>
                            <li><a class="dropdown-item" href="EditProfile.jsp">Edit Profile</a></li>
                            <li><a class="dropdown-item" href="RaiseComplaints.jsp">Raise Complaints</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/viewComplaints">View Complaints</a></li>
                            <li><a class="dropdown-item" href="/logout">Logout</a></li>
                        </ul>
               <li class="nav-item">
                    <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" width="70" height="70" class="rounded-circle profile-image"  id="profileImage" onerror="this.onerror=null:this.src='https://www.shutterstock.com/image-vector/vector-flat-illustration-grayscale-avatar-600nw-2264922221.jpg'">
                </li>
    </div>
</nav>
<center><b><span style="color: green;font-weight: bold;font-size:25px">${message}</span></b></center>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" crossorigin="anonymous"></script>
</body>
</html>
