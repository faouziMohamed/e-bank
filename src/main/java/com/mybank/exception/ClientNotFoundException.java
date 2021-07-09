package com.mybank.exception;

public class ClientNotFoundException extends BankException {
  public ClientNotFoundException(String reason) {
    super(reason);
  }

  public ClientNotFoundException() {
    this("Client not found");
  }
}
