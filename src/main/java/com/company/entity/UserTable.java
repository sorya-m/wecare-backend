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
public class UserTable {

    @Id
    @GenericGenerator(name = "customuserid", strategy = "com.company.utility.UserIdGenerator")
    @GeneratedValue(generator = "customuserid")
    private String userId;

    private String name;
    private String gender;
    private String password;
    private LocalDate dateOfBirth;
    private Long mobileNumber;
    private String email;
    private Long pincode;
    private String city;
    private String state;
    private String country;

}
