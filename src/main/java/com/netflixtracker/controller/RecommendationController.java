package com.netflixtracker.controller;

import com.netflixtracker.model.Recommendation;
import com.netflixtracker.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping
    public List<Recommendation> getAllRecommendations() {
        return recommendationService.getAllRecommendations();
    }

    @GetMapping("/user/{userId}")
    public List<Recommendation> getRecommendationsByUser(@PathVariable Long userId) {
        return recommendationService.getRecommendationsByUser(userId);
    }

    @PostMapping
    public Recommendation createRecommendation(@Valid @RequestBody Recommendation recommendation) {
        return recommendationService.saveRecommendation(recommendation);
    }
}