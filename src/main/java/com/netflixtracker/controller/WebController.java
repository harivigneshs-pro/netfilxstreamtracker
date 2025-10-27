package com.netflixtracker.controller;

import com.netflixtracker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final UserService userService;
    private final MovieService movieService;
    private final WatchHistoryService watchHistoryService;
    private final RecommendationService recommendationService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/movies")
    public String movies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "movies";
    }

    @GetMapping("/history")
    public String history(Model model) {
        model.addAttribute("watchHistories", watchHistoryService.getAllWatchHistory());
        return "history";
    }

    @GetMapping("/recommendations/{userId}")
    public String recommendations(@PathVariable Long userId, Model model) {
        model.addAttribute("recommendations", recommendationService.getRecommendationsByUser(userId));
        model.addAttribute("userId", userId);
        return "recommendations";
    }

    @GetMapping("/reports")
    public String reports(Model model) {
        model.addAttribute("topRated", movieService.getTopRatedMovies());
        model.addAttribute("topWatched", movieService.getTopWatchedMovies());
        return "reports";
    }

    // Add User (form submission)
    @PostMapping("/users/add")
    public String addUser(@RequestParam String name,
                          @RequestParam String email,
                          @RequestParam String subscriptionType) {
        com.netflixtracker.model.User user = new com.netflixtracker.model.User();
        user.setName(name);
        user.setEmail(email);
        user.setSubscriptionType(subscriptionType);
        userService.saveUser(user);
        return "redirect:/users";
    }

    // Add Movie (form submission)
    @PostMapping("/movies/add")
    public String addMovie(@RequestParam String title,
                          @RequestParam String genre,
                          @RequestParam int releaseYear,
                          @RequestParam int duration,
                          @RequestParam String contentType) {
        com.netflixtracker.model.Movie movie = new com.netflixtracker.model.Movie();
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setReleaseYear(releaseYear);
        movie.setDuration(duration);
        movie.setContentType(contentType);
        movieService.saveMovie(movie);
        return "redirect:/movies";
    }
}