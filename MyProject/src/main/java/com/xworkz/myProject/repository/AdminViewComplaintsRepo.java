package com.xworkz.myProject.repository;

import com.xworkz.myProject.dto.ComplaintsDTO;
import com.xworkz.myProject.dto.ComplaintsHistoryDTO;
import com.xworkz.myProject.dto.DepartmentDTO;

import java.util.List;

public interface AdminViewComplaintsRepo {
    List<ComplaintsDTO> findByType(String type);
    List<ComplaintsDTO> findByArea(String area);
    List<ComplaintsDTO> findByTypeAndArea(String type, String area);
    List<ComplaintsDTO> getAllComplaints();
    List<DepartmentDTO> getAllDepartments();
    ComplaintsDTO findById(int id);
    void save(ComplaintsDTO complaint);
    void saveHistory(ComplaintsHistoryDTO complaintsHistoryDTO);

}
