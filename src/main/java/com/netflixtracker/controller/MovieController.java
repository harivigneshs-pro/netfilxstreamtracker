package com.netflixtracker.controller;

import com.netflixtracker.model.Movie;
import com.netflixtracker.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Movie createMovie(@Valid @RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @GetMapping("/top-rated")
    public List<Movie> getTopRatedMovies() {
        return movieService.getTopRatedMovies();
    }

    @GetMapping("/top-watched")
    public List<Movie> getTopWatchedMovies() {
        return movieService.getTopWatchedMovies();
    }
}