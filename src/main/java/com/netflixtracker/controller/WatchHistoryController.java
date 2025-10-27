package com.netflixtracker.controller;

import com.netflixtracker.model.WatchHistory;
import com.netflixtracker.service.WatchHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class WatchHistoryController {
    private final WatchHistoryService watchHistoryService;

    @GetMapping
    public List<WatchHistory> getAllWatchHistory() {
        return watchHistoryService.getAllWatchHistory();
    }

    @GetMapping("/user/{userId}")
    public List<WatchHistory> getWatchHistoryByUser(@PathVariable Long userId) {
        return watchHistoryService.getWatchHistoryByUser(userId);
    }

    @GetMapping("/movie/{movieId}")
    public List<WatchHistory> getWatchHistoryByMovie(@PathVariable Long movieId) {
        return watchHistoryService.getWatchHistoryByMovie(movieId);
    }

    @PostMapping
    public WatchHistory createWatchHistory(@Valid @RequestBody WatchHistory watchHistory) {
        return watchHistoryService.saveWatchHistory(watchHistory);
    }
}