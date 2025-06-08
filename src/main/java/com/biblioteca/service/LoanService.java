package com.biblioteca.service;

import com.biblioteca.controller.LoanController;
import com.biblioteca.model.Loan;
import com.biblioteca.model.Book;
import com.biblioteca.model.User;
import com.biblioteca.util.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoanService {
    private LoanController controller;
    private Logger logger;
    private List<Loan> activeLoans;
    private List<Loan> historicalLoans;

    public LoanService(LoanController controller) {
        this.activeLoans = new ArrayList<>();
        this.historicalLoans = new ArrayList<>();
        this.controller = controller;
        this.logger = new Logger(this);
    }

    public void processLoan(Loan loan) {
        Book book = loan.getBook();
        User user = loan.getUser();

        if (hasLoanedBook(user, book)) {
            logger.log("Utente " + user.getName() + " ha già in prestito il libro: " + book.getTitle() + ". Prestito negato.");
            return;
        }

        if (book.isLoaned()) {
            book.addReservation(user);
            logger.log("Libro già prestato: " + book.getTitle() + ". Utente " + user.getName() + " aggiunto alla lista di attesa.");
        } else {
            book.setLoaned(true);
            activeLoans.add(loan);
            logger.log("Processando prestito per: " + book.getTitle());
            controller.loanProcessed(loan);
        }
    }

    private boolean hasLoanedBook(User user, Book book) {
        for (Loan loan : activeLoans) {
            if (loan.getUser().getName().equals(user.getName()) && loan.getBook().getTitle().equals(book.getTitle())) {
                return true;
            }
        }
        return false;
    }

    public int getActiveLoans() {
        return activeLoans.size();
    }

    public List<Loan> getAllLoans() {
        return activeLoans;
    }

    /**
     * Ritorna libro
     * @param user
     * @param book
     */
    public void returnBook(User user, Book book) {
        Loan loanToRemove = null;
        for (Loan loan : activeLoans) {
            if (loan.getUser().getName().equals(user.getName()) &&
                    loan.getBook().getTitle().equals(book.getTitle())) {
                loanToRemove = loan;
                break;
            }
        }

        if (loanToRemove != null) {
            activeLoans.remove(loanToRemove);
            logger.log("Libro restituito: " + book.getTitle() + " da " + user.getName());

            if (book.hasReservations()) {
                // Se c'è qualcuno in coda ➔ nuovo prestito automatico
                User nextUser = book.getNextReservation();
                Loan newLoan = new Loan(book, nextUser);
                activeLoans.add(newLoan);
                controller.loanProcessed(newLoan);
                logger.log("Libro " + book.getTitle() + " prestato automaticamente a " + nextUser.getName());
            } else {
                // Nessuno in coda ➔ libro torna libero
                book.setLoaned(false);
                logger.log("Libro " + book.getTitle() + " è ora disponibile.");
            }
        } else {
            logger.log("Tentativo di restituzione fallito: Nessun prestito attivo trovato per " + user.getName() + " con il libro " + book.getTitle());
        }
    }

    public int getTotalLoans() {
        return historicalLoans.size();
    }

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

    public void addHistoricalLoan(Loan loan) {
        historicalLoans.add(loan);
    }
}