package com.biblioteca.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.biblioteca.controller.LoanController;
import com.biblioteca.model.Book;
import com.biblioteca.model.Loan;
import com.biblioteca.model.User;
import org.junit.jupiter.api.Test;

/**
 * Classe di test che testa la business logic di LoanService.
 */
public class LoanServiceTest {

  private class DummyController extends LoanController {
    @Override
    public void loanProcessed(Loan loan) {
    }
  }

  @Test
  public void testLoanWhenUserAlreadyHasBook() {
    DummyController controller = new DummyController();
    LoanService service = new LoanService(controller);

    Book book = new Book("1984");
    User user = new User("Anna", "anna", "anna");

    Loan loan = new Loan(book, user);
    service.processLoan(loan);
    service.processLoan(loan);
  }

  @Test
  public void testLoanWhenBookAlreadyLoanedByOthers() {
    DummyController controller = new DummyController();
    LoanService service = new LoanService(controller);

    Book book = new Book("Brave New World");
    book.setLoaned(true);

    User user = new User("Luca", "luca", "luca");
    Loan loan = new Loan(book, user);

    service.processLoan(loan);
    assertTrue(book.hasReservations());
  }

  @Test
  public void testLoanWhenBookIsAvailable() {
    DummyController controller = new DummyController();
    LoanService service = new LoanService(controller);

    Book book = new Book("Siddhartha");
    User user = new User("Marco", "marco", "marco");
    Loan loan = new Loan(book, user);

    service.processLoan(loan);

    assertTrue(book.isLoaned());
  }
}
