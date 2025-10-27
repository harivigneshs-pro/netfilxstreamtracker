package com.netflixtracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "watch_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "watch_id")
    private Long watchId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "watch_date")
    private LocalDateTime watchDate = LocalDateTime.now();

    @Min(1)
    @Max(5)
    private Integer rating;
}