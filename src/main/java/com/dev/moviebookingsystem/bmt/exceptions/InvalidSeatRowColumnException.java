package com.dev.moviebookingsystem.bmt.exceptions;

public class InvalidSeatRowColumnException extends RuntimeException {
    public InvalidSeatRowColumnException(String message) {
        super(message);
    }
}
