package com.xworkz.myProject.repository;

import com.xworkz.myProject.dto.SignUpDto;

public interface SignUpRepo {

    boolean save(SignUpDto signUpDto);

    long findEmail(String email);

    long findPhoneNumber(Long phoneNo);
}
