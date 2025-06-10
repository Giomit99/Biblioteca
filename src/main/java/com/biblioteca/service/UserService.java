package com.biblioteca.service;

import com.biblioteca.model.User;
import com.biblioteca.repository.UserRepository;
import java.util.List;

/**
 * Business logic utenti.
 */
public class UserService {
  private UserRepository userRepository;

  /**
   * Costruttore.
   *
   * @param userRepository repository utenti
   */
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Login utente.
   *
   * @param username username
   * @param password password
   *
   * @return utente
   */
  public User login(String username, String password) {
    for (User user : userRepository.findAll()) {
      if (user.getUsername().equals(username) && user.checkPassword(password)) {
        return user;
      }
    }
    return null; // Login fallito
  }

  /**
   * Registra utente.
   *
   * @param user utente
   */
  public void registerUser(User user) {
    userRepository.save(user);
    System.out.println("Utente registrato: " + user.getName());
  }

  /**
   * Lista di tutti gli utenti.
   *
   * @return lista utenti
   */
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
}
