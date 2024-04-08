package com.agira.project.ExceptionController;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class CustomAdvicer {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> invalidDetails(MethodArgumentNotValidException error) {

        Map<String, String> mapError = new HashMap<>();
        error.getBindingResult().getFieldErrors().forEach(err -> {
            mapError.put(err.getField(), err.getDefaultMessage());
        });

        return mapError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchElementException.class)
    public Map<String, String> InvaildPlayers(NoSuchElementException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> userNotFound(UserNotFoundException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> coninvalidDetails(ConstraintViolationException error) {

        Map<String, String> mapError = new HashMap<>();
        mapError.put("error", error.getMessage());
        return mapError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Map<String, String> sqlConstraint(SQLIntegrityConstraintViolationException error) {

        Map<String, String> mapError = new HashMap<>();
        mapError.put("error", error.getMessage());

        return mapError;
    }

    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public Map<String, String> userNotFound(UserAlreadyExistsException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public Map<String, String> passwordWrong(BadCredentialsException e) {
        Map<String, String> error = new HashMap<>();
        error.put("password", "your enter wrong password Try to enter Correct password?");
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public Map<String, String> tournamentFull(IllegalStateException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        return error;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public Map<String, String> runtime(RuntimeException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        return error;
    }


}
