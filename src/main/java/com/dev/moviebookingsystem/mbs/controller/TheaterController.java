package com.dev.moviebookingsystem.mbs.controller;

import com.dev.moviebookingsystem.mbs.dto.TheaterDto;
import com.dev.moviebookingsystem.mbs.service.TheaterService;
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
public class TheaterController {
    private final TheaterService theaterService;

    @PostMapping("/theater")
    public ResponseEntity<TheaterDto> addTheater(@RequestBody TheaterDto theater) {
        return ResponseEntity.ok(theaterService.createTheater(theater));
    }

    @GetMapping("/theater")
    public ResponseEntity<List<TheaterDto>> getAllTheaters() {
        return ResponseEntity.ok(theaterService.getAllTheaters());
    }

    @GetMapping("/theater/{id}")
    public ResponseEntity<TheaterDto> getTheaterById(@PathVariable("id") int theaterId) {
        return ResponseEntity.ok(theaterService.getTheaterById(theaterId));
    }
}
