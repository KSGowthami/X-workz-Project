package com.xworkz.myProject.Service;

import com.xworkz.myProject.Password.PasswordGenerate;
import com.xworkz.myProject.dto.SignUpDto;
import com.xworkz.myProject.repository.SignUpRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class SignUpServiceImplementation implements SignUpService {

    @Autowired
    private SignUpRepo signUpRepo;

    @Autowired
    private PasswordGenerate passwordGenerate;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public boolean saveAndValidate(SignUpDto signUpDto) {
        log.info("running save in SignUpServiceImpl");

        String generatedPassword = PasswordGenerate.generatePassword();
        signUpDto.setPassword(generatedPassword);

        String fullName = signUpDto.getFirstName() + " " + signUpDto.getLastName();
        signUpDto.setCreatedBy(fullName);
        signUpDto.setCreatedAt(LocalDateTime.now());

        signUpDto.setLoginCount(0);
        signUpDto.setFailed_attempt_count(0);

        boolean save = signUpRepo.save(signUpDto);
        if (save) {
            System.out.println("Data saved successfully: " + signUpDto);
            sendEmailWithPassword(signUpDto);
            return true;
        } else {
            System.out.println("Data is not saved");
            return false;
        }
    }

    @Override
    public Optional<SignUpDto> findUserEmail(String email) {
        long count = signUpRepo.findEmail(email);
        if (count >= 1) {
            return Optional.of(new SignUpDto());
        }
        return Optional.empty();
    }

    @Override
    public Optional<SignUpDto> findPhone(Long phoneNo) {
        long count = signUpRepo.findByPhone(phoneNo);
        if (count >= 1) {
            return Optional.of(new SignUpDto());
        }
        return Optional.empty();
    }

    public Optional<SignUpDto> findById(int id) {
        Optional<SignUpDto> signUpDto = signUpRepo.findById(id);
        return Optional.of(signUpDto.get());
    }

    // Send password to email
    public void sendEmailWithPassword(SignUpDto signUpDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(signUpDto.getEmail());
        message.setSubject("Your One Time Password");
        message.setText("Dear User," + signUpDto.getFirstName() + " " + signUpDto.getLastName() + "\n\nYour account has been" +
                " created successfully.  Please Sign in using this password"
                + signUpDto.getPassword() + "\n\nRegards,\nMyProject Team");
        javaMailSender.send(message);
    }

    // Count the user number of logins
    @Override
    public void updateLoginCount(int userId) {
        Optional<SignUpDto> userOptional = signUpRepo.findById(userId);
        if (userOptional.isPresent()) {
            SignUpDto user = userOptional.get();
            user.setLoginCount(user.getLoginCount() + 1);
            signUpRepo.updateUser(user);
        }
    }


    @Override
    public Optional<SignUpDto> findUserByEmail(String email) {
        return signUpRepo.findByEmail(email);
    }

    @Override
    public SignUpDto savePassword(SignUpDto signUpDto) {
        signUpDto.setUpdatedBy(signUpDto.getFirstName() + " " + signUpDto.getLastName());
        signUpDto.setUpdatedAt(LocalDateTime.now());
        return signUpRepo.updateUser(signUpDto);
    }

    @Override
    public void updatePassword(int id, String newPassword) {
        signUpRepo.updatePassword(id, newPassword);
    }

    @Override
    public void resetLoginCount(int id) {
        signUpRepo.resetLoginCount(id);
    }

    @Override
    public void sendEmailResetPassword(SignUpDto user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Password Reset");
        message.setText("Dear " + user.getFirstName() + ",\n\nYour password has been reset. Your new password is: " + user.getPassword() + "\n\nPlease change your password after logging in.");

        javaMailSender.send(message);
    }

    @Override
    public void updateUser(SignUpDto signUpDto) {
        signUpRepo.updateUser(signUpDto);
    }
}
