package com.mybank.model;

public abstract class BankAccount {
  protected static long accountIdBase = 4845892L;
  protected long accountId;
  protected double balance;
  protected String wording;

  public BankAccount(double balance
    , String wording) {
    this.accountId = this.hashCode() + accountIdBase++;
    this.balance = balance;
    this.wording = wording;
  }

  public String getWording() {
    return wording;
  }

  public double getBalance() {
    return balance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (o == null || getClass() != o.getClass()) { return false; }

    BankAccount that = (BankAccount) o;

    if (accountId != that.accountId) { return false; }
    if (Double.compare(that.getBalance(), getBalance()) != 0) {
      return false;
    }
    return getWording() != null ? getWording().equals(that.getWording()) :
             that.getWording() == null;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = (int) (accountId ^ (accountId >>> 32));
    temp = Double.doubleToLongBits(getBalance());
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + (getWording() != null ? getWording().hashCode() : 0);
    return result;
  }
}
