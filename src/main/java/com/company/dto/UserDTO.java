package com.company.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private String userId;
    @NotNull(message = "{user.name.must}")
    @Size(min = 3, max = 50, message = "{user.name.invalid}")
    private String name;
    private String gender;

    @NotNull(message = "{user.password.must}")
    @Size(min = 3, max = 50, message = "{user.password.invalid}")
    private String password;
    private LocalDate dateOfBirth;

    @NotNull(message = "{user.mobileno.must}")
    private Long mobileNumber;
    private String email;
    @NotNull(message = "{user.pincode.must}")
    private Long pincode;
    @NotNull(message = "{user.city.must}")
    @Size(min = 3, max = 20, message = "{user.city.invalid}")
    private String city;
    @NotNull(message = "{user.state.must}")
    @Size(min = 3, max = 20, message = "{user.state.invalid}")
    private String state;
    @NotNull(message = "{user.country.must}")
    @Size(min = 3, max = 20, message = "{user.country.invalid}")
    private String country;
}
