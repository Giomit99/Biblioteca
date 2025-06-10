package com.biblioteca.controller;

import com.biblioteca.model.User;
import com.biblioteca.repository.UserRepository;
import com.biblioteca.service.UserService;
import java.util.List;

/**
 * Controller utenti.
 */
public class UserController {
  private UserService service;
  private static UserRepository userRepository = new UserRepository();
  private User authenticatedUser = null;

  /**
   * Costruttore.
   */
  public UserController() {
    service = new UserService(userRepository);
  }

  /**
   * Mostra tutti gli utenti.
   */
  public void showAllUsers() {
    List<User> users = service.getAllUsers();
    System.out.println("Lista utenti registrati:");
    for (User user : users) {
      System.out.println("- " + user.getName());
    }
  }

  /**
   * Regista un nuovo utente.
   *
   * @param name nome
   * @param username username
   * @param password password
   */
  public void registerNewUser(String name, String username, String password) {
    User user = new User(name, username, password);
    service.registerUser(user);
  }

  /**
   * Login di un utente.
   *
   * @param username username
   * @param password password
   *
   * @return accesso contentito
   */
  public boolean login(String username, String password) {
    User user = service.login(username, password);
    if (user != null) {
      authenticatedUser = user;
      System.out.println("Login effettuato con successo! Benvenuto, " + user.getName());
      return true;
    } else {
      System.out.println("Login fallito. Username o password errati.");
      return false;
    }
  }

  /**
   * Ritorna utente autentificato.
   *
   * @return utente
   */
  public User getAuthenticatedUser() {
    return authenticatedUser;
  }
}
