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
    <div class="collapse navbar-collapse justify-content-end" id="navbarNavDropdown">
           <ul class="navbar-nav">
               <li class="nav-item">
                   <a class="nav-link active text-white me-4 fs-5" aria-current="page" href="AdminHome.jsp">Home</a>
               </li>
               <li class="nav-item">
                   <a class="nav-link active text-white me-4 fs-5" aria-current="page" href="admin/userDetails">View Users</a>
               </li>
               <li class="nav-item">
                   <a class="nav-link active text-white me-4 fs-5" aria-current="page" href="admin/viewUserComplaints">View Complaints</a>
               </li>
           </ul>
       </div>
</nav>
<h1>Welcome</h1>
</body>
</html>
