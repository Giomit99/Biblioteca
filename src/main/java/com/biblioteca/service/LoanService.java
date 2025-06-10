package com.biblioteca.service;

import com.biblioteca.model.Loan;
import com.biblioteca.model.Book;
import com.biblioteca.model.User;
import com.biblioteca.util.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Business logic prestiti
 */
public class LoanService {
  private LoanListener listener;
  private List<Loan> activeLoans;
  private List<Loan> historicalLoans;

  /**
   * Costruttore
   *
   * @param listener interfaccia
   */
  public LoanService(LoanListener listener) {
    this.listener = listener;
    this.activeLoans = new ArrayList<>();
    this.historicalLoans = new ArrayList<>();
  }

  /**
   * Gestione di un prestito.
   *
   * @param loan prestito
   */
  public void processLoan(Loan loan) {
    Book book = loan.getBook();
    User user = loan.getUser();

    if (hasLoanedBook(user, book)) {
      String msg= "Utente "+ user.getName()+ " ha già in prestito il libro: "+ book.getTitle();
      Logger.log(msg);
      return;
    }

    if (book.isLoaned()) {
      book.addReservation(user);
      String message = "Libro già prestato. Utente aggiunto alla lista di attesa";
      Logger.log(message);
    }
    else {
      book.setLoaned(true);
      activeLoans.add(loan);
      Logger.log("Processando prestito per: " + book.getTitle());
      if (listener != null)
        listener.loanProcessed(loan);
    }
  }

  /**
   * Controlla se un determinato utente ha in presisto un determinato libro.
   *
   * @param user utente
   * @param book libro
   *
   * @return ritorna true se c'è l'ha
   */
  private boolean hasLoanedBook(User user, Book book) {
    for (Loan loan : activeLoans) {
      if (loan.getUser().getName().equals(user.getName()) && loan.getBook().getTitle().equals(book.getTitle())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Quantità prestiti attivi.
   *
   * @return numero prestiti
   */
  public int getActiveLoans() {
    return activeLoans.size();
  }

  /**
   * Lista dei prestiti attivi.
   *
   * @return lista prestiti
   */
  public List<Loan> getAllLoans() {
    return activeLoans;
  }

  /**
   * Ritorna libro.
   *
   * @param user utente
   * @param book libro
   */
  public void returnBook(User user, Book book) {
    Loan loanToRemove = null;
    for (Loan loan : activeLoans) {
      if (loan.getUser().getName().equals(user.getName())
              && loan.getBook().getTitle().equals(book.getTitle())) {
        loanToRemove = loan;
        break;
      }
    }

    if (loanToRemove != null) {
      activeLoans.remove(loanToRemove);
      Logger.log("Libro restituito: " + book.getTitle() + " da " + user.getName());

      if (book.hasReservations()) {
        // Se c'è qualcuno in coda ➔ nuovo prestito automatico
        User nextUser = book.getNextReservation();
        Loan newLoan = new Loan(book, nextUser);
        activeLoans.add(newLoan);
        if (listener != null) {
          listener.loanProcessed(newLoan);
        }

        Logger.log("Libro " + book.getTitle() + " prestato automaticamente a " + nextUser.getName());
      } else {
        // Nessuno in coda ➔ libro torna libero
        book.setLoaned(false);
        Logger.log("Libro " + book.getTitle() + " è ora disponibile.");
      }
    } else {
      Logger.log("Tentativo di restituzione fallito: Nessun prestito attivo trovato per " + user.getName() + " con il libro " + book.getTitle());
    }
  }

  /**
   * Numero totale dei prestiti avvenuti.
   *
   * @return numero
   */
  public int getTotalLoans() {
    return historicalLoans.size();
  }

  /**
   * Libro che è stato preso in prestito più volte.
   *
   * @return libro
   */
  public Book getMostLoanedBook() {
    Map<String, Integer> bookCount = new HashMap<>();
    for (Loan loan : historicalLoans) {
      String title = loan.getBook().getTitle();
      bookCount.put(title, bookCount.getOrDefault(title, 0) + 1);
    }
    String mostLoanedTitle = null;
    int max = 0;
    for (Map.Entry<String, Integer> entry : bookCount.entrySet()) {
      if (entry.getValue() > max) {
        mostLoanedTitle = entry.getKey();
        max = entry.getValue();
      }
    }
    if (mostLoanedTitle == null) {
      return null;
    }
    // Ritorno un "Book" fittizio con solo il titolo, giusto da stampare
    return new Book(mostLoanedTitle);
  }

  /**
   * L'utente che ha fatto più prestiti.
   *
   * @return utente
   */
  public User getUserWithMostLoans() {
    Map<String, Integer> userCount = new HashMap<>();
    for (Loan loan : historicalLoans) {
      String username = loan.getUser().getUsername();
      userCount.put(username, userCount.getOrDefault(username, 0) + 1);
    }
    String topUser = null;
    int max = 0;
    for (Map.Entry<String, Integer> entry : userCount.entrySet()) {
      if (entry.getValue() > max) {
        topUser = entry.getKey();
        max = entry.getValue();
      }
    }
    if (topUser == null) {
      return null;
    }
    return new User(topUser, topUser, ""); // Costruisco un User fittizio solo con username
  }

  /**
   * Metodo che rimuove i prestiti scacuti.
   *
   * @return il numero di presisti rimossi
   */
  public int cleanOldLoans() {
    List<Loan> toRemove = new ArrayList<>();
    for (Loan loan : activeLoans) {
      if (loan.isOverdue() && loan.getDueDate().plusDays(30).isBefore(java.time.LocalDate.now())) {
        toRemove.add(loan);
      }
    }
    activeLoans.removeAll(toRemove);
    return toRemove.size(); // ritorna quanti prestiti sono stati puliti
  }

  /**
   * Aggiunge prestito al l'insieme dei prestiti avvenuti.
   *
   * @param loan prestito
   */
  public void addHistoricalLoan(Loan loan) {
    historicalLoans.add(loan);
  }
}