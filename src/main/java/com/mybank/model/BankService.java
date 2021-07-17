package com.mybank.model;

import com.mybank.exception.ClientNotFoundException;
import com.mybank.exception.PasswordErrorException;

import java.util.TreeMap;

public class BankService {
  private static final BankService theBank = new BankService();
  private static TreeMap<String, Client> allClients;

  private BankService() {
    allClients = new TreeMap<>();
    Client[] arr = {
      Client.getTestClient("faouzi", "12345", 9, 2),
      Client.getTestClient("fsk", "67890", 15, 4),
      Client.getTestClient("hadi", "password", 11, 9),
      Client.getTestClient("douze", "linux", 3, 5),
      Client.getTestClient("fsk21", "12345", 0, 12),
      Client.getTestClient("mohamed", "faouzi", 6, 41),
      Client.getTestClient("admin", "admin", 21, 9),
      Client.getTestClient("guest", "pass", 6, 7),
      Client.getTestClient("fawzi", "math", 4, 3)
    };

    for (Client c : arr) {
      allClients.put(c.getUsername(), c);
    }
  }

  public static BankService getInstance() {
    return theBank;
  }

  public Client findClient(String userName, String password) throws com.mybank.exception.BankException {
    if (allClients.containsKey(userName)) {
      Client c = allClients.get(userName);
      if (password.equals(c.getPassword())) {
        return c;
      }
      throw new PasswordErrorException("Incorrect Password");
    }
    throw new ClientNotFoundException("Client not found");
  }

  public TreeMap<String, Client> getAllClients() {
    return allClients;
  }
}
