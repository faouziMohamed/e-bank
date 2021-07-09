package com.mybank.exception;

public class PasswordErrorException extends BankException {

  public PasswordErrorException(String reason) {
    super(reason);
  }

  public PasswordErrorException() {
    this("Passowrd Incorrect");
  }
}
