package com.biblioteca.util;

/**
 * Classe logger per stampa.
 */
public class Logger {
  /**
   * Metodo statico per stampa il messaggio passatogli come parametro.
   *
   * @param message message in output
   */
  public static void log(String message) {
    System.out.println("LOG: " + message);
  }
}
