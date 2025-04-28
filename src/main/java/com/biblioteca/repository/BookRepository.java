package com.biblioteca.repository;

import com.biblioteca.model.Book;
import com.biblioteca.exception.DatabaseException;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();
    }

    public void save(Book book) {
        if (book == null) {
            throw new DatabaseException("Errore: libro nullo non può essere salvato.");
        }
        books.add(book);
    }

    public List<Book> findAll() {
        return books;
    }

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
