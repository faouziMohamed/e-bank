package com.mabanque.beans;

public class Client {
  private String name;
  private String password;

  public Client(String name, String password) {
    this.name = name;
    this.password = password;
  }

  @Override
  public int hashCode() {
    int result = getName().hashCode();
    result = 31 * result + getPassword().hashCode();
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Client client = (Client) o;

    if (!getName().equals(client.getName())) return false;
    return getPassword().equals(client.getPassword());
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setName(String name) {
    this.name = name;
  }
}
