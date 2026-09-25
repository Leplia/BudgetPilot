package org.budget.authservice.exception;

import org.budget.commonaudit.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleTariffNotFoundException(EmailAlreadyExistException e) {
        ErrorResponse error = new ErrorResponse("1401", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(LoginAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleTariffNotFoundException(LoginAlreadyExistException e) {
        ErrorResponse error = new ErrorResponse("1402", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(WrongCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleTariffNotFoundException(WrongCredentialsException e) {
        ErrorResponse error = new ErrorResponse("1403", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}