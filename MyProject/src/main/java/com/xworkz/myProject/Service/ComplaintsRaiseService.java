package com.xworkz.myProject.Service;

import com.xworkz.myProject.dto.ComplaintsDTO;
import com.xworkz.myProject.dto.SignUpDto;

import java.util.List;
import java.util.Optional;

public interface ComplaintsRaiseService {

    boolean validateAndSave(ComplaintsDTO complaintsDTO);

    List<ComplaintsDTO> getAllUsers(int user);
    void sendComplaintRaiseEmail(ComplaintsDTO complaintsDTO, SignUpDto signUpDto);

    Optional<ComplaintsDTO> findById(int complaintId);
    boolean updateComplaint(ComplaintsDTO complaintsDTO);

}
