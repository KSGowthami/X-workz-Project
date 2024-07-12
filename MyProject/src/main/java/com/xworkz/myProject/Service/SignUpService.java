package com.xworkz.myProject.Service;

import com.xworkz.myProject.dto.SignUpDto;

import java.util.Optional;

public interface SignUpService {

     boolean saveAndValidate(SignUpDto signUpDto);
     Optional<SignUpDto> findUserEmail(String email);
    Optional<SignUpDto>  findPhone(Long phoneNo);
    Optional<SignUpDto> findById(int id);
    Optional<SignUpDto> findUserByEmail (String email);

    void sendEmailWithPassword(SignUpDto signUpDto);

    void updateLoginCount(int userId);
    SignUpDto savePassword(SignUpDto user);

    void updatePassword(int id, String newPassword);
    void resetLoginCount(int id);
    void sendEmailResetPassword(SignUpDto user);

    void updateUser(SignUpDto signUpDto);



}
