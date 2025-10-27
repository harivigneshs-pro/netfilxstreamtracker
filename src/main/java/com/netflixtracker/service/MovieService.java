package com.netflixtracker.service;

import com.netflixtracker.model.Movie;
import com.netflixtracker.repository.MovieRepository;
import com.netflixtracker.repository.WatchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final WatchHistoryRepository watchHistoryRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getTopRatedMovies() {
        return movieRepository.findTopRatedMovies().stream().limit(5).toList();
    }

    public List<Movie> getTopWatchedMovies() {
        return movieRepository.findTopWatchedMovies().stream().limit(5).toList();
    }

    public void updateMovieRating(Long movieId) {
        Double avgRating = watchHistoryRepository.findAverageRatingByMovieId(movieId);
        if (avgRating != null) {
            Movie movie = movieRepository.findById(movieId).orElse(null);
            if (movie != null) {
                movie.setRatingAvg(avgRating);
                movieRepository.save(movie);
            }
        }
    }
}