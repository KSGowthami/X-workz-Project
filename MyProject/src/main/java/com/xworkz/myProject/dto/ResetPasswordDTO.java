package com.xworkz.myProject.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class ResetPasswordDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Please provide an email")
    @Email(message = "Please provide a valid email")
    private String email;

    @NotEmpty(message = "Please provide current password")
    private String password;

    @NotEmpty(message = "Please provide new password")
    @Size(min = 16, message = "Password must be at least 16 characters long")
    private String newPassword;

    @NotEmpty(message = "Please confirm new password")
    private String confirmPassword;

    @Override
    public String toString() {
        return "ResetPasswordDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
