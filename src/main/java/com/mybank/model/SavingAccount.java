package com.mybank.model;

public class SavingAccount extends BankAccount {
  private double interestRate;
  private double ceiling;

  public SavingAccount(double balance, String wording) {
    super(balance, wording);
  }

  public double getInterestRate() {
    return interestRate;
  }

  public void setInterestRate(double interestRate) {
    this.interestRate = interestRate;
  }

  public double getCeiling() {
    return ceiling;
  }

  public void setCeiling(double ceiling) {
    this.ceiling = ceiling;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (o == null || getClass() != o.getClass()) { return false; }
    if (!super.equals(o)) { return false; }

    SavingAccount that = (SavingAccount) o;

    if (Double.compare(that.getInterestRate(), getInterestRate()) != 0) {
      return false;
    }
    return Double.compare(that.getCeiling(), getCeiling()) == 0;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    long temp;
    temp = Double.doubleToLongBits(getInterestRate());
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(getCeiling());
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}
