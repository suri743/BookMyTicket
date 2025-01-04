package com.dev.moviebookingsystem.bmt.service;

<<<<<<< Updated upstream:src/main/java/com/dev/moviebookingsystem/bmt/service/MovieService.java
import com.dev.moviebookingsystem.bmt.dto.AdminDataDto;
import com.dev.moviebookingsystem.bmt.dto.MovieDto;
import com.dev.moviebookingsystem.bmt.exceptions.MovieNotFoundException;
import com.dev.moviebookingsystem.bmt.mapper.MovieMapper;
import com.dev.moviebookingsystem.bmt.model.Movie;
import com.dev.moviebookingsystem.bmt.repository.MovieRepository;
=======
import com.dev.moviebookingsystem.mbs.dto.MovieDto;
import com.dev.moviebookingsystem.mbs.exceptions.MovieNotFoundException;
import com.dev.moviebookingsystem.mbs.mapper.MovieMapper;
import com.dev.moviebookingsystem.mbs.model.Movie;
import com.dev.moviebookingsystem.mbs.repository.MovieRepository;
>>>>>>> Stashed changes:src/main/java/com/dev/moviebookingsystem/mbs/service/MovieService.java
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieDto createMovie(MovieDto movieDto) {
        Movie movie = movieRepository.save(movieMapper.mapDtoToEntity(movieDto));
        return movieMapper.mapEntityToDto(movie);
    }

    public MovieDto getMovieById(int movieId) {
        Movie movie = movieRepository.findById(movieId)
            .orElseThrow(() -> new MovieNotFoundException("Movie not found for id: " + movieId));

        return movieMapper.mapEntityToDto(movie);
    }

    public MovieDto getMovieByName(String movieName) {
        Movie movie = movieRepository.findMovieByName(movieName)
            .orElseThrow(() -> new MovieNotFoundException("Movie not found for name: " + movieName));

        return movieMapper.mapEntityToDto(movie);
    }

    public List<MovieDto> getAllMovies() {
        return movieMapper.mapEntityListToDtoList(movieRepository.findAll());
    }

    public MovieDto updateMovie(int id, MovieDto movieDto) {
        Movie mainMovie = movieRepository.findById(id)
            .orElseThrow(() -> new MovieNotFoundException("Movie not found for id: " + id));

        Movie movie = movieMapper.mapDtoToEntity(movieDto);

        movie.setId(id);
        movie.setCreatedAt(mainMovie.getCreatedAt());
        movie.setDescription(movie.getDescription() == null ? mainMovie.getDescription() : movie.getDescription());
        movie.setLanguage(movie.getLanguage() == null ? mainMovie.getLanguage() : movie.getLanguage());
        movie.setMovieFeatures(movie.getMovieFeatures() == null ? mainMovie.getMovieFeatures() : movie.getMovieFeatures());
        movie.setName(movie.getName() == null ? mainMovie.getName() : movie.getName());

        return movieMapper.mapEntityToDto(movieRepository.save(movie));
    }

    public void deleteMovie(int movieId) {
        movieRepository.deleteById(movieId);
    }
}
