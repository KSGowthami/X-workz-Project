    // Function to generate a random CAPTCHA string
    function generateCaptcha() {
        var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        var captcha = '';
        for (var i = 0; i < 5; i++) { // Adjust the length of CAPTCHA as needed
            var randomIndex = Math.floor(Math.random() * chars.length);
            captcha += chars.charAt(randomIndex);
        }
        return captcha;
    }

    // Validate CAPTCHA on form submission
    document.getElementById("submitBtn").addEventListener("click", function(event) {
        var captchaInput = document.getElementById("captcha").value.trim();
        var correctCaptcha = document.getElementById("correctCaptcha").value;

        if (captchaInput === correctCaptcha) {
            // CAPTCHA is correct
            return true; // Submit the form
        } else {
            // CAPTCHA is incorrect
            document.getElementById("captchaError").innerText = "Incorrect Captcha. Please try again.";
            event.preventDefault(); // Prevent form submission
        }
    });

    // Generate CAPTCHA on page load
    window.onload = function() {
        var captcha = generateCaptcha();
        document.getElementById("captchaText").innerText = captcha; // Set CAPTCHA text
        document.getElementById("correctCaptcha").value = captcha; // Set correct CAPTCHA value
    };