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
            background-image: url('path/to/your/background/image.jpg');
            background-size: cover;
            background-repeat: no-repeat;
        }
        .action-btn {
            font-size: 14px;
            color: gray;
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
                 <a class="nav-link active text-white me-4 fs-5" aria-current="page" href="admin/viewComplaints">View Complaints</a>
             </li>
        </ul>
    </div>
</nav>
<form action="userDetails" method="get">

</form>

 <div class="mt-4">
     <h3 class="text-center">Users</h3>
     <table class="table table-bordered">
         <thead class="table table-dark">
             <tr>
                 <th scope="col">ID</th>
                 <th scope="col">Name</th>
                 <th scope="col">Phone Number</th>
                 <th scope="col">Email</th>
                 <th scope="col">CreatedBy</th>
                 <th scope="col">CreatedAt</th>
                 <th scope="col">UpdatedBy</th>
                 <th scope="col">UpdatedAt</th>
             </tr>
         </thead>
         <tbody>
             <c:choose>
                 <c:when test="${empty dto}">
                     <tr>
                         <td colspan="8" class="text-center">No users found</td>
                     </tr>
                 </c:when>
                 <c:otherwise>
                     <c:forEach items="${dto}" var="users">
                         <tr>
                             <th scope="row">${users.id}</th>
                             <td>${users.firstName} ${users.lastName}</td>
                             <td>${users.phoneNo}</td>
                             <td>${users.email}</td>
                             <td>${users.createdBy}</td>
                             <td>${users.createdAt}</td>
                             <td>${users.updatedBy}</td>
                             <td>${users.updatedAt}</td>
                         </tr>
                     </c:forEach>
                 </c:otherwise>
             </c:choose>
         </tbody>
     </table>
 </div>
</body>
</html>