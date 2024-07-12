package com.xworkz.myProject.Service;

import com.xworkz.myProject.Password.PasswordGenerate;
import com.xworkz.myProject.dto.SignUpDto;
import com.xworkz.myProject.repository.SignUpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SignUpServiceImplementation implements SignUpService {

    @Autowired
    private SignUpRepo signUpRepo;

    @Autowired
    private PasswordGenerate passwordGenerate;

    @Override
    public boolean saveAndValidate(SignUpDto signUpDto) {

        System.out.println("running save in SignUpServiceImpl");


        // Generate password and set other fields
        String generatedPassword = PasswordGenerate.generatePassword();
        signUpDto.setPassword(generatedPassword);

        //Set createdBy and CreatedAt
        String fullName = signUpDto.getFirstName() + " " + signUpDto.getLastName();
        signUpDto.setCreatedBy(fullName);
        signUpDto.setCreatedAt(LocalDateTime.now());



        boolean save = signUpRepo.save(signUpDto);
        if (save) {
            System.out.println("Data saved successfully" + signUpDto);
        } else {
            System.out.println("Data is not saved");
        }
        return true;
    }

    @Override
    public Optional<SignUpDto> findEmail(String email) {
        long count= signUpRepo.findEmail(email);
        if(count>= 1){
            return Optional.of(new SignUpDto());
        }
        return Optional.empty();
    }

    @Override
    public Optional<SignUpDto> findPhone(Long phoneNo) {
        long count= signUpRepo.findPhoneNumber(phoneNo);
        if (count>= 1){
            return Optional.of(new SignUpDto());
        }

        return Optional.empty();
    }


}
