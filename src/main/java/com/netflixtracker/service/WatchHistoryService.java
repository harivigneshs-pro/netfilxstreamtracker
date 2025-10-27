package com.netflixtracker.service;

import com.netflixtracker.model.WatchHistory;
import com.netflixtracker.repository.WatchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchHistoryService {
    private final WatchHistoryRepository watchHistoryRepository;
    private final MovieService movieService;

    public List<WatchHistory> getAllWatchHistory() {
        return watchHistoryRepository.findAll();
    }

    public List<WatchHistory> getWatchHistoryByUser(Long userId) {
        return watchHistoryRepository.findByUserUserId(userId);
    }

    public List<WatchHistory> getWatchHistoryByMovie(Long movieId) {
        return watchHistoryRepository.findByMovieMovieId(movieId);
    }

    public WatchHistory saveWatchHistory(WatchHistory watchHistory) {
        WatchHistory saved = watchHistoryRepository.save(watchHistory);
        movieService.updateMovieRating(watchHistory.getMovie().getMovieId());
        return saved;
    }
}