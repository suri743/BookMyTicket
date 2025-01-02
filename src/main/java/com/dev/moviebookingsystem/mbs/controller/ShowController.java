package com.dev.moviebookingsystem.mbs.controller;

import com.dev.moviebookingsystem.mbs.dto.ShowDto;
import com.dev.moviebookingsystem.mbs.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShowController {
    private final ShowService showService;

    @PostMapping("/show")
    public ResponseEntity<ShowDto> addShow(ShowDto show) {
        return ResponseEntity.ok(showService.createShow(show));
    }

    @GetMapping("/show")
    public ResponseEntity<List<ShowDto>> getAllShows(){
        return ResponseEntity.ok(showService.getAllShows());
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<ShowDto> getShowById(@PathVariable("id") int showId){
        return ResponseEntity.ok(showService.getShowById(showId));
    }
}
