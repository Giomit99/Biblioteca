package com.biblioteca.controller;

import com.biblioteca.model.Book;
import com.biblioteca.model.Loan;
import com.biblioteca.model.User;
import com.biblioteca.repository.BookRepository;
import com.biblioteca.service.LoanListener;
import com.biblioteca.service.LoanService;
import com.biblioteca.util.Formatter;
import java.util.List;

/**
 * Controller per gestire i prestiti nella biblioteca.
 */
public class LoanController implements LoanListener {
  private LoanService service;
  private BookRepository bookRepository;
  private Formatter formatter;

  /**
   * Costruttore
   */
  public LoanController() {
    this.bookRepository = new BookRepository();
    this.service = new LoanService(this);
    this.formatter = new Formatter();
  }

  public void requestLoan(Loan loan) {
    service.processLoan(loan);
  }

  public LoanService getLoanService() {
    return service;
  }

  @Override
  public void loanProcessed(Loan loan) {
    System.out.println(formatter.format("Prestito confermato: "
            + loan.getBook().getTitle()
            + " a "
            + loan.getUser().getName()
            + " (Restituire entro: " + loan.getDueDate() + ")"));
  }

  /**
   * Metodo che mostra i prestiti scaduti
   * @param loans
   */
  public void showOverdueLoans(List<Loan> loans) {
    System.out.println(formatter.format("Prestiti scaduti:"));
    for (Loan loan : loans) {
      if (loan.isOverdue()) {
        System.out.println("- " + loan.getBook().getTitle() + " di " + loan.getUser().getName());
      }
    }
  }

  public void showAvailableBooks() {
    List<Book> books = bookRepository.findAll();
    System.out.println(formatter.format("Lista dei libri disponibili:"));
    for (Book book : books) {
      System.out.println("- " + book.getTitle());
    }
  }

  public void addBook(Book book) {
    bookRepository.save(book);
  }

  public void returnBook(User user, Book book) {
    service.returnBook(user, book);
  }

  public void showLoansNearExpiry(List<Loan> loans) {
    System.out.println(formatter.format("Prestiti prossimi alla scadenza:"));
    for (Loan loan : loans) {
      long daysLeft = loan.daysUntilDue();
      if (daysLeft >= 0 && daysLeft <= 3) {
        System.out.println("- " + loan.getBook().getTitle() + " preso da " + loan.getUser().getName() + " (scade tra " + daysLeft + " giorni)");
      }
    }
  }

  public void searchBooks(String query) {
    List<Book> results = bookRepository.searchByTitle(query);
    System.out.println(formatter.format("Risultati ricerca per: '" + query + "'"));
    if (results.isEmpty()) {
      System.out.println("Nessun libro trovato.");
    } else {
      for (Book book : results) {
        System.out.println("- " + book.getTitle());
      }
    }
  }
}
