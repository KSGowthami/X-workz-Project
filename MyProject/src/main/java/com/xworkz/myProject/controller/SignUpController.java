package com.xworkz.myProject.controller;

import com.xworkz.myProject.dto.SignUpDto;
import com.xworkz.myProject.Service.SignUpService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
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
    public String onSignUp(@Valid SignUpDto signUpDto, BindingResult bindingResult, Model model) {
        System.out.println("Running SignUp controller");

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("dto", signUpDto);
        }else {

            Optional<SignUpDto> existingEmail= signUpService.findEmail(signUpDto.getEmail());
            if(existingEmail.isPresent()){
                model.addAttribute("message","Email is already Exists");
                return "SignUp";
            }
            Optional<SignUpDto> existingPhone= signUpService.findPhone(signUpDto.getPhoneNo());
            if(existingPhone.isPresent()){
                model.addAttribute("message","Phone number is already Exists");
                return "SignUp";
            }



            boolean save = signUpService.saveAndValidate(signUpDto);
            if (save) {
                model.addAttribute("message", signUpDto.getFirstName() + " " + signUpDto.getLastName() + " Signup Successful");
            }else{
                model.addAttribute("sign up not successful");
            }

        }
        return "SignUp";
    }
}
