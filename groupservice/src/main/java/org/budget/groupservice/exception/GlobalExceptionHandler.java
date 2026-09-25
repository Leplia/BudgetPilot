package org.budget.groupservice.exception;

import org.budget.commonaudit.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(WrongEmailException.class)
    public ResponseEntity<ErrorResponse> handleTariffNotFoundException(WrongEmailException e) {
        ErrorResponse error = new ErrorResponse("1501", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}