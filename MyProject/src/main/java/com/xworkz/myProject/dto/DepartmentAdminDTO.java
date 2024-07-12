package com.xworkz.myProject.dto;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departmentLogin")
@NamedQuery(name="findDepartmentAdminMail", query =" Select d from DepartmentAdminDTO d where d.email =: email")
public class DepartmentAdminDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentAdminId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
