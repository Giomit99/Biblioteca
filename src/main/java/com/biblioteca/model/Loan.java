package com.biblioteca.model;

import java.time.LocalDate;

/**
 * Classe che rappresenta i prestiti dei libri agli utenti
 */
public class Loan {
  private Book book;
  private User user;
  private LocalDate startDate;
  private LocalDate dueDate;

  public Loan(Book book, User user) {
    this.book = book;
    this.user = user;
    this.startDate = LocalDate.now(); // data attuale
    this.dueDate = startDate.plusDays(30); // prestito valido 30 giorni
  }

  public Loan(Book book, User user, LocalDate customDueDate) {
    this.book = book;
    this.user = user;
    this.startDate = LocalDate.now();
    this.dueDate = customDueDate;
  }

  public Book getBook() {
    return book;
  }

  public User getUser() {
    return user;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public boolean isOverdue() {
    return LocalDate.now().isAfter(dueDate);
  }

  public long daysUntilDue() {
    return java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
  }
}
