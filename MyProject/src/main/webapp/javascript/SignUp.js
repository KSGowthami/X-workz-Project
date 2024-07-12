let fieldsCheck = {
    "firstName": false,
    "lastName": false,
    "phoneNo": false,
    "email": false,
    "check": false
};

function validateAndEnableSubmit() {
    let flag = true;

    for (let value of Object.values(fieldsCheck)) {
        if (!value) {
            flag = false;
            break;
        }
    }

    if (flag) {
        document.getElementById("submitBtn").removeAttribute("disabled");
    } else {
        document.getElementById("submitBtn").setAttribute("disabled", "");
    }
}

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
    validateAndEnableSubmit();
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

function validateEmail() {
    let email = document.getElementById("email").value.trim(); // Corrected variable name
    let errorMsg = document.getElementById("emailError");

    let xhr = new XMLHttpRequest();
    xhr.open("GET", `http://localhost:8080/MyProject/validateEmail?email=${encodeURIComponent(email)}`, true);

    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                let responseText = xhr.responseText.trim();
                if (responseText !== "") {
                    errorMsg.innerHTML = responseText;
                    errorMsg.style.color = 'red';
                    fieldsCheck["email"] = false;
                } else {
                    errorMsg.innerHTML = "";
                    fieldsCheck["email"] = true;
                }
                validateAndEnableSubmit();
            } else {
                console.error('Error validating email: ', xhr.statusText);
            }
        }
    };
    xhr.send();
}

function validatePhoneNumber() {
    let phoneNo = document.getElementById("phoneNo").value.trim(); // Corrected variable name
    let errorMsg = document.getElementById("phoneNumberError");

    let xhr = new XMLHttpRequest();
    xhr.open("GET", `http://localhost:8080/MyProject/validatePhone?phoneNo=${encodeURIComponent(phoneNo)}`, true);

    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                let responseText = xhr.responseText.trim();
                if (responseText !== "") {
                    errorMsg.innerHTML = responseText;
                    errorMsg.style.color = 'red';
                    fieldsCheck["phoneNo"] = false;
                } else {
                    errorMsg.innerHTML = "";
                    fieldsCheck["phoneNo"] = true;
                }
                validateAndEnableSubmit();
            } else {
                console.error('Error validating phone: ', xhr.statusText);
            }
        }
    };
    xhr.send();
}

function validateCheckbox() {
    let checkbox = document.getElementById("defaultCheck1");
    let errorMsg = document.getElementById("checkboxError");

    if (!checkbox.checked) {
        errorMsg.innerHTML = "Please select the terms.";
        fieldsCheck["check"] = false;
    } else {
        errorMsg.innerHTML = "";
        fieldsCheck["check"] = true;
    }
    validateAndEnableSubmit();
}
