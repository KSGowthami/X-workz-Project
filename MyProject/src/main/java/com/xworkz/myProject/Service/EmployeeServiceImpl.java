package com.xworkz.myProject.Service;

import com.xworkz.myProject.Password.OTPGenerator;
import com.xworkz.myProject.dto.EmployeesDTO;
import com.xworkz.myProject.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Optional<EmployeesDTO> findEmployeeByEmail(String email) {
        return employeeRepo.findByEmail(email);
    }

    @Override
    public void generateAndSendOtp(String email) {
        String otp = OTPGenerator.generateOTP(6);
        employeeRepo.updateOtp(email, otp);
        sendOTPEmail(email, otp);
    }




    private void sendOTPEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP for Employee Login");
        message.setText("Your OTP is: " + otp);
        mailSender.send(message);
    }
}
