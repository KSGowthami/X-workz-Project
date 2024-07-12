package com.xworkz.myProject.controller;

import com.xworkz.myProject.Password.PasswordGenerate;
import com.xworkz.myProject.Service.SignUpService;
import com.xworkz.myProject.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class ForgotPasswordController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PasswordGenerate passwordGenerate;

    public ForgotPasswordController() {
        System.out.println("ForgotPasswordController");
    }

    @PostMapping("/forgotPassword")
    public String forgotPassword(@RequestParam("email") String email, Model model) {
        Optional<SignUpDto> userOptional = signUpService.findUserByEmail(email);
        if (userOptional.isPresent()) {
            SignUpDto user = userOptional.get();
            String newPassword = passwordGenerate.generatePassword();
            signUpService.updatePassword(user.getId(), newPassword);
            signUpService.resetLoginCount(user.getId());
            signUpService.sendEmailResetPassword(user);
            user.setPassword(newPassword);

            model.addAttribute("message", "A new password has been sent to your email.");
        } else {
            model.addAttribute("errors", "Email not found.");
        }
        return "ForgotPassword";
    }

}
