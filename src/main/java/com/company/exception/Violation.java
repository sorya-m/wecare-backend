package com.company.exception;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Violation {
    private String fieldname;
    private String message;
}
