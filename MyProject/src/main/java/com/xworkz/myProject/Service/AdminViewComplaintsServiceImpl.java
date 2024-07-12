package com.xworkz.myProject.Service;

import com.xworkz.myProject.dto.ComplaintsDTO;
import com.xworkz.myProject.dto.ComplaintsHistoryDTO;
import com.xworkz.myProject.dto.DepartmentDTO;
import com.xworkz.myProject.repository.AdminViewComplaintsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminViewComplaintsServiceImpl implements AdminViewComplaintsService {

    @Autowired
    private AdminViewComplaintsRepo adminViewComplaintsRepo;

    @Override
    public List<ComplaintsDTO> getComplaintsByType(String type) {
        return adminViewComplaintsRepo.findByType(type);
    }

    @Override
    public List<ComplaintsDTO> getComplaintsByArea(String area) {
        return adminViewComplaintsRepo.findByArea(area);
    }

    @Override
    public List<ComplaintsDTO> getComplaintsByTypeAndArea(String type, String area) {
        return adminViewComplaintsRepo.findByTypeAndArea(type, area);
    }

    @Override
    public List<ComplaintsDTO> getAllComplaints() {
        return adminViewComplaintsRepo.getAllComplaints();
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return adminViewComplaintsRepo.getAllDepartments();
    }

    @Override
    public boolean updateComplaintStatusAndDepartment(int complaintId, String status, int departmentId) {
        ComplaintsDTO complaint = adminViewComplaintsRepo.findById(complaintId);
        if (complaint != null) {
            complaint.setStatus(status);
            complaint.setDepartmentId(departmentId);
            adminViewComplaintsRepo.save(complaint);
            return true;
        }
        return false;
    }
    @Override
    public boolean updateComplaintStatus(int complaintId, String status) {
        ComplaintsDTO complaint = adminViewComplaintsRepo.findById(complaintId);
        if (complaint != null) {
            complaint.setStatus(status);
            adminViewComplaintsRepo.save(complaint);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateComplaintDepartment(int complaintId, int departmentId) {
        ComplaintsDTO complaint = adminViewComplaintsRepo.findById(complaintId);
        if (complaint != null) {
            complaint.setDepartmentId(departmentId);
            adminViewComplaintsRepo.save(complaint);
            return true;
        }
        return false;
    }

    @Override
    public boolean addComplaintsHistory(int complaintId, int userId, int departmentId, String status) {
        ComplaintsHistoryDTO history = new ComplaintsHistoryDTO();
        history.setComplaintsId(complaintId);
        history.setUserId(userId);
        history.setDepartmentId(departmentId);
        history.setStatus(status);
        adminViewComplaintsRepo.saveHistory(history);
        return true;
    }
}
