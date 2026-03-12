package com.hy.BioClub.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gameKey; // e.g. "phylo" or "mini-foldit"
    private String player;
    private int score;
    private Instant createdAt = Instant.now();

    public Score() {}

    public Score(String gameKey, String player, int score) {
        this.gameKey = gameKey;
        this.player = player;
        this.score = score;
        this.createdAt = Instant.now();
    }

    public Long getId() { return id; }
    public String getGameKey() { return gameKey; }
    public void setGameKey(String gameKey) { this.gameKey = gameKey; }
    public String getPlayer() { return player; }
    public void setPlayer(String player) { this.player = player; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
