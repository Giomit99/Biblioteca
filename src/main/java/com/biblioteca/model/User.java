package com.biblioteca.model;

/**
 * Classe pojo che rappresenta gli utenti.
 */
public class User {
  private String name;
  private String username;
  private String password;

  /**
   * Costruttore dell'utente.
   *
   * @param name nome utente
   * @param username username utente
   * @param password password utente
   */
  public User(String name, String username, String password) {
    this.name = name;
    this.username = username;
    this.password = password;
  }

  /**
   * Metodo nome utente.
   *
   * @return ritorna il nome dell'utente
   */
  public String getName() {
    return name;
  }

  /**
   * Metodo username.
   *
   * @return ritorna lo username dell'utente
   */
  public String getUsername() {
    return username;
  }

  /**
   * Verifica la password.
   *
   * @param password password da validare
   *
   * @return boleano che è true se la password è uguale
   */
  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }
}
