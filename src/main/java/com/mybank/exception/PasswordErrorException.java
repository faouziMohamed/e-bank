package com.mybank.exception;

public class PasswordErrorException extends BankException {

  public PasswordErrorException() {
    this("Passowrd Incorrect");
  }

  public PasswordErrorException(String reason) {
    super(reason);
  }
}
