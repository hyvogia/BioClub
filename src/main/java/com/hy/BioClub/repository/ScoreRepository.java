package com.hy.BioClub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hy.BioClub.model.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findTop20ByGameKeyOrderByScoreDescCreatedAtAsc(String gameKey);
}
