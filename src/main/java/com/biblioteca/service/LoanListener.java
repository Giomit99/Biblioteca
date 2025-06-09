package com.biblioteca.service;

import com.biblioteca.model.Loan;

public interface LoanListener {
  void loanProcessed(Loan loan);
}
