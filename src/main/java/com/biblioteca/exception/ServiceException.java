package com.biblioteca.exception;

/**
 * Eccezione specifica business logic.
 */
public class ServiceException extends RuntimeException {
  /**
   * Costruttore.
   *
   * @param message messaggio d'errore
   */
  public ServiceException(String message) {
    super(message);
  }
}
