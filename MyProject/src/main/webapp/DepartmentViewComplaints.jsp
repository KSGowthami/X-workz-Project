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
                <a class="nav-link active  text-white me-4 fs-5" aria-current="page" href="DepartmentHome.jsp">Home</a>
            </li>
            <li class="nav-item ">
                <a class="nav-link active text-white me-4 fs-5" aria-current="page" href="DepartmentViewComplaints.jsp">View Complaints</a>
            </li>
             <li class="nav-item ">
                 <a class="nav-link active text-white me-4 fs-5" aria-current="page" href="AddEmployees.jsp">Add Employees</a>
             </li>
        </ul>
    </div>
</nav>

<div class="container mt-5">
    <c:if test="${not empty message}">
        <center><b><span style="color: green;font-weight: bold;font-size:20px">${message}</span></b></center>
    </c:if>

    <!-- Display Error Message -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger text-center">${error}</div>
    </c:if>

    <form action="department/departmentViewComplaints" method="get">
        <div class="row justify-content-center">
            <div class="card popup-card shadow col-md-4">
                <div class="card-body">
                    <div class="mb-3">
                        <label for="type" class="form-label text-dark">Complaint Type</label>
                        <select class="form-select custom-select-height w-100" id="type" name="type" onblur="setGroup()">
                            <option value="0" ${empty type ? 'selected' : ''}>Choose...</option>
                            <option value="Drainage Problem" ${type eq 'Drainage Problem' ? 'selected' : ''}>Drainage Problem</option>
                            <option value="Electric Problem" ${type eq 'Electric Problem' ? 'selected' : ''}>Electric Problem</option>
                            <option value="Plumber Problem" ${type eq 'Plumber Problem' ? 'selected' : ''}>Plumber Problem</option>
                            <option value="Wastage Problem" ${type eq 'Wastage Problem' ? 'selected' : ''}>Wastage Problem</option>
                            <option value="Water Problem" ${type eq 'Water Problem' ? 'selected' : ''}>Water Problem</option>
                        </select>
                    </div>
                    <div class="form-group mb-3">
                        <button class="btn btn-primary" id="submitBtn" type="submit">Search</button>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <c:if test="${not empty complaints}">
        <div class="mt-4">
            <h3 class="text-center">Complaints</h3>
            <table class="table table-bordered">
                <thead class="table table-dark">
                    <tr>
                        <th class="text-center">ID</th>
                        <th class="text-center">Type</th>
                        <th class="text-center">Area</th>
                        <th class="text-center">Address</th>
                        <th class="text-center">Description</th>
                        <th class="text-center">Status</th>
                        <th class="text-center">Assign Employee</th>
                        <th class="text-center">Action</th>
                        <th class="text-center">Update</th>
                    </tr>
                </thead>
                <tbody>
            <c:forEach var="complaint" items="${complaints}">
                <tr>
                    <td class="text-center">${complaint.id}</td>
                    <td class="text-center">${complaint.type}</td>
                    <td class="text-center">${complaint.area}</td>
                    <td class="text-center">${complaint.address}</td>
                    <td class="text-center">${complaint.description}</td>
                    <td class="text-center">${complaint.status}</td>
                    <td class="text-center">
                        <form action="department/assignEmployeeToDepartment" method="post">
                            <select name="employeeId" id="employeeId" class="form-select">
                                <c:choose>
                                    <c:when test="${not empty employees}">
                                        <option value="">Assign Employee</option>
                                        <c:forEach var="employee" items="${employees}">
                                            <option value="${employee.employeeId}" ${complaint.employeeId == employee.employeeId ? 'selected' : ''}>
                                                ${employee.firstName} ${employee.lastName}
                                            </option>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="">No employee for this department</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                            <input type="hidden" name="complaintId" value="${complaint.id}" />
                    </td>
                    <td class="text-center">
                        <select class="form-select custom-select-width table-active" name="status" id="status" required>
                            <option value="">Action</option>
                            <option value="Pending" ${complaint.status eq 'Pending' ? 'selected' : ''}>Pending</option>
                            <option value="Resolved" ${complaint.status eq 'Resolved' ? 'selected' : ''}>Resolved</option>
                            <option value="In Process" ${complaint.status eq 'In Process' ? 'selected' : ''}>In Process</option>
                        </select>
                    </td>
                    <td>
                        <button class="btn btn-primary mt-2" type="submit">Update</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

    <c:if test="${empty complaints}">
        <div class="mt-4 text-center">
            <h3 class="alert alert-danger">No complaints found</h3>
        </div>
    </c:if>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
