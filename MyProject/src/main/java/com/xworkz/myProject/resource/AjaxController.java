package com.xworkz.myProject.resource;

import com.xworkz.myProject.Service.DepartmentAdminService;
import com.xworkz.myProject.Service.SignUpService;
import com.xworkz.myProject.dto.DepartmentDTO;
import com.xworkz.myProject.dto.EmployeesDTO;
import com.xworkz.myProject.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class AjaxController {

    @Autowired
    private SignUpService signUpService;
    @Autowired
    private DepartmentAdminService departmentAdminService;


    @GetMapping("/validateEmail")
    public String validateEmail(@RequestParam String email){
        Optional<SignUpDto> validEmail = signUpService.findUserByEmail(email);
        return validEmail.isPresent() ? "Email already exists" : "";
    }

    @GetMapping("/validatePhone")
    public String validatePhoneNumber(@RequestParam Long phoneNo){
        Optional<SignUpDto> validPhoneNumber = signUpService.findPhone(phoneNo);
        return validPhoneNumber.isPresent() ? "Phone Number already exists" : "";
    }

    @GetMapping("departmentAdmin/emailValidation")
    public String validateMail(@RequestParam String email) {
        Optional<EmployeesDTO> optional = departmentAdminService.findEmployeeEmail(email);
        return optional.isPresent() ? "Email is already Exist" : "";
    }

    @GetMapping("departmentAdmin/phoneValidation")
    public String validateMobile(@RequestParam Long phone) {
        Optional<EmployeesDTO> existingPhone = departmentAdminService.findEmployeePhone(phone);
        return existingPhone.isPresent() ? "Phone is already Exist": "";
    }
}
