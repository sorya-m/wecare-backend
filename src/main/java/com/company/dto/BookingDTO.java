package com.company.dto;

import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class BookingDTO {

    private int bookingId;
    private String userId;
    private String coachId;
    private LocalDate appointmentDate;
    private String slot;
}