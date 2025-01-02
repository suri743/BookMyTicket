package com.dev.moviebookingsystem.bmt.controller;

import com.dev.moviebookingsystem.bmt.dto.AuditoriumDto;
import com.dev.moviebookingsystem.bmt.service.AuditoriumService;
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
public class AuditoriumController {
    private final AuditoriumService auditoriumService;

    @PostMapping("/auditorium")
    public ResponseEntity<AuditoriumDto> addTheater(@RequestBody AuditoriumDto auditorium) {
        return ResponseEntity.ok(auditoriumService.createAuditorium(auditorium));
    }

    @GetMapping("/auditorium")
    public ResponseEntity<List<AuditoriumDto>> getAllAuditoriums() {
        return ResponseEntity.ok(auditoriumService.getAllAuditoriums());
    }

    @GetMapping("/auditorium/{id}")
    public ResponseEntity<AuditoriumDto> getAuditoriumById(@PathVariable("id") int auditoriumId) {
        return ResponseEntity.ok(auditoriumService.getAuditoriumById(auditoriumId));
    }
}
