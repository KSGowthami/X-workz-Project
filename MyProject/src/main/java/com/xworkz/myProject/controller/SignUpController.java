package com.xworkz.myProject.controller;

import com.xworkz.myProject.dto.SignUpDto;
import com.xworkz.myProject.Service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;


@Controller
@RequestMapping("/")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    public SignUpController() {
        System.out.println("Calling SignUp Controller");
    }

    @PostMapping("/sign")
    public String onSignUp(@Valid SignUpDto signUpDto, BindingResult bindingResult, Model model ) {
        System.out.println("Running SignUp controller");

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("dto", signUpDto);
            System.out.println("id=============" + signUpDto.getId());
        } else {
            Optional<SignUpDto> existingEmail = signUpService.findUserEmail(signUpDto.getEmail());
            if (existingEmail.isPresent()) {
                model.addAttribute("message", "Email is already Exists");
                return "SignUp";
            }
            Optional<SignUpDto> existingPhone = signUpService.findPhone(signUpDto.getPhoneNo());
            if (existingPhone.isPresent()) {
                model.addAttribute("message", "Phone number is already Exists");
                return "SignUp";
            }

            boolean save = signUpService.saveAndValidate(signUpDto);
            if (save) {
                model.addAttribute("message", signUpDto.getFirstName() + " " + signUpDto.getLastName() + " Signup Successful");
                return "SignIn";
            } else {
                model.addAttribute("message", "Sign up not successful");
            }
        }
        model.addAttribute("dto",signUpDto);
        return "SignUp";
    }

    @PostMapping("/login")
    public String login(SignUpDto signUpDto, Model model, HttpServletRequest request) {
        System.out.println("Running login method");
        Optional<SignUpDto> userOptional = signUpService.findUserByEmail(signUpDto.getEmail());

        if (userOptional.isPresent()) {
            SignUpDto user = userOptional.get();

            if (user.getFailed_attempt_count() >= 3) {
                if (user.getFailed_attempt_dateTime() != null && user.getFailed_attempt_dateTime().plusHours(1).isAfter(LocalDateTime.now())) {
                    model.addAttribute("errors", "Your account is locked. Please reset your password or try again later.");
                    return "SignIn";
                } else {
                    // Reset failed attempt count after 1 hour
                    user.setFailed_attempt_count(0);
                    user.setFailed_attempt_dateTime(null);
                    signUpService.updateUser(user);
                }
            }

            if (user.getPassword().equals(signUpDto.getPassword())) {
                user.setFailed_attempt_count(0);
                user.setFailed_attempt_dateTime(null);
                signUpService.updateUser(user);

                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("userId", user.getId());
                httpSession.setAttribute("email", user.getEmail());
                httpSession.setAttribute("firstName", user.getFirstName());
                httpSession.setAttribute("lastName", user.getLastName());
                httpSession.setAttribute("phoneNumber", user.getPhoneNo());

                // Set userId in session


                System.out.println("User ID set in session: " + user.getId());

                // Add profile image URL to the session
                String profileImageUrl = "/images/" + user.getProfileImage();
                httpSession.setAttribute("profileImage", profileImageUrl);

                if (user.getLoginCount() == 0) {
                    model.addAttribute("dto", user);
                    model.addAttribute("message", "Login successful. Please reset your password.");
                    return "PasswordReset";
                } else {
                    signUpService.updateLoginCount(user.getId());
                    model.addAttribute("message", "Login successful. Welcome back!");

                    return "Home";
                }
            } else {
                user.setFailed_attempt_count(user.getFailed_attempt_count() + 1);
                if (user.getFailed_attempt_count() >= 3) {
                    user.setFailed_attempt_dateTime(LocalDateTime.now());
                }
                signUpService.updateUser(user);

                model.addAttribute("errors", "Incorrect Password or Email");
                model.addAttribute("attempts", user.getFailed_attempt_count());
            }
        } else {
            model.addAttribute("errors", "User not found for email");
        }
        return "SignIn";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}