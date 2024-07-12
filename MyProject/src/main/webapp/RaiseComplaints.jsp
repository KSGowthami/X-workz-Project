<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script src="/MyProject/javascript/ComplaintsRaise.js"></script>
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
                    <img src="${pageContext.request.contextPath}${sessionScope.profileImage}" width="70" height="70" class="rounded-circle profile-image" alt="Profile Image" id="profileImage">
                </li>
            </ul>
        </div>
    </nav>

    <center><b><span style="color: green;font-weight: bold;font-size:25px">${complaintMsg}</span></b></center>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="card popup-card shadow col-md-4">
                <h2 class="text-center mt-3">Raise Complaint</h2>
                <div class="card-body">
                <form action="${action == 'edit' ? 'editComplaints' : 'raiseComplaint'}" method="post">
                <input type="hidden" name="id" value="${dto.id}"/>
                  <div class="form-group mb-3">
           <select class="form-select" id="type" name="type" ${action == 'edit' ? 'disabled' : ''} onblur="setGroup()">
            <option value="0" ${dto.type == null ? 'selected' : ''}>Choose...</option>
            <option value="Drainage Problem" ${dto.type eq 'Drainage Problem' ? 'selected' : ''}>Drainage Problem</option>
            <option value="Electric Problem" ${dto.type eq 'Electric Problem' ? 'selected' : ''}>Electric Problem</option>
            <option value="Plumber Problem" ${dto.type eq 'Plumber Problem' ? 'selected' : ''}>Plumber Problem</option>
            <option value="Wastage Problem" ${dto.type eq 'Wastage Problem' ? 'selected' : ''}>Wastage Problem</option>
            <option value="Drainage Problem" ${dto.type eq 'Drainage Problem' ? 'selected' : ''}>Drainage Problem</option>
            <option value="Water Problem" ${dto.type eq 'Water Problem' ? 'selected' : ''}>Water Problem</option>
        </select>
        <span id="groupError" class="text-danger"></span>
    </div>

    <div class="mb-3">
        <label for="area" class="form-label text-dark">Area</label>
        <input type="text" class="form-control" id="area" name="area" ${action == 'edit' ? 'disabled' : ''} value="${dto.area}" />
        <span id="areaError" class="text-danger"></span>
    </div>

    <div class="mb-3">
        <label for="address" class="form-label text-dark">Address</label>
        <input type="text" class="form-control" id="address" name="address" ${action == 'edit' ? 'disabled' : ''} value="${dto.address}" />
        <span id="addressError" class="text-danger"></span>
    </div>
       <div class="mb-3">
                        <label for="country" class="form-label text-dark">Country</label>
                        <select class="form-select" id="country" name="country" ${action == 'edit' ? 'disabled' : ''} onblur="fetchStates(); validateCountry();">
                         <c:choose>
                            <c:when test= "${action != 'edit'}">
                                <option value="0">Choose...</option>
                             </c:when>
                             <c:otherwise>
                                <option value="0">${dto.country}</option>
                                 </c:otherwise>
                          </c:choose>
                        </select>
                        <span id="countryError" class="text-danger"></span>
                    </div>

                    <!-- State Dropdown -->
                    <div class="mb-3">
                        <label for="state" class="form-label text-dark">State</label>
                        <select class="form-select" id="state" name="state" ${action == 'edit' ? 'disabled' : ''} onblur="fetchCities(); validateState();">
                         <c:choose>
                            <c:when test= "${action != 'edit'}">
                                <option value="0">Choose...</option>
                             </c:when>
                             <c:otherwise>
                                <option value="0">${dto.state}</option>
                                 </c:otherwise>
                          </c:choose>
                        </select>
                        <span id="stateError" class="text-danger"></span>
                    </div>

                    <!-- City Dropdown -->
                    <div class="mb-3">
                        <label for="city" class="form-label text-dark">City</label>
                        <select class="form-select" id="city" name="city" ${action == 'edit' ? 'disabled' : ''} onblur="validateCity()" >
                         <c:choose>
                            <c:when test= "${action != 'edit'}">
                                <option value="0">Choose...</option>
                             </c:when>
                             <c:otherwise>
                                <option value="0">${dto.city}</option>
                                 </c:otherwise>
                          </c:choose>
                        </select>
                        <span id="cityError" class="text-danger"></span>
                    </div>
    <div class="mb-3">
        <label for="description" class="form-label text-dark">Description</label>
        <textarea class="form-control" id="description" name="description" rows="4">${dto.description}</textarea>
        <span id="descriptionError" class="text-danger"></span>
    </div>

    <div class="d-grid gap-2">
        <c:if test="${action == 'edit'}">
            <button type="submit" class="btn btn-primary">Update</button>
        </c:if>
        <c:if test="${action != 'edit'}">
            <button type="submit" class="btn btn-primary">Submit</button>
        </c:if>
    </div>
</form>

                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
            integrity="sha384-oBqDVmMz4fnFO9gybM6Wx8/mXB4j7yDMmclcO9OMpDwpJo2gtFf5Ofp9i1jVgg5/"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
            integrity="sha384-QOKDzwC1nZ1zjJcf24zUehE0OZel7tyFf8tke26K54QSNMbF+7iIekxZoT4anmQC"
            crossorigin="anonymous"></script>
</body>
</html>
