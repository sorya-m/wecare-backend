package com.company.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CoachDTO {

    private String coachId;
    @NotNull(message = "{coach.name.must}")
    @Size(min = 3, max = 50, message = "{coach.name.invalid}")
    private String name;

    private String gender;

    @Past
    // @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotNull(message = "{coach.password.must}")
    @Size(min = 5, max = 10, message = "{coach.password.invalid}")
    private String password;

    @NotNull(message = "{coach.mobileno.must}")
    private Long mobileNumber;

    @NotNull(message = "{coach.speciality.must}")

    @Size(min = 3, max = 50, message = "{coach.speciality.invalid}")
    private String speciality;

}
