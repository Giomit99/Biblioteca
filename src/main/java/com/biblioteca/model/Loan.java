package com.biblioteca.model;

import java.time.LocalDate;

/**
 * Classe che rappresenta i prestiti dei libri agli utenti.
 */
public class Loan {
  private Book book;
  private User user;
  private LocalDate startDate;
  private LocalDate dueDate;

  /**
   * Costruttore con default dei numero di giorni di prestito.
   *
   * @param book libro da prestare
   *
   * @param user utente che richiede il prestito
   */
  public Loan(Book book, User user) {
    this.book = book;
    this.user = user;
    this.startDate = LocalDate.now(); // data attuale
    this.dueDate = startDate.plusDays(30); // prestito valido 30 giorni
  }

  /**
   * Costruttore che permette di maneggiare i giorni di prestito.
   *
   * @param book libro
   * @param user utente
   * @param customDueDate data scadenza prestito
   */
  public Loan(Book book, User user, LocalDate customDueDate) {
    this.book = book;
    this.user = user;
    this.startDate = LocalDate.now();
    this.dueDate = customDueDate;
  }

  /**
   * Ritorna il libro.
   *
   * @return libro
   */
  public Book getBook() {
    return book;
  }

  /**
   * Ritorna l'utente.
   *
   * @return user
   */
  public User getUser() {
    return user;
  }

  /**
   * Data prestito.
   *
   * @return data
   */
  public LocalDate getStartDate() {
    return startDate;
  }

  /**
   * Data fine prestito.
   *
   * @return data
   */
  public LocalDate getDueDate() {
    return dueDate;
  }

  /**
   * Verifica se il prestito è scaduto.
   *
   * @return boleano che è true se il prestito è scaduto
   */
  public boolean isOverdue() {
    return LocalDate.now().isAfter(dueDate);
  }

  /**
   * Ritorna il numero di giorno di un libro ancora in prestito.
   *
   * @return numero di giorni rimanenti
   */
  public long daysUntilDue() {
    return java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
  }
}
