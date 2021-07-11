package com.mybank.model;

import com.mybank.exception.ClientNotFoundException;
import com.mybank.exception.PasswordErrorException;

import java.util.HashMap;

public class BankService {
  private static final BankService theBank = new BankService();
  private static HashMap<String, Client> clients;

  private BankService() {
    clients = new HashMap<>();
    Client[] arr = {
      new Client("faouzi", "12345", "Mohamed", "Faouzi", 14_046),
      new Client("khalid", "6789a", "Khalid", "Housni", 154_900),
      new Client("marc", "bcdef", "Marc", "Dave", 16_846),
      new Client("fsk21", "password", "Jaafar", "Abuchabaka", 269_046),
      new Client("tekashi", "fb/fzmhd", "Diplo", "Killer", 14_385_000),
      new Client("hero99", "gh/faouzimohamed", "Allen", "Barry", 645_500),
    };

    for (Client c : arr) {
      clients.put(c.getUsername(), c);
    }
  }

  public static BankService getInstance() {
    return theBank;
  }

  public Client findClient(String userName, String password) throws com.mybank.exception.BankException {
    if (clients.containsKey(userName)) {
      Client c = clients.get(userName);
      if (password.equals(c.getPassword())) {
        return c;
      }
      throw new PasswordErrorException("Incorrect Password");
    }
    throw new ClientNotFoundException("Client not found");
  }

  public HashMap<String, Client> allClients() {
    return clients;
  }

}
