package com.xworkz.myProject.dto;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="complaintshistory")
public class ComplaintsHistoryDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int historyId;

    private int userId;

    private int complaintsId;

    private int departmentId;

    private String status;
}
