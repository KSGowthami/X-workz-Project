package com.xworkz.myProject.repository;

import com.xworkz.myProject.dto.AdminDTO;
import com.xworkz.myProject.dto.SignUpDto;

import java.util.List;
import java.util.Optional;

public interface AdminRepository {

    Optional<AdminDTO> findByEmail(String email);

    List<SignUpDto> getAllUsers();
    void updateAdmin(AdminDTO adminDTO);

    int getDepartmentIdByName(String departmentName);
}
