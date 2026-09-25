package org.budget.groupservice.exception;

public class WrongEmailException extends RuntimeException {
    public WrongEmailException() {
        super("Wrong email");
    }
}
