package com.mybank.model;

public abstract class BankAccount {
  protected int accountId;
  protected AccountTypeEnum accountType;
  protected double balance;
  protected String wording;

  public BankAccount(int accountId, AccountTypeEnum accountType, double balance
    , String wording) {
    this.accountId = accountId;
    this.accountType = accountType;
    this.balance = balance;
    this.wording = wording;
  }

  public int getAccountId() {
    return accountId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (o == null || getClass() != o.getClass()) { return false; }

    BankAccount that = (BankAccount) o;

    if (getAccountId() != that.getAccountId()) { return false; }
    if (Double.compare(that.getBalance(), getBalance()) != 0) {
      return false;
    }
    if (getAccountType() != that.getAccountType()) { return false; }
    return getWording().equals(that.getWording());
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = getAccountId();
    result = 31 * result + getAccountType().hashCode();
    temp = Double.doubleToLongBits(getBalance());
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + getWording().hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "BankAccount{" +
             "accountId=" + accountId +
             ", accountType=" + accountType +
             ", sold=" + balance +
             ", wording='" + wording + '\'' +
             '}';
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public AccountTypeEnum getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountTypeEnum accountType) {
    this.accountType = accountType;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public String getWording() {
    return wording;
  }

  public void setWording(String wording) {
    this.wording = wording;
  }
}
