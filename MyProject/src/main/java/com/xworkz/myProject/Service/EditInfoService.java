package com.xworkz.myProject.Service;

import com.xworkz.myProject.dto.SignUpDto;

public interface EditInfoService {

    SignUpDto updateUserProfile(String email, SignUpDto signUpDto);
}
