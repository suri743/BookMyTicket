package com.dev.moviebookingsystem.bmt.exceptions;

import com.dev.moviebookingsystem.bmt.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<ResponseDto<Void>> handleTicketNotFoundException(TicketNotFoundException ex) {
        ResponseDto<Void> response = new ResponseDto<>("ERROR", ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuditoriumNotFoundException.class)
    public ResponseEntity<ResponseDto<Void>> handleAuditoriumNotFoundException(AuditoriumNotFoundException ex) {
        ResponseDto<Void> response = new ResponseDto<>("ERROR", ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTicketDetailsException.class)
    public ResponseEntity<ResponseDto<Void>> handleInvalidTicketDetailsException(InvalidTicketDetailsException ex) {
        ResponseDto<Void> response = new ResponseDto<>("ERROR", ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShowNotFoundException.class)
    public ResponseEntity<ResponseDto<Void>> handleShowNotFoundException(ShowNotFoundException ex) {
        ResponseDto<Void> response = new ResponseDto<>("ERROR", ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<Void>> handleGenericException(Exception ex) {
        return buildErrorResponse("ERROR", "An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ResponseDto<Void>> buildErrorResponse(String status,
                                                                 String error,
                                                                 HttpStatus httpStatus) {
        ResponseDto<Void> response = new ResponseDto<>(status, error, null);
        return new ResponseEntity<>(response, httpStatus);
    }
}