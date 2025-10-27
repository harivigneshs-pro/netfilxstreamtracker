package com.netflixtracker.repository;

import com.netflixtracker.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m ORDER BY m.ratingAvg DESC")
    List<Movie> findTopRatedMovies();

    @Query("SELECT m FROM Movie m JOIN m.watchHistories wh GROUP BY m ORDER BY COUNT(wh) DESC")
    List<Movie> findTopWatchedMovies();
}