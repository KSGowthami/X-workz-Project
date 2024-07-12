let fieldsCheck = {
    "firstName": false,
    "lastName": false,
    "phoneNo": false,
    "email": false,
    "designation": false,
    "departmentId": false
};



function validateFirstName() {
    let name = document.getElementById('firstName');
    let errorMsg = document.getElementById('firstNameError');

    if (!/^[a-zA-Z\s]{3,30}$/.test(name.value)) {
        errorMsg.innerHTML = "First Name must contain only alphabets and name should have 3-30 characters.";
        fieldsCheck["firstName"] = false;
    } else {
        errorMsg.innerHTML = "";
        fieldsCheck["firstName"] = true;
    }
}

function validateLastName() {
    let name = document.getElementById('lastName');
    let errorMsg = document.getElementById('lastNameError');

    if (!/^[a-zA-Z\s]{3,30}$/.test(name.value)) {
        errorMsg.innerHTML = "Last Name must contain only alphabets and name should have 3-30 characters.";
        fieldsCheck["lastName"] = false;
    } else {
        errorMsg.innerHTML = "";
        fieldsCheck["lastName"] = true;
    }
    validateAndEnableSubmit();
}

function setDesignation() {
    let designation = document.getElementById("designation");
    let error = document.getElementById("designationError");
    let regex = /^[A-Za-z\s]+$/;

    if (designation.value.trim() === "" || designation.value.length < 3 || designation.value.length > 30 || !regex.test(designation.value)) {
        error.innerHTML = "Please enter a valid Designation (3-30 characters, only alphabets and spaces)";
        error.style.color = 'red';
        fieldsCheck["designation"] = false;
    } else {
        fieldsCheck["designation"] = true;
        error.innerHTML = "";
    }
}

function validateEmail() {
    let email = document.getElementById("email").value.trim();
    let error = document.getElementById("emailError");

    if (email === "") {
        error.innerHTML = "Please enter an email.";
        error.style.color = 'red';
        fieldsCheck["email"] = false;
        validateAndEnableSubmit();
        return;
    }

    let xhr = new XMLHttpRequest();
    xhr.open("GET", `http://localhost:8080/MyProject/departmentAdmin/emailValidation?email=${encodeURIComponent(email)}`, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                let responseText = xhr.responseText.trim();
                if (responseText !== "") {
                    error.innerHTML = responseText;
                    error.style.color = 'red';
                    fieldsCheck["email"] = false;
                } else {
                    error.innerHTML = "";
                    fieldsCheck["email"] = true;
                }

            } else {
                console.error('Error validating email:', xhr.statusText);
            }
        }
    };
    xhr.send();
}

function validatePhoneNumber() {
    let phoneNo = document.getElementById("phoneNo").value.trim();
    let error = document.getElementById("phoneNumberError");

    let phoneRegex = /^[0-9]{10}$/;
    if (!phoneRegex.test(phoneNo)) {
        error.innerHTML = "Please enter a valid 10-digit phone number.";
        error.style.color = 'red';
        fieldsCheck["phoneNo"] = false;
        return;
    }

    let xhr = new XMLHttpRequest();
    xhr.open("GET", `http://localhost:8080/MyProject/departmentAdmin/phoneValidation?phone=${encodeURIComponent(phoneNo)}`, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                let phoneExists = xhr.responseText.trim();
                if (phoneExists) {
                    error.innerHTML = "Phone number is already registered. Please use a different phone number.";
                    error.style.color = 'red';
                    fieldsCheck["phoneNo"] = false;
                } else {
                    error.innerHTML = "";
                    fieldsCheck["phoneNo"] = true;
                }
            } else {
                console.error('Error validating phone number:', xhr.statusText);
            }
        }
    };
    xhr.send();

}
