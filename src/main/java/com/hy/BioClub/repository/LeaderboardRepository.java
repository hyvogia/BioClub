package com.hy.BioClub.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hy.BioClub.model.LeaderboardEntry;

@Repository
public class LeaderboardRepository {

    private final List<LeaderboardEntry> store = Collections.synchronizedList(new ArrayList<>());

    public List<LeaderboardEntry> findAll() {
        return new ArrayList<>(store);
    }

    public void save(LeaderboardEntry entry) {
        store.add(entry);
    }
}
