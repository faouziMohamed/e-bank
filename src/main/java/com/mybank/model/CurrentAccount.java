package com.mybank.model;

public class CurrentAccount extends BankAccount {
  public CurrentAccount(int accountId, double balance, String wording) {
    super(accountId, AccountTypeEnum.CurrentAccount, balance, wording);
  }
}
