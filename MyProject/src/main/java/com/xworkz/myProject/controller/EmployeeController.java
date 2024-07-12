package com.xworkz.myProject.controller;

import com.xworkz.myProject.Service.EmployeeService;
import com.xworkz.myProject.dto.EmployeesDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController() {
        log.info("created EmployeeController");
    }

    @PostMapping("/getOtp")
    public String getOtp(@RequestParam("email") String email, Model model,EmployeesDTO employeesDTO) {
        Optional<EmployeesDTO> optional = employeeService.findEmployeeByEmail(email);
        if (optional.isPresent()) {
            employeeService.generateAndSendOtp(email);
            model.addAttribute("message", "OTP sent to your email.");
            model.addAttribute("employee", employeesDTO);
        } else {
            model.addAttribute("emailError", "Email not found.");
        }
        return "EmployeeLogin";
    }


    @PostMapping("/loginWithOtp")
    public String loginWithOtp(@RequestParam("email") String email, @RequestParam("otp") String otp, Model model) {
        Optional<EmployeesDTO> optionalEmployee = employeeService.findEmployeeByEmail(email);

        if (optionalEmployee.isPresent()) {
            EmployeesDTO employee = optionalEmployee.get();

            if (employee.getOtp().equals(otp.trim())) {
                model.addAttribute("message", "Login successful. Welcome back!");
                return "EmployeeHome";
            } else {
                model.addAttribute("otpError", "Invalid OTP. Please try again.");
            }
        } else {
            model.addAttribute("otpError", "Employee not found.");
        }
        return "EmployeeLogin";
    }

}
