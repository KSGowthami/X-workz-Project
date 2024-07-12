package com.xworkz.myProject.controller;

import com.xworkz.myProject.Service.AdminViewComplaintsService;
import com.xworkz.myProject.Service.DepartmentAdminService;
import com.xworkz.myProject.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/department")
@Slf4j
public class DepartmentAdminController {

    @Autowired
    private DepartmentAdminService departmentAdminService;

    @Autowired
    private AdminViewComplaintsService adminViewComplaintsService;

    public  DepartmentAdminController(){
        log.info("running DepartmentAdminController");
    }
    @GetMapping("/login")
    public String getDepartmentAdminSignIn(Model model, @RequestParam(required = false, defaultValue = "true") boolean department) {
        model.addAttribute("department", department);
        return "SignIn";
    }

    @PostMapping("/login")
    public String departmentAdmin(Model model, DepartmentAdminDTO departmentAdminDTO) {
        log.info("Running department admin signIn");

        Optional<DepartmentAdminDTO> optional = departmentAdminService.findByEmail(departmentAdminDTO.getEmail());

        if (optional.isPresent()) {
            DepartmentAdminDTO department = optional.get();

            if (department.getPassword().equals(departmentAdminDTO.getPassword())) {
                model.addAttribute("message", "Login successful. Welcome back!");
                return "DepartmentHome";
            } else {
                model.addAttribute("errors", "Incorrect email or password");
            }
        } else {
            model.addAttribute("errors", "Admin not found");
        }
        return "redirect:/department/login";
    }

    @GetMapping("/departmentViewComplaints")
    public String viewUserComplaints(@RequestParam(value = "type", required = false) String type,
                                     Model model) {

        List<ComplaintsDTO> complaints = null;

        if (type != null && !type.isEmpty()) {
            complaints = adminViewComplaintsService.getComplaintsByType(type);
        } else {
            complaints = adminViewComplaintsService.getAllComplaints();
        }

        model.addAttribute("complaints", complaints);

        return "DepartmentViewComplaints";
    }

    @GetMapping("/addEmployees")
    public String showAddEmployeesForm(Model model) {
        List<DepartmentDTO> departments = departmentAdminService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "AddEmployees";
    }


    @PostMapping("/addEmployees")
    public String saveEmployees(@ModelAttribute("employeesDTO") @Valid EmployeesDTO employeesDTO,
                                BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            // Handle validation errors
            List<DepartmentDTO> departments = departmentAdminService.getAllDepartments();
            model.addAttribute("departments", departments); // Ensure departments are added back to the model
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "AddEmployees";
        } else {
            // Process form submission
            boolean save = departmentAdminService.save(employeesDTO);
            if (save) {
                model.addAttribute("message", employeesDTO.getFirstName() + " " + employeesDTO.getLastName() + " Employee added successfully");
                model.addAttribute("employeesDTO", new EmployeesDTO());
            } else {
                model.addAttribute("message", "Unable to add Employee");
            }

            // Add departments again to populate the dropdown
            List<DepartmentDTO> departments = departmentAdminService.getAllDepartments();
            model.addAttribute("departments", departments);

            return "AddEmployees";
        }
    }

}
