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
                <a class="nav-link active text-white me-4 fs-5" aria-current="page" href="EditProfile.jsp">Edit Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white me-4 fs-5" aria-current="page" href="Home.jsp">Home</a>
            </li>
          <li class="nav-item">
                 <a class="nav-link text-white me-4 fs-5" aria-current="page" href="RaiseComplaints.jsp">Raise Complaints</a>
          </li>
            <li class="nav-item">
                <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" width="70" height="70" class="rounded-circle profile-image" alt="Profile Image" id="profileImage">
            </li>
        </ul>
    </div>
</nav>
<center><b><span style="color: green;font-weight: bold;font-size:25px">${message}</span></b></center>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="card popup-card shadow col-md-4">
            <h2 class="text-center mt-3">Edit Profile</h2>
            <div class="card-body">
                <form action="upload" method="post" enctype="multipart/form-data">
                    <div class="form-group mb-3">
                        <span style="color:red;text-align:center">
                            <c:forEach items="${errors}" var="error">
                                <span class="text-danger">${error.defaultMessage}</span><br>
                            </c:forEach>
                        </span>
                    </div>
                    <div class="form-group mb-3">
                        <label for="firstName">First Name</label>
                        <input class="form-control" id="firstName" name="firstName" placeholder="Enter Your name" type="text"
                               value="${dto.firstName}" onblur="validateFirstName()">
                        <span class="text-danger" id="firstNameError"></span>
                    </div>
                    <div class="form-group mb-3">
                        <label for="lastName">Last Name</label>
                        <input class="form-control" id="lastName" name="lastName" placeholder="Enter Your name" type="text"
                               value="${dto.lastName}" onblur="validateLastName()">
                        <span class="text-danger" id="lastNameError"></span>
                    </div>
                    <div class="form-group mb-3">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email"
                               placeholder="Enter your email Id" value="${dto.email}" onblur="validateEmail()">
                        <span class="text-danger" id="emailError"></span>
                    </div>
                     <div class="form-group mb-3">
                          <label for="phoneNo">Phone Number</label>
                               <input type="text" class="form-control" id="phoneNo" name="phoneNo"
                                        placeholder="Enter phone Number" value="${dto.phoneNo}" onblur="validatePhoneNumber()">
                                     <span class="text-danger" id="phoneNumberError"></span>
                                </div>
                    <div class="form-group mb-3">
                        <label for="file">Upload Profile Image</label>
                        <input type="file" class="form-control" id="file" name="file">
                    </div>
                    <div class="form-group mb-3">
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-ND83p6+2LC9sNGvzFgiptEh0Wt3veCHpdwwvWY3Aj23FR5f4ob0C5sHbPkzJf6Hm" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-5mrLOimZlMFbbXUpiH8eAFKmKXbLqaW8GDoAWF+Q6h4Ec8Q2pSyyKhcvwwa3fznK" crossorigin="anonymous"></script>
</body>
</html>
