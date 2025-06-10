package com.biblioteca.exception;

/**
 * Eccezione custom per repository.
 */
public class DatabaseException extends RuntimeException {
  /**
   * Eccezione specifica per database.
   *
   * @param message messaggio di errore
   */
  public DatabaseException(String message) {
    super(message);
  }
}
