package com.xworkz.myProject.dto;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name="employee")
@NamedQuery(name = "searchEmployeeMail", query = "select count(e.email) from EmployeesDTO e where e.email = :email")
@NamedQuery(name = "searchEmployeePhone", query = "select count(e.phoneNo) from EmployeesDTO e where e.phoneNo = :phoneNo")
public class EmployeesDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    private String firstName;

    private String lastName;

    private String designation;

    private String email;

    private Long phoneNo;

    private Integer departmentId;

    @Column(name = "otp", length = 6)
    private String otp;

}
