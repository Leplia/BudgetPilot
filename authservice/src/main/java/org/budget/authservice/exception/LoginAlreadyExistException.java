package org.budget.authservice.exception;

public class LoginAlreadyExistException extends RuntimeException {
    public LoginAlreadyExistException() {
        super("Login already exists");
    }
}
