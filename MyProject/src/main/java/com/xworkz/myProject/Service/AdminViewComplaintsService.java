package com.xworkz.myProject.Service;

import com.xworkz.myProject.dto.ComplaintsDTO;
import com.xworkz.myProject.dto.DepartmentDTO;

import java.util.List;

public interface AdminViewComplaintsService {
    List<ComplaintsDTO> getComplaintsByType(String type);

    List<ComplaintsDTO> getComplaintsByArea(String area);

    List<ComplaintsDTO> getComplaintsByTypeAndArea(String type, String area);

    List<ComplaintsDTO> getAllComplaints();

    List<DepartmentDTO> getAllDepartments();

    boolean updateComplaintStatusAndDepartment(int complaintId, String status, int departmentId);
    boolean updateComplaintStatus(int complaintId, String status);
    boolean updateComplaintDepartment(int complaintId, int departmentId);
    boolean addComplaintsHistory(int complaintId, int userId, int departmentId, String status);

}

