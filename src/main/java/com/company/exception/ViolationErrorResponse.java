package com.company.exception;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ViolationErrorResponse {

    private List<Violation> violation = new ArrayList<Violation>();

}
