package com.xworkz.myProject.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="signup")
@NamedQuery(name = "searchEmail",query = "select count(s.email) from SignUpDto s where s.email= :email")
@NamedQuery(name="searchPhone" ,query = "select count(s.phoneNo) from SignUpDto s where s.phoneNo= :phoneNo")
public class SignUpDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Please enter the name")
    @Size(min = 3, max = 30, message = "first name should be 3 or 30 characters")
    private String firstName;

    @NotNull(message = "Please enter the name")
    @Size(min = 3, max = 30, message = " last name should be 3 or 30 characters")
    private String lastName;

    @NotNull(message = "Enter your mail")
    @Email(message = "Enter your mail")
    private String email;

    @NotNull(message = "Enter phone number")
    @Min(1)
    @Max(9999999999L)
    private Long phoneNo;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedBy")
    private String updatedBy;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    private String password;

    public String getPassword() {
        return password;
    }
    public SignUpDto(){
        System.out.println("Sign up dto is running");
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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
                '}';
    }
}