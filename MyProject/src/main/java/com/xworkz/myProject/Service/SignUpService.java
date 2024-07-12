package com.xworkz.myProject.Service;

import com.xworkz.myProject.dto.SignUpDto;

import java.util.Optional;

public interface SignUpService {

    boolean saveAndValidate(SignUpDto signUpDto);

    Optional<SignUpDto> findEmail(String email);

    Optional<SignUpDto> findPhone(Long phoneNo);
}
