package com.xworkz.myProject.repository;

import com.xworkz.myProject.dto.EmployeesDTO;

import java.util.Optional;

public interface EmployeeRepo {
    Optional<EmployeesDTO> findByEmail(String email);
    boolean save(EmployeesDTO employeesDTO);
    void updateOtp(String email, String otp);
}
