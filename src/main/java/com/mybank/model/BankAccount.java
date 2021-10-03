package com.mybank.model;

import java.util.Objects;

import static com.mybank.utils.Utils.toHex;

public abstract class BankAccount {
  protected static long accountIdBase = 4845892L;
  protected String accountId;
  protected double balance;
  protected String wording;

  public BankAccount(double balance, String wording) {
    String id = "-" + (this.hashCode() + accountIdBase++);
    this.accountId = "A4F" + toHex(id).toUpperCase();
    this.balance = balance;
    this.wording = wording;
  }

  public static long getAccountIdBase() {
    return accountIdBase;
  }

  public String getWording() {
    return wording;
  }

  public double getBalance() {
    return balance;
  }

  public String getAccountId() {
    return accountId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BankAccount that = (BankAccount) o;
    return Double.compare(that.getBalance(), getBalance()) == 0 && Objects.equals(getAccountId(), that.getAccountId()) && Objects.equals(getWording(), that.getWording());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getAccountId(), getBalance(), getWording());
  }
}
