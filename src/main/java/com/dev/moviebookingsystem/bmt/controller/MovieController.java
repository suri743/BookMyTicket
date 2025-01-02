package com.dev.moviebookingsystem.bmt.controller;

import com.dev.moviebookingsystem.bmt.dto.MovieDto;
import com.dev.moviebookingsystem.bmt.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/movie")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movie) {
        return ResponseEntity.ok(movieService.createMovie(movie));
    }

    @GetMapping("/movie")
    public ResponseEntity<List<MovieDto>> getAllMovies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable("id") int movieId){
        return ResponseEntity.ok(movieService.getMovieById(movieId));
    }

    @GetMapping("/movie/name/{name}")
    public ResponseEntity<MovieDto> getMovieByName(@PathVariable("name") String movieName){
        return ResponseEntity.ok(movieService.getMovieByName(movieName));
    }

    @PatchMapping("/movie/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable int id , @RequestBody MovieDto movie){
        return ResponseEntity.ok(movieService.updateMovie(id, movie));
    }

}
