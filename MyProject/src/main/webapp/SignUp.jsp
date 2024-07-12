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
         <script src="/MyProject/javascript/SignUp.js"></script>
   <style>
  body {
             background-color:rgb(240, 240, 240); /* Light Gray */
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
                               <a class="nav-link active text-white me--4 fs-5" aria-current="page" href="SignIn.jsp">SignIn</a>
                           </li>

                       </ul>
    </div>
</nav>

<center><b><span style="color: green;font-weight: bold;font-size:25px">${message}</span></b></center>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="card popup-card shadow col-md-4">
         <h2 class="text-center mt-3">Sign Up</h2>
            <div class="card-body">
               <form action="sign" method="post">
                    <div class="form-group mb-3">
                        <span style="color:red;text-align:center">
                            <c:forEach items="${errors}" var="objectError">
                                ${objectError.defaultMessage}<br>
                            </c:forEach>
                        </span>
                    </div>
                    <div class="form-group mb-3">
                        <label for="name"> First Name</label>
                        <input class="form-control" id="firstName" name="firstName" placeholder="Enter Your name" type="text"
                               value="${dto.firstName}" onblur="validateFirstName()">
                        <span class="text-danger" id="fastNameError"></span>
                    </div>
                     <div class="form-group mb-3">
                         <label for="name">Last Name</label>
                         <input class="form-control" id="lastName" name="lastName" placeholder="Enter Your name" type="text"
                                value="${dto.LastName}" onblur="validateLastName()">
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
                    <div class="form-check mb-3">
                        <input class="form-check-input" type="checkbox" ${dto.check} id="defaultCheck1" name="check" onblur="validateCheckbox()">
                        <label class="form-check-label" for="defaultCheck1">
                            Provided details are true
                        </label>
                        <span class="text-danger" id="checkboxError"></span>
                    </div>

                    <div class="form-group mb-3">
                        <button class="btn btn-primary" id="submitBtn" type="submit" disabled>Submit</button>
                    </div>
                    </form>
             </div>
         </div>
     </div>
 </div>
 </body>
 </html>