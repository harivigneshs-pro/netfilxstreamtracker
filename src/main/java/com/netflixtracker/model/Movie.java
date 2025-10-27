package com.netflixtracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    @NotBlank
    @Column(length = 100)
    private String title;

    @Column(length = 50)
    private String genre;

    @Column(name = "release_year")
    private Integer releaseYear;

    private Integer duration;

    @Column(name = "rating_avg")
    private Double ratingAvg = 0.0;

    @Column(name = "content_type", length = 30)
    private String contentType;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<WatchHistory> watchHistories;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Recommendation> recommendations;
}