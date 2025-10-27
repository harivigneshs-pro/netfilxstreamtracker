package com.netflixtracker.repository;

import com.netflixtracker.model.WatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchHistoryRepository extends JpaRepository<WatchHistory, Long> {
    List<WatchHistory> findByUserUserId(Long userId);
    List<WatchHistory> findByMovieMovieId(Long movieId);

    @Query("SELECT AVG(wh.rating) FROM WatchHistory wh WHERE wh.movie.movieId = :movieId")
    Double findAverageRatingByMovieId(Long movieId);
}