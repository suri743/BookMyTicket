package com.dev.moviebookingsystem.bmt.exceptions;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String message) {
        super(message);
    }
}
