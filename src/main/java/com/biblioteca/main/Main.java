package com.biblioteca.main;

import com.biblioteca.controller.LoanController;
import com.biblioteca.controller.UserController;
import com.biblioteca.model.Book;
import com.biblioteca.model.Loan;
import com.biblioteca.model.User;
import com.biblioteca.service.LoanService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * Main principale
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Sistema Biblioteca Avanzata ===");

        // --- Gestione utenti ---
        UserController userController = new UserController();
        userController.registerNewUser("Mario Rossi", "mario", "1234");
        userController.registerNewUser("Luisa Bianchi", "luisa", "abcd");
        userController.registerNewUser("Giacomo Verdi", "giacomo", "pass");

        // --- Login utente ---
        System.out.println("Effettua il login per continuare:");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        boolean loggedIn = userController.login(username, password);

        if (!loggedIn) {
            System.out.println("Accesso negato. Termino il programma.");
            return;
        }

        User loggedUser = userController.getAuthenticatedUser();

        // --- Gestione libri ---
        LoanController loanController = new LoanController();
        Book book1 = new Book("Il Signore degli Anelli");
        Book book2 = new Book("Harry Potter e la Pietra Filosofale");
        Book book3 = new Book("Cronache di Narnia");

        loanController.addBook(book1);
        loanController.addBook(book2);
        loanController.addBook(book3);

        System.out.println();
        loanController.showAvailableBooks();
        System.out.println();

        // --- Prestiti ---
        Loan loan1 = new Loan(book1, loggedUser);
        loanController.requestLoan(loan1);

        User user2 = new User("Luisa Bianchi", "luisa", "abcd");
        Loan loan2 = new Loan(book1, user2); // Prenotazione
        loanController.requestLoan(loan2);

        User user3 = new User("Giacomo Verdi", "giacomo", "pass");
        Loan loan3 = new Loan(book1, user3); // Prenotazione
        loanController.requestLoan(loan3);

        Loan loan4 = new Loan(book2, user3);
        loanController.requestLoan(loan4);

        System.out.println();

        List<Loan> prestiti = new ArrayList<>();
        prestiti.add(loan1);
        prestiti.add(loan4);

        Loan oldLoan = new Loan(new Book("Vecchio Libro"), loggedUser);
        // Forziamo manualmente date vecchie:
        try {
            java.lang.reflect.Field startDateField = Loan.class.getDeclaredField("startDate");
            java.lang.reflect.Field dueDateField = Loan.class.getDeclaredField("dueDate");
            startDateField.setAccessible(true);
            dueDateField.setAccessible(true);
            startDateField.set(oldLoan, LocalDate.now().minusDays(70));
            dueDateField.set(oldLoan, LocalDate.now().minusDays(40));
        } catch (Exception e) {
            e.printStackTrace();
        }

        prestiti.add(oldLoan);
        loanController.getLoanService().addHistoricalLoan(oldLoan);

        loanController.showOverdueLoans(prestiti);

        System.out.println();

        System.out.println("=== Pulizia Prestiti Obsoleti ===");
        int removed = loanController.getLoanService().cleanOldLoans();
        System.out.println("Prestiti rimossi: " + removed);

        System.out.println();

        System.out.println("=== Notifica prestiti in scadenza ===");
        loanController.showLoansNearExpiry(prestiti);

        System.out.println();

        System.out.println("=== Statistiche Biblioteca ===");
        LoanService loanService = loanController.getLoanService();

        System.out.println("- Prestiti totali effettuati: " + loanService.getTotalLoans());

        Book mostLoanedBook = loanService.getMostLoanedBook();
        if (mostLoanedBook != null) {
            System.out.println("- Libro più prestato: " + mostLoanedBook.getTitle());
        } else {
            System.out.println("- Nessun libro prestato ancora.");
        }

        User topUser = loanService.getUserWithMostLoans();
        if (topUser != null) {
            System.out.println("- Utente con più prestiti: " + topUser.getUsername());
        } else {
            System.out.println("- Nessun utente con prestiti registrati.");
        }

        System.out.println();

        System.out.println("=== Ricerca Libri ===");
        System.out.print("Inserisci una parola chiave per cercare libri: ");
        String keyword = scanner.nextLine();

        loanController.searchBooks(keyword);

        System.out.println();
        System.out.println("=== Fine Operazioni ===");

        scanner.close();
    }
}
