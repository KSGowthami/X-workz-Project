package com.xworkz.myProject.Service;

import com.xworkz.myProject.Password.OTPGenerator;
import com.xworkz.myProject.dto.*;
import com.xworkz.myProject.repository.DepartmentAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentAdminServiceImpl implements DepartmentAdminService {

    @Autowired
    private DepartmentAdminRepo departmentAdminRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Optional<DepartmentAdminDTO> findByEmail(String email) {
        return departmentAdminRepo.findByEmail(email);
    }

    @Override
    @Transactional
    public boolean save(EmployeesDTO employeesDTO) {
        String otp = OTPGenerator.generateOTP(6);
        employeesDTO.setOtp(otp);

        try {
            departmentAdminRepo.save(employeesDTO);
            sendOTPEmail(employeesDTO.getEmail(), otp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return departmentAdminRepo.getAllDepartments();
    }

    private void sendOTPEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP for Employee Login");
        message.setText("Your OTP is: " + otp);
        mailSender.send(message);
    }

    @Override
    public Optional<EmployeesDTO> findEmployeePhone(Long phoneNo) {
        long count = departmentAdminRepo.findEmployeePhone(phoneNo);
        if (count >= 1) {
            return Optional.of(new EmployeesDTO());
        }
        return Optional.empty();
    }

    @Override
    public Optional<EmployeesDTO> findEmployeeEmail(String email) {
        long count = departmentAdminRepo.findEmployeeEmail(email);
        if (count >= 1) {
            return Optional.of(new EmployeesDTO());
        }
        return Optional.empty();
    }
}
