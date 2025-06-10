package com.biblioteca.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BookTest {

  @Test
  public void testBookCreation() {
    Book book = new Book("Il nome della rosa");
    assertEquals("Il nome della rosa", book.getTitle());
    assertFalse(book.isLoaned());
  }

  @Test
  public void testSetLoaned() {
    Book book = new Book("1984");
    book.setLoaned(true);
    assertTrue(book.isLoaned());
  }

  @Test
  public void testReservationQueue() {
    Book book = new Book("La coscienza di Zeno");
    User user1 = new User("Mario", "mario", "mario");
    User user2 = new User("Luca", "luca", "luca");

    book.addReservation(user1);
    book.addReservation(user2);

    assertTrue(book.hasReservations());
    assertEquals(user1, book.getNextReservation());
    assertEquals(user2, book.getNextReservation());
    assertFalse(book.hasReservations());
  }
}
