package org.budget.authservice.exception;

public class WrongCredentialsException extends RuntimeException {
  public WrongCredentialsException() { super("Wrong credentials exception"); }
}
