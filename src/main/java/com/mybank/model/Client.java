package com.mybank.model;

import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class Client {
  private final TreeMap<String, SavingAccount> allSavingAccount =
    new TreeMap<>();
  private final TreeMap<String, CurrentAccount> allCurrentAccount =
    new TreeMap<>();
  private final String username;
  private final String password;
  private final String firstName;
  private String lastName;

  public Client(String username, String password, String firstName,
                String lastName,
                TreeMap<String, SavingAccount> allSavingAccount,
                TreeMap<String, CurrentAccount> allCurrentAccount) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;

    for (SavingAccount sva : allSavingAccount.values()) {
      this.allSavingAccount.put(sva.getWording(), sva);
    }

    for (CurrentAccount cra : allCurrentAccount.values()) {
      this.allCurrentAccount.put(cra.getWording(), cra);
    }
  }

  public Client(String username, String password, String firstName,
                String lastName, double balance) {
    this(username, password, firstName, lastName, new TreeMap<>(),
         new TreeMap<>());
    String[] allNames = getAllNames();
    String wording = allNames[ getIdName(allNames.length) ];
    addNewSavingAccount(balance, wording);
  }

  public Client(String username, String password, String firstName,
                String lastName) {
    this(username, password, firstName, lastName, 0.0);
  }

  private static int getIdName(int limit) {
    return ThreadLocalRandom.current().nextInt(0, limit);
  }

  private static double getRandomNumber(double limit) {
    return ThreadLocalRandom.current().nextDouble(0, limit);
  }

  public static Client getTestClient(String username, String password,
                                     int numSva, int numCra) {
    String[] firstNames = getListFirstNames();
    String[] familyNames = getListFamilyNames();

    String firstName = firstNames[ getIdName(firstNames.length) ];
    String familyName = familyNames[ getIdName(familyNames.length) ];

    Client c = new Client(username, password, firstName, familyName);
    if (numSva > 0) {
      AccountInterface acc = c::addNewSavingAccount;
      addAccount(acc, c, numSva);
    }
    if (numCra > 0) {
      AccountInterface acc = c::addNewCurrentAccount;
      addAccount(acc, c, numCra);
    }
    return c;
  }

  private static void addAccount(AccountInterface a, Client c, int numAccount) {
    String[] allNames = getAllNames();
    for (int i = 0; i < numAccount; i++) {
      double balance =
        getRandomNumber(Math.random() * getRandomNumber(54.6)) * 1000 * Math.random();
      String wording = allNames[ getIdName(allNames.length) ];
      a.addAccount(balance, wording);
    }
  }

  private static String[] getAllNames() {
    String[] first = getListFirstNames();
    String[] familyName = getListFamilyNames();

    String[] allNames = new String[ first.length + familyName.length ];
    System.arraycopy(first, 0, allNames, 0, first.length);
    System.arraycopy(familyName, 0, allNames, first.length, familyName.length);
    return allNames;
  }

  private static String[] getListFamilyNames() {
    return new String[]{ "Faouzi", "Mohamed", "Marwan", "Ayoub", "Silkhi",
      "Mouzaoir", "Adourahmane", "Malcolm", "Salim", "Mourchid", "Fateh",
      "Karmen", "Youssouf", "Karim", "Ragragui", "Azhar", "Ben Moussa",
      "Mourinho", "Souleyman", "Camara", "Naissoune", "Jarir", "Abd El-Hakim"
      , "Mofid", "Abdoulali", "Khalil", "Rachid", "John", "Snow", "Barry",
      "Allen", "Joe", "Moussa", "Tony", "Stark", "Steeve", "Rogers", "Romanov"
      , "West", "Paris", "Morgan", "Freeman", "Kennedy", "Donald", "Trump",
      "Khalifa", "James", "Said", "Ali", "Ramone", "Cisco", "Slade", "Wilson"
      , "Skywalker", "Palpatine", "Luke", "Lando", "Ray", "Kayloren", "Vader"
      , "Bob", "Patrick", "Chanfi", "Moulay Hassa", "Moulay Rachid", "Rachid"
      , "Hassan", "Clark", "Kent", "Jones", "Frank", "Ronaldo", "Faouzoudine" };
  }

  private static String[] getListFirstNames() {
    return new String[]{ "Maryam", "Dounia", "Dounya", "Batma", "Fatima " +
                                                                  "Zahra",
      "FatimaZahra", "Mohamed", "Abtal", "Moukrimat", "Nouhaila", "El-Mouden"
      , "Faouzi", "Chaimae", "Chaymae", "West", "Paris", "Morgan", "Freeman",
      "Kennedy", "Donald", "Trump", "Khalifa", "James", "Said", "Ali",
      "Ramone", "Cisco", "Slade", "Wilson", "Rebecca", "Sara", "Najma", "Oumy"
      , "Kara", "Fatima", "Maymouna", "Samir", "Mourchid", "Khawla", "Khawlat"
      , "Said", "Jessica", "Alex", "Denvers", "Avicii", "Snow", "Sambi",
      "Idriss", "Morgan", "Ali", "Mahmat", "Oumar", "Nioukousa", "Alpha",
      "Mamadou", "Ahmady", "Senoussi", "Haggar", "Halim", "Aneflous", "Salma"
      , "Sanima", "Dahilou", "Nasma", "Karima", "Fayad", "Ahmada", "Imane",
      "Nadia", "Yassem", "Joundan", "Aichat", "Saber", "Imam", "CJ", "Mze",
      "Asmae", "Nasra", "Haidar", "Nas", "Kenza", "Natacha", "Romanof",
      "Kassandra" };
  }

  private void addNewSavingAccount(double balance, String wording) {
    SavingAccount sva = new SavingAccount(balance, wording);
    sva.setCeiling(Math.random() * 54.076);
    sva.setInterestRate(Math.random() * 94.12);
    allSavingAccount.put(wording, sva);
  }

  private void addNewCurrentAccount(double balance, String wording) {
    CurrentAccount cra = new CurrentAccount(balance, wording);
    allCurrentAccount.put(wording, cra);
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public TreeMap<String, SavingAccount> getAllSavingAccount() {
    return allSavingAccount;
  }

  public TreeMap<String, CurrentAccount> getAllCurrentAccount() {
    return allCurrentAccount;
  }

  public TreeMap<String, BasicData> getBasicData(String accType) {

    TreeMap<String, BasicData> cl = new TreeMap<>();
    if (accType.equals("sva")) {
      allSavingAccount.forEach((k, v) -> cl.put(v.getAccountId(),
                                                new BasicData(v.getAccountId(), v.getWording())));
    }
    else if (accType.equals("cra")) {
      allCurrentAccount.forEach((k, v) -> cl.put(v.getAccountId(),
                                                 new BasicData(v.getAccountId(), v.getWording())));
    }
    return cl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (o == null || getClass() != o.getClass()) { return false; }

    Client client = (Client) o;

    if (getUsername() != null ? !getUsername().equals(client.getUsername()) :
        client.getUsername() != null) {
      return false;
    }
    if (getPassword() != null ? !getPassword().equals(client.getPassword()) :
        client.getPassword() != null) {
      return false;
    }
    if (getFirstName() != null ?
        !getFirstName().equals(client.getFirstName()) :
        client.getFirstName() != null) {
      return false;
    }
    if (getLastName() != null ? !getLastName().equals(client.getLastName()) :
        client.getLastName() != null) {
      return false;
    }
    if (getAllSavingAccount() != null ?
        !getAllSavingAccount().equals(client.getAllSavingAccount()) :
        client.getAllSavingAccount() != null) {
      return false;
    }
    return getAllCurrentAccount() != null ?
           getAllCurrentAccount().equals(client.getAllCurrentAccount()) :
           client.getAllCurrentAccount() == null;
  }

  @Override
  public int hashCode() {
    int result = getUsername() != null ? getUsername().hashCode() : 0;
    result = 31 * result + (getPassword() != null ? getPassword().hashCode()
                                                  : 0);
    result = 31 * result + (getFirstName() != null ?
                            getFirstName().hashCode() : 0);
    result = 31 * result + (getLastName() != null ? getLastName().hashCode()
                                                  : 0);
    result = 31 * result + (getAllSavingAccount() != null ?
                            getAllSavingAccount().hashCode() : 0);
    result = 31 * result + (getAllCurrentAccount() != null ?
                            getAllCurrentAccount().hashCode() : 0);
    return result;
  }

  private interface AccountInterface {
    void addAccount(double balance, String wording);
  }

  public static class BasicData {
    public String id;
    public String wording;

    public BasicData(String id, String wd) {
      this.id = id;
      this.wording = wd;
    }
  }

}
