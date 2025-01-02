package com.dev.moviebookingsystem.bmt.exceptions;

public class TheaterNotFoundException extends RuntimeException {
    public TheaterNotFoundException(String message) {
        super(message);
    }
}
