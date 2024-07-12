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
            <script src="/MyProject/javascript/Employee.js"></script>
    <style>
        body {
            background-color: rgb(240, 240, 240);
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
                <a class="nav-link text-white me-4 fs-5" aria-current="page" href="department/departmentViewComplaints">View Complaints</a>
            </li>
             <li class="nav-item ">
         <a class="nav-link active text-white me-4 fs-5" aria-current="page" href="/MyProject/department/addEmployees">Add Employees</a>
             </li>
        </ul>
    </div>
</nav>

<center><b><span style="color: green;font-weight: bold;font-size:25px">${message}</span></b></center>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="card popup-card shadow col-md-4">
            <h2 class="text-center mt-3">Add Employees</h2>
            <div class="card-body">
                <form action="department/addEmployees" method="post">
                    <div class="form-group mb-3">
                        <span style="color:red;text-align:center">
                            <c:forEach items="${errors}" var="error">
                                <span class="text-danger">${error.defaultMessage}</span><br>
                            </c:forEach>
                        </span>
                    </div>
                  <div class="mb-4">
                      <label for="departmentId" class="form-label text-dark">Department</label>
                      <select class="form-select" id="departmentId" name="departmentId">
                          <option value="0">Choose...</option>
                          <c:forEach items="${departments}" var="department">
                              <option value="${department.departmentId}">${department.departmentName}</option>
                          </c:forEach>
                              <span id="departmentIdError" class="text-danger"></span>
                      </select>

                  </div>
                    <div class="form-group mb-3">
                        <label for="firstName">First Name</label>
                        <input class="form-control" id="firstName" name="firstName" placeholder="Enter Employee first name" type="text" value="${employeesDTO.firstName}" onblur="validateFirstName()" >
                        <span class="text-danger" id="firstNameError"></span>
                    </div>
                    <div class="form-group mb-3">
                        <label for="lastName">Last Name</label>
                        <input class="form-control" id="lastName" name="lastName" placeholder="Enter Employee last name" type="text" value="${employeesDTO.lastName}" onblur="validateLastName()">
                        <span class="text-danger" id="lastNameError"></span>
                    </div>
                    <div class="form-group mb-3">
                        <label for="designation">Designation</label>
                        <input class="form-control" id="designation" name="designation" placeholder="Enter Employee Designation" type="text" value="${employeesDTO.designation}"  onblur="setDesignation()">
                        <span class="text-danger" id="designationError"></span>
                    </div>
                    <div class="form-group mb-3">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Enter Employees email Id" value="${employeesDTO.email}" onblur=" validateEmail()">
                        <span class="text-danger" id="emailError"></span>
                    </div>
                    <div class="form-group mb-3">
                        <label for="phoneNo">Phone Number</label>
                        <input type="text" class="form-control" id="phoneNo" name="phoneNo" placeholder="Enter phone Number" value="${employeesDTO.phoneNo}" onblur="validatePhoneNumber()">
                        <span class="text-danger" id="phoneNumberError"></span>
                    </div>
                    <div class="form-group mb-3 mt-3">
                        <button class="btn btn-primary" id="submitBtn" type="submit">Add Employee</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>

</html>
