package com.dev.moviebookingsystem.bmt.exceptions;

public class ShowSeatNotFoundException extends RuntimeException {
    public ShowSeatNotFoundException(String message) {
        super(message);
    }
}
