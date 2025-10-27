package com.netflixtracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank
    @Column(length = 50)
    private String name;

    @Email
    @Column(length = 100, unique = true)
    private String email;

    @Column(name = "subscription_type", length = 30)
    private String subscriptionType;

    @Column(name = "join_date")
    private LocalDateTime joinDate = LocalDateTime.now();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<WatchHistory> watchHistories;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Recommendation> recommendations;
}