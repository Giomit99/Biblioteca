package com.biblioteca.model;

public class User {
  private String name;
  private String username;
  private String password;

  /**
   * Costruttore dell'utente
   *
   * @param name
   * @param username
   * @param password
   */
  public User(String name, String username, String password) {
    this.name = name;
    this.username = username;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public String getUsername() {
    return username;
  }

  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }
}
