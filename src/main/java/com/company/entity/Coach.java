package com.company.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Coach {

    @Id
    @GenericGenerator(name = "customcoachid", strategy = "com.company.utility.CoachIdGenerator")
    @GeneratedValue(generator = "customcoachid")
    private String coachId;

    private String name;
    private String gender;
    private LocalDate dateOfBirth;
    private String password;
    private Long mobileNumber;
    private String speciality;


}
