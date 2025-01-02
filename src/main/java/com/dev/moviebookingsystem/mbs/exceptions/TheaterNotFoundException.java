package com.dev.moviebookingsystem.mbs.exceptions;

public class TheaterNotFoundException extends RuntimeException {
    public TheaterNotFoundException(String message) {
        super(message);
    }
}
