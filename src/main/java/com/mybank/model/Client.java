package com.mybank.model;

public class Client {
  private final String username;
  private final String password;
  private final String firstName;
  private final String name;
  private final double balance;

  public Client(String username, String password, String firstName,
    String name, double balance) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.name = name;
    this.balance = balance;
  }

  public String getUsername() {
    return username;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public double getBalance() {
    return balance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (o == null || getClass() != o.getClass()) { return false; }

    Client client = (Client) o;

    if (Double.compare(client.getBalance(), getBalance()) != 0) {
      return false;
    }
    if (!getUsername().equals(client.getUsername())) { return false; }
    if (!getFirstName().equals(client.getFirstName())) { return false; }
    if (!getName().equals(client.getName())) { return false; }
    return getPassword().equals(client.getPassword());
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = getUsername().hashCode();
    result = 31 * result + getFirstName().hashCode();
    result = 31 * result + getName().hashCode();
    result = 31 * result + getPassword().hashCode();
    temp = Double.doubleToLongBits(getBalance());
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}
