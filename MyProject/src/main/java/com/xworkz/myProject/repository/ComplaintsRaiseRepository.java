package com.xworkz.myProject.repository;

import com.xworkz.myProject.dto.ComplaintsDTO;

import java.util.List;
import java.util.Optional;

public interface ComplaintsRaiseRepository {

    boolean save(ComplaintsDTO complaintsDTO);
    public List<ComplaintsDTO> getAllComplaints(int userId);

    Optional<ComplaintsDTO> findById(int complaintId);
    boolean updateComplaint(ComplaintsDTO complaintsDTO);


}
