package com.netflixtracker.service;

import com.netflixtracker.model.Recommendation;
import com.netflixtracker.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;

    public List<Recommendation> getAllRecommendations() {
        return recommendationRepository.findAll();
    }

    public List<Recommendation> getRecommendationsByUser(Long userId) {
        return recommendationRepository.findByUserUserId(userId);
    }

    public Recommendation saveRecommendation(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }
}