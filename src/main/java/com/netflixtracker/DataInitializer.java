package com.netflixtracker;

import com.netflixtracker.model.*;
import com.netflixtracker.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final WatchHistoryRepository watchHistoryRepository;
    private final RecommendationRepository recommendationRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            initializeData();
        }
    }

    private void initializeData() {
        // Create users
        User user1 = new User(null, "John Doe", "john@example.com", "Premium", LocalDateTime.now(), null, null);
        User user2 = new User(null, "Jane Smith", "jane@example.com", "Standard", LocalDateTime.now(), null, null);
        User user3 = new User(null, "Bob Johnson", "bob@example.com", "Basic", LocalDateTime.now(), null, null);
        
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        // Create movies
        Movie movie1 = new Movie(null, "The Matrix", "Sci-Fi", 1999, 136, 0.0, "Movie", LocalDateTime.now(), null, null);
        Movie movie2 = new Movie(null, "Inception", "Sci-Fi", 2010, 148, 0.0, "Movie", LocalDateTime.now(), null, null);
        Movie movie3 = new Movie(null, "The Godfather", "Crime", 1972, 175, 0.0, "Movie", LocalDateTime.now(), null, null);
        Movie movie4 = new Movie(null, "Pulp Fiction", "Crime", 1994, 154, 0.0, "Movie", LocalDateTime.now(), null, null);
        Movie movie5 = new Movie(null, "The Dark Knight", "Action", 2008, 152, 0.0, "Movie", LocalDateTime.now(), null, null);
        
        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);
        movieRepository.save(movie4);
        movieRepository.save(movie5);

        // Create watch history
        WatchHistory wh1 = new WatchHistory(null, user1, movie1, LocalDateTime.now(), 5);
        WatchHistory wh2 = new WatchHistory(null, user1, movie2, LocalDateTime.now(), 4);
        WatchHistory wh3 = new WatchHistory(null, user2, movie1, LocalDateTime.now(), 4);
        WatchHistory wh4 = new WatchHistory(null, user2, movie3, LocalDateTime.now(), 5);
        WatchHistory wh5 = new WatchHistory(null, user3, movie1, LocalDateTime.now(), 3);
        WatchHistory wh6 = new WatchHistory(null, user3, movie4, LocalDateTime.now(), 4);
        
        watchHistoryRepository.save(wh1);
        watchHistoryRepository.save(wh2);
        watchHistoryRepository.save(wh3);
        watchHistoryRepository.save(wh4);
        watchHistoryRepository.save(wh5);
        watchHistoryRepository.save(wh6);

        // Update movie ratings
        updateMovieRatings();

        // Create recommendations
        Recommendation rec1 = new Recommendation(null, user1, movie3, "Based on your high ratings", LocalDateTime.now());
        Recommendation rec2 = new Recommendation(null, user2, movie2, "Similar to your watched movies", LocalDateTime.now());
        Recommendation rec3 = new Recommendation(null, user3, movie5, "Top rated action movie", LocalDateTime.now());
        
        recommendationRepository.save(rec1);
        recommendationRepository.save(rec2);
        recommendationRepository.save(rec3);
    }

    private void updateMovieRatings() {
        movieRepository.findAll().forEach(movie -> {
            Double avgRating = watchHistoryRepository.findAverageRatingByMovieId(movie.getMovieId());
            if (avgRating != null) {
                movie.setRatingAvg(avgRating);
                movieRepository.save(movie);
            }
        });
    }
}