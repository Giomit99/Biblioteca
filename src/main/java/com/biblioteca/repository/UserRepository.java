package com.biblioteca.repository;

import com.biblioteca.model.User;
import com.biblioteca.exception.DatabaseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Insieme degli utenti
 */
public class UserRepository {
  private List<User> users;

  /**
   * Costruttore che istanzia una ArrayList
   */
  public UserRepository() {
    this.users = new ArrayList<>();
  }

  /**
   * Salva utente
   *
   * @param user utente da salvare
   */
  public void save(User user) {
    if (user == null) {
      throw new DatabaseException("Errore: utente nullo non può essere salvato.");
    }
    users.add(user);
  }

  /**
   * Ritorna tutti gli utenti
   * @return lista degli utenti
   */
  public List<User> findAll() {
    return users;
  }
}
