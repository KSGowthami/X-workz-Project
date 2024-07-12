package com.xworkz.myProject.Service;

import com.xworkz.myProject.dto.AdminDTO;
import com.xworkz.myProject.dto.SignUpDto;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    Optional<AdminDTO> findByEmail(String email);
    List<SignUpDto> getAllUsers();
    void updateAdmin(AdminDTO adminDTO);

    int getDepartmentIdByType(String departmentName);
}
