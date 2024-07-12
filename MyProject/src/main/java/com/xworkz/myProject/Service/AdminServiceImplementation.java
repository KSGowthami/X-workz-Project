package com.xworkz.myProject.Service;

import com.xworkz.myProject.dto.AdminDTO;
import com.xworkz.myProject.dto.SignUpDto;
import com.xworkz.myProject.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImplementation implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Optional<AdminDTO> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public List<SignUpDto> getAllUsers() {
        return adminRepository.getAllUsers();
    }

    @Override
    public void updateAdmin(AdminDTO adminDTO) {
        adminRepository.updateAdmin(adminDTO);
    }

    @Override
    public int getDepartmentIdByType(String departmentName) {
        return adminRepository.getDepartmentIdByName(departmentName);
    }

}
