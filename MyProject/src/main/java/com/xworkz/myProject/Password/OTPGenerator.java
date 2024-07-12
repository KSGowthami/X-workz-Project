package com.xworkz.myProject.Password;

public class OTPGenerator {

    public static String generateOTP(int length) {
        String digits = "0123456789";
        StringBuilder otp = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            otp.append(digits.charAt((int) (Math.random() * digits.length())));
        }
        return otp.toString();
    }
}
