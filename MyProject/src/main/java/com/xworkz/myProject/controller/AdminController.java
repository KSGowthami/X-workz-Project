package com.xworkz.myProject.controller;

import com.xworkz.myProject.Service.AdminService;
import com.xworkz.myProject.dto.AdminDTO;
import com.xworkz.myProject.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    public AdminController() {
        System.out.println("Running AdminController");
    }

    @GetMapping("/login")
    public String getAdminSignIn(Model model, @RequestParam(required = false, defaultValue = "true") boolean admin) {
        model.addAttribute("admin", admin);
        return "SignIn";
    }

    @PostMapping("/login")
    public String postAdminSignIn(Model model, AdminDTO adminDTO) {
        System.out.println("Post Admin Method");
        Optional<AdminDTO> adminOptional = adminService.findByEmail(adminDTO.getEmail());

        if (adminOptional.isPresent()) {
            AdminDTO admin = adminOptional.get();

            if (admin.getFailed_attempt_count() >= 3) {
                model.addAttribute("errors", "Your account is locked. Please reset your password or try again later.");
                model.addAttribute("admin", true);
                return "SignIn";
            }

            if (admin.getPassword().equals(adminDTO.getPassword())) {
                admin.setFailed_attempt_count(0);
                adminService.updateAdmin(admin);

                model.addAttribute("dto", admin);
                model.addAttribute("message", "Login successful. Welcome back!");
                model.addAttribute("admin", true);
                return "AdminHome";
            } else {
                admin.setFailed_attempt_count(admin.getFailed_attempt_count() + 1);
                adminService.updateAdmin(admin);

                model.addAttribute("errors", "Incorrect Password or Email");
                model.addAttribute("attempts", admin.getFailed_attempt_count());
                model.addAttribute("admin", true);
            }
        } else {
            model.addAttribute("errors", "User not found for email");
            model.addAttribute("admin", true);
        }

        return "SignIn";
    }

    @GetMapping("/userDetails")
    public String viewUsers(Model model) {
        List<SignUpDto> list = adminService.getAllUsers();
        model.addAttribute("dto", list);
        return "UsersDetails";
    }
}
