package com.xworkz.myProject.controller;

import com.xworkz.myProject.Service.ComplaintsRaiseService;
import com.xworkz.myProject.Service.SignUpService;
import com.xworkz.myProject.dto.ComplaintsDTO;
import com.xworkz.myProject.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ComplaintsRaiseController {

    public ComplaintsRaiseController() {
        System.out.println("Running ComplaintsRaiseController");
    }

    @Autowired
    private ComplaintsRaiseService complaintsRaiseService;

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/raiseComplaint")
    public String setComplaint(ComplaintsDTO complaintsDTO, Model model, HttpSession session) {
        System.out.println("Running setComplaint in RaiseComplaintController");

        Integer userId = (Integer) session.getAttribute("userId");
        System.out.println("Retrieved userId from session: " + userId);


        if (userId == null || userId == 0) {
            model.addAttribute("complaintMsg", "User not logged in.");
            return "RaiseComplaints";
        }

        Optional<SignUpDto> optionalSignUpDto = signUpService.findById(userId);
        if (optionalSignUpDto.isPresent()) {
            SignUpDto signUpDto = optionalSignUpDto.get();
            complaintsDTO.setCreatedBy(signUpDto.getFirstName() + " " + signUpDto.getLastName());
            complaintsDTO.setCreatedAt(LocalDateTime.now());

            String systemUserName = System.getProperty("user.name");
            System.out.println("System Username: " + systemUserName);

            complaintsDTO.setModifiedBy(systemUserName);
            complaintsDTO.setModifiedAt(LocalDateTime.now());

            complaintsDTO.setUserId(userId);
            boolean save = complaintsRaiseService.validateAndSave(complaintsDTO);
            if (save) {
                model.addAttribute("complaintMsg", "Your Complaint is Received");
                model.addAttribute("dto",complaintsDTO);
                complaintsRaiseService.sendComplaintRaiseEmail(complaintsDTO, signUpDto);

            } else {
                model.addAttribute("complaintMsg", "Your Complaint is not Received, Please try again");
            }
        } else {
            model.addAttribute("complaintMsg", "User not found.");
        }

        return "RaiseComplaints";
    }

    @GetMapping("/viewComplaints")
    public String viewComplaints(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        System.out.println("viewComplaints");
        if (userId == null || userId == 0) {
            model.addAttribute("errorMsg", "User not logged in.");
            return "errorPage";
        } else {
            List<ComplaintsDTO> complaints = complaintsRaiseService.getAllUsers(userId);

            model.addAttribute("complaints", complaints);
            return "ViewComplaints";
        }
    }

    @GetMapping("/editComplaints")
    public String showEditComplaint(@RequestParam("complaintId") int complaintId, Model model) {
        Optional<ComplaintsDTO> optionalComplaint = complaintsRaiseService.findById(complaintId);
        if (optionalComplaint.isPresent()) {
            ComplaintsDTO complaint = optionalComplaint.get();
            model.addAttribute("dto", complaint);
            model.addAttribute("action", "edit");
        } else {
            model.addAttribute("errorMsg", "Complaint not found.");
        }
        return "RaiseComplaints";
    }


    @PostMapping("/editComplaints")
    public String updateComplaint(ComplaintsDTO complaintsDTO, Model model, HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");
        System.out.println("User ID: " + userId);
        if (userId == null || userId == 0) {
            model.addAttribute("complaintMsg", "User not logged in.");
            return "RaiseComplaints";
        }

        System.out.println("Complaint ID: " + complaintsDTO.getId());

        Optional<ComplaintsDTO> optionalComplaint = complaintsRaiseService.findById(complaintsDTO.getId());
        if (optionalComplaint.isPresent()) {
            complaintsDTO.setModifiedBy(System.getProperty("user.name"));
            complaintsDTO.setModifiedAt(LocalDateTime.now());
            boolean updated = complaintsRaiseService.updateComplaint(complaintsDTO);
            if (updated) {
                model.addAttribute("complaintMsg", "Complaint updated successfully.");
            } else {
                model.addAttribute("complaintMsg", "Failed to update complaint.");
            }
        } else {
            model.addAttribute("complaintMsg", "Invalid complaint ID.");
        }
        return "RaiseComplaints";
    }

}
