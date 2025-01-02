package com.dev.moviebookingsystem.bmt.controller;

import com.dev.moviebookingsystem.bmt.dto.SeatDto;
import com.dev.moviebookingsystem.bmt.service.SeatService;
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
public class SeatController {
    private final SeatService seatService;

    @PostMapping("/seat")
    public ResponseEntity<SeatDto> addSeat(@RequestBody SeatDto seat) {
        return ResponseEntity.ok(seatService.createSeat(seat));
    }

    @GetMapping("/seat")
    public ResponseEntity<List<SeatDto>> getAllSeats(){
        return ResponseEntity.ok(seatService.getAllSeats());
    }

    @GetMapping("/seat/{id}")
    public ResponseEntity<SeatDto> getSeatById(@PathVariable("id") int seatId){
        return ResponseEntity.ok(seatService.getSeatById(seatId));
    }

}
