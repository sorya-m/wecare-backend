package com.company.exception;

import com.company.utility.CompanyConstants;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@PropertySource("classpath:ValidationMessages.properties")
public class GlobalExceptionHandler {

    @Autowired
    private Environment environment;

    @ExceptionHandler({CoachNotFoundException.class, BookingAlreadyExistException.class, UserNotFoundException.class,})
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(Exception ex) {
        ErrorResponse err = new ErrorResponse();
        err.setStatusCode(HttpStatus.BAD_REQUEST.value());
        err.setMessage(ex.getMessage());
        return new ResponseEntity<>(err, HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentException(MethodArgumentNotValidException ex) {

        System.out.println(ex.getMessage());


        /*ex.getBindingResult().getFieldErrors()
                .stream().map(error -> new Violation(error.getField(), error.getDefaultMessage())
                );
*/

        ErrorResponse err = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", ")));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentException(ConstraintViolationException ex) {

        ErrorResponse err = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", ")));

        return new ResponseEntity<>(err, HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {

        ex.printStackTrace();
        ErrorResponse err = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                environment.getProperty(CompanyConstants.GENERAL_EXCEPTION_MESSAGE.getValue()));

        return new ResponseEntity<>(err, HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

}
