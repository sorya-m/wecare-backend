package com.company.utility;

public enum CompanyConstants {
    USER_NOT_FOUND("user.not.found"),
    COACH_NOT_FOUND("coach.not.found"),
    BOOKING_ALREADY_EXISTS("booking.already.exists"),
    GENERAL_EXCEPTION_MESSAGE("general.exception");


    private String value;

    CompanyConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
