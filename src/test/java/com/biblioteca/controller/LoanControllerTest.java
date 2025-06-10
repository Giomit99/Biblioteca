package com.biblioteca.controller;

import com.biblioteca.model.Book;
import com.biblioteca.model.Loan;
import com.biblioteca.model.User;
import org.junit.jupiter.api.AfterEach;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoanControllerTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  void restoreStreams() {
    System.setOut(originalOut);
  }

  @Test
  void testShowLoansNearExpiry_printsHeader() {
    // Arrange
    LoanController controller = new LoanController();
    Book book = new Book("Il Nome della Rosa");
    User user = new User("ugo123", "Ugo", "ugo@email.com");

    // Set due date to 2 days from now (within "near expiry" window)
    Loan loan = new Loan(book, user, LocalDate.now().plusDays(2));
    controller.showLoansNearExpiry(Collections.singletonList(loan));

    // Act
    String output = outContent.toString();
    System.out.println("DEBUG OUTPUT:\n" + output);
    // Assert
    assertTrue(output.toLowerCase().contains("prestiti prossimi alla scadenza:"),
            "Il metodo dovrebbe stampare l'intestazione dei prestiti prossimi alla scadenza");
  }
}
