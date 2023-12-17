package com.company.exception;

public class BookingAlreadyExistException extends Exception {

    public BookingAlreadyExistException(String message) {
        super(message);
    }
}