package com.xworkz.myProject.Service;

import com.xworkz.myProject.dto.EmployeesDTO;

import java.util.Optional;

public interface EmployeeService {
    Optional<EmployeesDTO> findEmployeeByEmail(String email);

    void generateAndSendOtp(String email);

}
