package com.biblioteca.repository;

import com.biblioteca.model.User;
import com.biblioteca.exception.DatabaseException;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
  private List<User> users;

  public UserRepository() {
    this.users = new ArrayList<>();
  }

  /**
   * Salva utente
   *
   * @param user
   */
  public void save(User user) {
    if (user == null) {
      throw new DatabaseException("Errore: utente nullo non può essere salvato.");
    }
    users.add(user);
  }

  public List<User> findAll() {
    return users;
  }
}
