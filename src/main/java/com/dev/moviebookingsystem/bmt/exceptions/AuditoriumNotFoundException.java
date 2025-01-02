package com.dev.moviebookingsystem.bmt.exceptions;

public class AuditoriumNotFoundException extends RuntimeException {
    public AuditoriumNotFoundException(String message) {
        super(message);
    }
}
