package com.biblioteca.repository;

import com.biblioteca.model.Book;
import com.biblioteca.exception.DatabaseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Raccolta di libri
 */
public class BookRepository {
  private List<Book> books;

  /**
   * Costruttore che istanzia con ArrayList
   */
  public BookRepository() {
    this.books = new ArrayList<>();
  }

  /**
   * Medoto che salva un libro nel repository
   *
   * @param book libro da salvare
   */
  public void save(Book book) {
    if (book == null) {
      throw new DatabaseException("Errore: libro nullo non può essere salvato.");
    }

    books.add(book);
  }

  /**
   * Ritorna la lista di tutti i libri presenti
   * @return lista
   */
  public List<Book> findAll() {
    return books;
  }

  /**
   * Cerca se sono presenti libri con che contengono determinate parole
   * @param query parole del titolo del libro cercato
   * @return lista dei libri trovati
   */
  public List<Book> searchByTitle(String query) {
    List<Book> results = new ArrayList<>();
    for (Book book : books) {
      if (book.getTitle().toLowerCase().contains(query.toLowerCase())) {
        results.add(book);
      }
    }
    return results;
  }
}
