package com.xworkz.myProject.dto;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="complaints")
public class ComplaintsDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;

    private String area;

    private String address;

    private String country;

    private String status;

    private String state;

    private String city;

    private String description;

    private String createdBy;

    private LocalDateTime createdAt;

    private String modifiedBy;

    private LocalDateTime modifiedAt;

    private Integer departmentId;

    private int userId;

    private Integer employeeId;
}
