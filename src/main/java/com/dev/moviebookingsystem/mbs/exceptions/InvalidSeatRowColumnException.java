package com.dev.moviebookingsystem.mbs.exceptions;

public class InvalidSeatRowColumnException extends RuntimeException {
    public InvalidSeatRowColumnException(String message) {
        super(message);
    }
}
