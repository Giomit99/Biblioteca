package com.biblioteca.model;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Pojo che rappresenta un libro.
 */
public class Book {
  private String title;
  private boolean isLoaned;
  private Queue<User> reservationQueue;

  /**
   * Costruttore del libro.
   *
   * @param title titolo del libro
   */
  public Book(String title) {
    this.reservationQueue = new LinkedList<>();
    this.title = title;
    this.isLoaned = false;
  }

  /**
   * Ritorna il titolo del libro.
   *
   * @return titolo
   */
  public String getTitle() {
    return title;
  }

  /**
   * Ritorna un boleano che se è true indica che il libro è già in prestito.
   *
   * @return boleano
   */
  public boolean isLoaned() {
    return isLoaned;
  }

  /**
   * Motodo che permette di settare il prestito a un utente.
   *
   * @param loaned boleano
   */
  public void setLoaned(boolean loaned) {
    isLoaned = loaned;
  }

  /**
   * Aggiunge a una coda l'utente che vuole prendere in prestito il libro.
   *
   * @param user utente
   */
  public void addReservation(User user) {
    reservationQueue.add(user);
  }

  /**
   * Metodo che ritorna il primo che ha prenotato il libro.
   *
   * @return utente
   */
  public User getNextReservation() {
    return reservationQueue.poll();
  }

  /**
   * Ritorna un boleano che se è true indica che c'è almeno un utente che lo vuole in prestito.
   *
   * @return boleano che torna true se ci sono utenti in coda per un determinato libro
   */
  public boolean hasReservations() {
    return !reservationQueue.isEmpty();
  }
}
