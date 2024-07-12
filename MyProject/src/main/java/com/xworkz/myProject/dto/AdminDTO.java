package com.xworkz.myProject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "admin")
@NamedQuery(name="findAdminMail",query = "select a from AdminDTO a where a.email= :email")
public class AdminDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String createdBy;

    private LocalDateTime createdAt;

    private String updatedBy;

    private LocalDateTime updatedAt;

    private int failed_attempt_count ;

}
