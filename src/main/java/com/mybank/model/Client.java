package com.mybank.model;

import java.util.TreeMap;

public class Client {
  private String username;
  private String password;
  private String firstName;
  private String name;
  private TreeMap<String, SavingAccount> allSavingAccount;
  private TreeMap<String, CurrentAccount> allCurrentAccount;

  public Client(String username, String password, String firstName,
                String name,
                TreeMap<String, SavingAccount> allSavingAccount,
                TreeMap<String, CurrentAccount> allCurrentAccount) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.name = name;
    this.allSavingAccount = allSavingAccount;
    this.allCurrentAccount = allCurrentAccount;
  }

  public Client(String username, String password, String firstName,
                String name) {
    this(username, password, firstName, name, new TreeMap<>(), new TreeMap<>());
  }

  public Client(String username, String password, String firstName,
                String name, double balance) {
    this(username, password, firstName, name, new TreeMap<>(), new TreeMap<>());
    SavingAccount sva = new SavingAccount(1, balance, firstName);
    allSavingAccount.put(sva.getWording(), sva);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TreeMap<String, SavingAccount> getAllSavingAccount() {
    return allSavingAccount;
  }

  public void setAllSavingAccount(TreeMap<String, SavingAccount> allSavingAccount) {
    this.allSavingAccount = allSavingAccount;
  }

  public TreeMap<String, CurrentAccount> getAllCurrentAccount() {
    return allCurrentAccount;
  }

  public void setAllCurrentAccount(TreeMap<String, CurrentAccount> allCurrentAccount) {
    this.allCurrentAccount = allCurrentAccount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (o == null || getClass() != o.getClass()) { return false; }

    Client client = (Client) o;

    if (!getUsername().equals(client.getUsername())) { return false; }
    if (!getPassword().equals(client.getPassword())) { return false; }
    if (!getFirstName().equals(client.getFirstName())) { return false; }
    if (!getName().equals(client.getName())) { return false; }
    if (!getAllSavingAccount().equals(client.getAllSavingAccount())) {
      return false;
    }
    return getAllCurrentAccount().equals(client.getAllCurrentAccount());
  }

  @Override
  public int hashCode() {
    int result = getUsername().hashCode();
    result = 31 * result + getPassword().hashCode();
    result = 31 * result + getFirstName().hashCode();
    result = 31 * result + getName().hashCode();
    result = 31 * result + getAllSavingAccount().hashCode();
    result = 31 * result + getAllCurrentAccount().hashCode();
    return result;
  }
}
