package com.mabanque.beans;

import java.util.ArrayList;

public class BanqueService {
  private static ArrayList<Client> clients;

  public BanqueService(Client client) {
    this.clients = clients;
  }

  public static Client findClient(String login, String password) {
    Client client = new Client(login, password);
    for (Client c : clients) {
      if (c.equals(client)) {
        return c;
      }
    }
    return null;
  }

  public ArrayList<Client> getClients() {
    return clients;
  }

  public void setClients(Client client) {
    this.clients.add(client);
  }
}
