package com.mybank.exception;

public class ClientNotFoundException extends BankException {
  public ClientNotFoundException() {
    this("Client not found");
  }

  public ClientNotFoundException(String reason) {
    super(reason);
  }
}
