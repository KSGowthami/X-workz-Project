package com.xworkz.myProject.Service;

import com.xworkz.myProject.dto.ComplaintsDTO;
import com.xworkz.myProject.dto.SignUpDto;
import com.xworkz.myProject.repository.ComplaintsRaiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComplaintsRaiseServiceImpl implements ComplaintsRaiseService {

    @Autowired
    private ComplaintsRaiseRepository complaintsRaiseRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public boolean validateAndSave(ComplaintsDTO complaintsDTO) {
        boolean save = complaintsRaiseRepository.save(complaintsDTO);
        return save;
    }

    @Override
    public List<ComplaintsDTO> getAllUsers(int userId)
    {
        return complaintsRaiseRepository.getAllComplaints(userId);
    }

    public void sendComplaintRaiseEmail(ComplaintsDTO complaintsDTO,SignUpDto signUpDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(signUpDto.getEmail());
        message.setSubject("Complaint Raise");
        message.setText("Dear User," + signUpDto.getFirstName() + " " + signUpDto.getLastName() +
                "\n\nYour Complaint has been raised" +
              "\n\n This is the complaint id for the reference "+complaintsDTO.getId()+
                "\n\nRegards,\nMyProject Team");
        javaMailSender.send(message);
    }

    public Optional<ComplaintsDTO> findById(int complaintId) {
        return complaintsRaiseRepository.findById(complaintId);
    }

    @Override
    public boolean updateComplaint(ComplaintsDTO complaintsDTO) {
        Optional<ComplaintsDTO> optionalComplaint = complaintsRaiseRepository.findById(complaintsDTO.getId());
        if (optionalComplaint.isPresent()) {
            ComplaintsDTO existingComplaint = optionalComplaint.get();
            existingComplaint.setDescription(complaintsDTO.getDescription());
            existingComplaint.setStatus(complaintsDTO.getStatus());
            existingComplaint.setDepartmentId(complaintsDTO.getDepartmentId());
            existingComplaint.setModifiedBy(System.getProperty("user.name"));
            existingComplaint.setModifiedAt(LocalDateTime.now());
            return complaintsRaiseRepository.updateComplaint(existingComplaint);
        }
        return false;
    }


}
