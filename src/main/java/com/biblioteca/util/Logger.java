package com.biblioteca.util;

import com.biblioteca.service.LoanService;

public class Logger {
    private LoanService service;

    public Logger(LoanService service) {
        this.service = service;
    }

    public void log(String message) {
        System.out.println("LOG: " + message);
        int count = service.getActiveLoans();
        System.out.println("Prestiti attivi registrati: " + count);
    }
}
