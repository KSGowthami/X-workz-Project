package com.xworkz.myProject.Service;

import com.xworkz.myProject.dto.DepartmentAdminDTO;
import com.xworkz.myProject.dto.DepartmentDTO;
import com.xworkz.myProject.dto.EmployeesDTO;
import com.xworkz.myProject.dto.SignUpDto;

import java.util.List;
import java.util.Optional;

public interface DepartmentAdminService {
    Optional<DepartmentAdminDTO> findByEmail(String email);

    boolean save(EmployeesDTO employeesDTO);

    List<DepartmentDTO> getAllDepartments();
    Optional<EmployeesDTO> findEmployeePhone(Long phoneNo);
    Optional<EmployeesDTO> findEmployeeEmail(String email);
}
