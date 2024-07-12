package com.xworkz.myProject.repository;

import com.xworkz.myProject.dto.DepartmentAdminDTO;
import com.xworkz.myProject.dto.DepartmentDTO;
import com.xworkz.myProject.dto.EmployeesDTO;

import java.util.List;
import java.util.Optional;

public interface DepartmentAdminRepo {
    Optional<DepartmentAdminDTO> findByEmail(String email);

    boolean save(EmployeesDTO employeesDTO);

    List<DepartmentDTO> getAllDepartments();

    long findEmployeeEmail(String email);
    public long findEmployeePhone(Long phoneNo);

}
