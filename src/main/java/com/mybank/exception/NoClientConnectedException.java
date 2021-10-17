package com.mybank.exception;

public class NoClientConnectedException extends BankException {
  public NoClientConnectedException() {
    this("There is any client connected");
  }

  public NoClientConnectedException(String reason) {
    super(reason);
  }
}
