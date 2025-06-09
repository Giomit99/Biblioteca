package com.biblioteca.exception;

public class DatabaseException extends RuntimeException {
  /**
   * Eccezione specifica per database
   * @param message
   */
  public DatabaseException(String message) {
    super(message);
  }
}
