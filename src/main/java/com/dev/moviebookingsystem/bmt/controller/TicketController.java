package com.dev.moviebookingsystem.bmt.controller;

import com.dev.moviebookingsystem.bmt.dto.TicketDto;
import com.dev.moviebookingsystem.bmt.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/ticket")
    public ResponseEntity<TicketDto> createTicket(@RequestBody
                             TicketDto ticketDto) {
        return ResponseEntity.ok(ticketService.createTicket(ticketDto));
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable("id") int id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @GetMapping("/ticket/user/{userId}")
    public ResponseEntity<List<TicketDto>> getTicketByUserId(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(ticketService.getTicketsByUserId(userId));
    }

}
