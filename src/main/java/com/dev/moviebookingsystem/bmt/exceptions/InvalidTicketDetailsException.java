package com.dev.moviebookingsystem.bmt.exceptions;

public class InvalidTicketDetailsException extends RuntimeException {
    public InvalidTicketDetailsException(String message) {
        super(message);
    }
}
