let fieldsCheck = {
    "firstName": false,
    "lastName":false,
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

function validateFirstName(){
    let name = document.getElementById('firstName');
    let errorMsg = document.getElementById('fastNameError');

    if (!/^[a-zA-Z\s]{3,30}$/.test(name.value)) {
        errorMsg.innerHTML = " First Name must contain only alphabets and name should have 3-30 characters.";
        fieldsCheck["firstName"] = false;
    } else {
        errorMsg.innerHTML = "";
        fieldsCheck["firstName"] = true;
    }
    validateAndEnableSubmit();
}

function validateLastName(){
    let name = document.getElementById('lastName');
    let errorMsg = document.getElementById('lastNameError'); // corrected ID

    if (!/^[a-zA-Z\s]{3,30}$/.test(name.value)) {
        errorMsg.innerHTML = "Last Name must contain only alphabets and name should have 3-30 characters.";
        fieldsCheck["lastName"] = false;
    } else {
        errorMsg.innerHTML = "";
        fieldsCheck["lastName"] = true;
    }
    validateAndEnableSubmit();
}

function validatePhoneNumber() {
    let phoneno = document.getElementById("phoneNo");
    let errorMsg = document.getElementById("phoneNumberError");
    let pno = phoneno.value.trim();

    if (pno.length === 10 && /^\d+$/.test(pno)) {
        errorMsg.innerHTML = "";
        fieldsCheck["phoneNo"] = true;
    } else {
        errorMsg.innerHTML = "Please enter a valid 10-digit phone number.";
        fieldsCheck["phoneNo"] = false;
    }
    validateAndEnableSubmit();
}

function validateEmail() {
    let email = document.getElementById("email");
    let errorMsg = document.getElementById("emailError");

    if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email.value)) {
        errorMsg.innerHTML = "Enter a valid email address.";
        fieldsCheck["email"] = false;
    } else {
        errorMsg.innerHTML = "";
        fieldsCheck["email"] = true;
    }
    validateAndEnableSubmit();
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