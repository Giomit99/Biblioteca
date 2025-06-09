package com.biblioteca.model;

import java.util.LinkedList;
import java.util.Queue;

public class Book {
    private String title;
    private boolean isLoaned;
    private Queue<User> reservationQueue;

    public Book(String title) {
        this.reservationQueue = new LinkedList<>();
        this.title = title;
        this.isLoaned = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isLoaned() {
        return isLoaned;
    }

    public void setLoaned(boolean loaned) {
        isLoaned = loaned;
    }

  public void addReservation(User user) {
    reservationQueue.add(user);
  }

    public User getNextReservation() {
        return reservationQueue.poll();
    }

    public boolean hasReservations() {
        return !reservationQueue.isEmpty();
    }
}
