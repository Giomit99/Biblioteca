package com.biblioteca.service;

import com.biblioteca.model.Loan;

/**
 * Interfaccia.
 */
public interface LoanListener {
  /**
   * Metodo.
   *
   * @param loan prestito
   */
  void loanProcessed(Loan loan);
}
