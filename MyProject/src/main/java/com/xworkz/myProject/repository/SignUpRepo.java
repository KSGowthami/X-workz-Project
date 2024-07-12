package com.xworkz.myProject.repository;

import com.xworkz.myProject.dto.SignUpDto;

import java.util.Optional;

public interface SignUpRepo {

    boolean save(SignUpDto signUpDto);

    long findEmail(String email);

   long findByPhone(Long phoneNo);

    Optional<SignUpDto> findById(int id);

    Optional<SignUpDto> findByEmail(String email);

    public SignUpDto merge(SignUpDto signUpDto);

    void updatePassword(int id, String newPassword);

    void resetLoginCount(int id);

    SignUpDto updateUser(SignUpDto signUpDto);

}
