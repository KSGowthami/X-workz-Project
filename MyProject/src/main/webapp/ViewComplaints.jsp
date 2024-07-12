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
                <a class="nav-link text-white me-4 fs-5" aria-current="page" href="Home.jsp">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active text-white me-4 fs-5" aria-current="page" href="EditProfile.jsp">Edit Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white me-4 fs-5" aria-current="page" href="RaiseComplaints.jsp">Raise Complaints</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white me-4 fs-5" aria-current="page" href="${pageContext.request.contextPath}/viewComplaints">View Complaints</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white me-4 fs-5" aria-current="page" href="#">Welcome ${sessionScope.firstName} ${sessionScope.lastName}</a>
            </li>
            <li class="nav-item">
                <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" width="70" height="70" class="rounded-circle profile-image" alt="Profile Image" id="profileImage">
            </li>
        </ul>
    </div>
</nav>

<div class="table-container">
    <table class="table mt-5 ml-3">
        <thead class="thead-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Complaint Type</th>
                <th scope="col">Address</th>
                <th scope="col">Country</th>
                <th scope="col">State</th>
                <th scope="col">City</th>
                <th scope="col">Description</th>
                <th scope="col">Status</th>
                <th scope="col">Edit</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${empty complaints}">
                    <tr>
                        <td colspan="9" class="text-center">No complaints found</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${complaints}" var="complaint">
                        <tr>
                            <th scope="row">${complaint.id}</th>
                            <td>${complaint.type}</td>
                            <td>${complaint.address}</td>
                            <td>${complaint.country}</td>
                            <td>${complaint.state}</td>
                            <td>${complaint.city}</td>
                            <td>${complaint.description}</td>
                            <td>${complaint.status}</td>
                            <td>
                              <a href="editComplaints?complaintId=${complaint.id}" class="btn btn-primary">Edit</a>
                              </td>
                    </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</div>

</body>
</html>
