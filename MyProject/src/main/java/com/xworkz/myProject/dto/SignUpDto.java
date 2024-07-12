package com.xworkz.myProject.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "signup")
@NamedQuery(name = "searchEmail", query = "select count(s.email) from SignUpDto s where s.email = :email")
@NamedQuery(name = "searchPhoneNo", query = "select count(s.email) from SignUpDto s where s.phoneNo = :phoneNo")
public class SignUpDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Please enter the name")
    @Size(min = 3, max = 30, message = "First name should be between 3 and 30 characters")
    private String firstName;

    @NotNull(message = "Please enter the name")
    @Size(min = 3, max = 30, message = "Last name should be between 3 and 30 characters")
    private String lastName;

    @NotEmpty(message = "Enter your email")
    @Email(message = "Enter a valid email")
    private String email;

    @NotNull(message = "Enter phone number")
    private Long phoneNo;

    private String createdBy;

    private LocalDateTime createdAt;

    private String updatedBy;

    private LocalDateTime updatedAt;

    private String password;

    @Transient
    private String check;

    @Column(name = "count")
    private int loginCount = 0;

    @Transient
    private String confirmPassword;

    @Transient
    private String newPassword;

    private Integer failed_attempt_count = 0;

    private LocalDateTime failed_attempt_dateTime;

    @Column(name = "profileImage")
    private String profileImage;

    public SignUpDto() {
        System.out.println("Sign up DTO is running");
    }

    @Override
    public String toString() {
        return "SignUpDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo=" + phoneNo +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", password='" + password + '\'' +
                ", loginCount=" + loginCount +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
