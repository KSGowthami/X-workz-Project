package com.xworkz.myProject.Service;

import com.xworkz.myProject.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EditInfoServiceImpl implements EditInfoService{



    @Autowired
    private SignUpService signUpService;

    public SignUpDto updateUserProfile(String email, SignUpDto signUpDto) {
        Optional<SignUpDto> optionalUser = signUpService.findUserByEmail(email);
        if (optionalUser.isPresent()) {
            SignUpDto user = optionalUser.get();
            // Update the user's profile with new information from signUpDto
            user.setFirstName(signUpDto.getFirstName());
            user.setLastName(signUpDto.getLastName());
            user.setPhoneNo(signUpDto.getPhoneNo());
            user.setProfileImage(signUpDto.getProfileImage());
            user.setUpdatedBy(signUpDto.getEmail());
            user.setUpdatedAt(signUpDto.getUpdatedAt());

            signUpService.updateUser(user);
            return user;
        }
        return null;
    }
}
