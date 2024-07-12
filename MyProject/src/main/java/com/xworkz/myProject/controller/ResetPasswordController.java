package com.xworkz.myProject.controller;

import com.xworkz.myProject.Service.SignUpService;
import com.xworkz.myProject.dto.ResetPasswordDTO;
import com.xworkz.myProject.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ResetPasswordController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/resetPassword")
    public String resetPassword(@Valid @ModelAttribute("resetPasswordDTO") ResetPasswordDTO resetPasswordDTO,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult);
            return "PasswordReset";
        }

        Optional<SignUpDto> userOptional = signUpService.findUserByEmail(resetPasswordDTO.getEmail());
        if (userOptional.isPresent()) {
            SignUpDto user = userOptional.get();

            if (!resetPasswordDTO.getPassword().equals(user.getPassword())) {
                model.addAttribute("customError", "Current password does not match");
                return "PasswordReset";
            }

            if (!resetPasswordDTO.getNewPassword().equals(resetPasswordDTO.getConfirmPassword())) {
                model.addAttribute("customError", "New password and confirm password do not match");
                return "PasswordReset";
            }

            // Update user password and save
            user.setPassword(resetPasswordDTO.getNewPassword());
            user.setUpdatedBy(user.getFirstName() + " " + user.getLastName());
            user.setUpdatedAt(LocalDateTime.now());
            SignUpDto updatedUser = signUpService.savePassword(user);

            if (updatedUser != null) {
                // Update login count
                signUpService.updateLoginCount(user.getId());

                model.addAttribute("message", "Password reset successful. Please login with your new password.");
                return "SignIn";
            } else {
                model.addAttribute("customError", "Password reset failed. Please try again.");
                return "PasswordReset";
            }
        } else {
            model.addAttribute("customError", "User not found for email");
            return "PasswordReset";
        }
    }
}
