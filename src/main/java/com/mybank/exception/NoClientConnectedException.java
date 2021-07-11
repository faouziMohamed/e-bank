package com.mybank.exception;

public class NoClientConnectedException extends BankException{
  public NoClientConnectedException(String reason) {
    super(reason);
  }

  public NoClientConnectedException() {
    this("There is any client connected");
  }
}
