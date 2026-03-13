package com.hy.BioClub.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hy.BioClub.model.GameEntry;

@Service
public class GamesService {
    private final List<GameEntry> games = new ArrayList<>();

    public GamesService() {
        games.add(new GameEntry("Foldit", "Protein-folding puzzle that teaches structural biology and contributes to real research.", "https://fold.it", "Play Game"));
        games.add(new GameEntry("Eterna", "RNA design game where students learn RNA structure and participate in crowdsourced experiments.", "https://eternagame.org", "Play Game"));
        games.add(new GameEntry("Phylo", "Multiple sequence alignment game teaching comparative genomics and alignment principles.", "http://phylo.cs.mcgill.ca/", "Play Game"));
        games.add(new GameEntry("Immune Attack", "A 3D exploration game that explores cell biology and the immune system.", "https://www.hhmi.org/biointeractive/immune-attack", "Play Game"));
    }

    public List<GameEntry> getGames() {
        return Collections.unmodifiableList(games);
    }

    public void add(GameEntry g) {
        games.add(g);
    }

    public GameEntry get(int idx) {
        return games.get(idx);
    }

    public void update(int idx, GameEntry g) {
        games.set(idx, g);
    }

    public void delete(int idx) {
        games.remove(idx);
    }
}
