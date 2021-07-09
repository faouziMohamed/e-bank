package com.mybank.exception;

public class BankException extends Exception {
  public BankException(String reason) {
    super(reason);
  }
}
