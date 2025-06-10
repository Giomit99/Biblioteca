package com.biblioteca.util;

/**
 * Formatter.
 */
public class Formatter {
  /**
   * Formato una string in maniera specifica.
   *
   * @param text testo da formattare
   *
   * @return testo formattato
   */
  public String format(String text) {
    return "*** " + text.toUpperCase() + " ***";
  }
}
