package com.xworkz.myProject.controller;

import com.xworkz.myProject.Service.AdminViewComplaintsService;
import com.xworkz.myProject.Service.ComplaintsRaiseService;
import com.xworkz.myProject.dto.ComplaintsDTO;
import com.xworkz.myProject.dto.DepartmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class AdminViewComplaintsController {

    @Autowired
    private AdminViewComplaintsService adminViewComplaintsService;

    @Autowired
    private ComplaintsRaiseService complaintsRaiseService;

    @GetMapping("/viewUserComplaints")
    public String viewUserComplaints(@RequestParam(value = "type", required = false) String type,
                                     @RequestParam(value = "area", required = false) String area,
                                     Model model) {


        List<ComplaintsDTO> complaints = null;
        List<DepartmentDTO> departments = adminViewComplaintsService.getAllDepartments();

        System.out.println("Received type: " + type + ", area: " + area);

        if (type != null && !type.isEmpty() && area != null && !area.isEmpty()) {
            complaints = adminViewComplaintsService.getComplaintsByTypeAndArea(type, area);
        } else if (type != null && !type.isEmpty()) {
            complaints = adminViewComplaintsService.getComplaintsByType(type);
        } else {
            complaints = adminViewComplaintsService.getAllComplaints();
        }
        if (area != null && !area.isEmpty()) {
            complaints = adminViewComplaintsService.getComplaintsByArea(area);
        }

        System.out.println("Fetched complaints: " + complaints);

        model.addAttribute("complaints", complaints);
        model.addAttribute("departments", departments);

        return "AdminViewComplaints";
    }


    @PostMapping("/updateComplaint")
    public String updateComplaint(@RequestParam("complaintId") int complaintId,
                                  @RequestParam("status") String status,
                                  @RequestParam("departmentId") Integer departmentId,
                                  RedirectAttributes redirectAttributes) {
        Optional<ComplaintsDTO> complaintOpt = complaintsRaiseService.findById(complaintId);

        if (complaintOpt.isPresent()) {
            ComplaintsDTO complaint = complaintOpt.get();
            complaint.setStatus(status);
            complaint.setModifiedBy(System.getProperty("user.name"));
            complaint.setModifiedAt(LocalDateTime.now());

            boolean isStatusUpdated = adminViewComplaintsService.updateComplaintStatus(complaintId, status);
            boolean isDepartmentUpdated = adminViewComplaintsService.updateComplaintDepartment(complaintId, departmentId);
            boolean isHistoryAdded = adminViewComplaintsService.addComplaintsHistory(complaintId, complaint.getUserId(), departmentId, status);

            if (isStatusUpdated && isDepartmentUpdated && isHistoryAdded) {
                redirectAttributes.addFlashAttribute("message", "Complaint updated successfully!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Failed to update complaint.");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Complaint not found.");
        }

        return "redirect:/admin/viewUserComplaints";
    }

}

