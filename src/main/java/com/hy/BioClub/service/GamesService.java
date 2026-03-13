package com.hy.BioClub.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hy.BioClub.model.GameEntry;

public class GamesService {
    private static final List<GameEntry> GAMES = new ArrayList<>();

    static {
        GAMES.add(new GameEntry("Foldit", "Protein-folding puzzle that teaches structural biology and contributes to real research.", "https://fold.it", "Play Game"));
        GAMES.add(new GameEntry("Eterna", "RNA design game where students learn RNA structure and participate in crowdsourced experiments.", "https://eternagame.org", "Play Game"));
        GAMES.add(new GameEntry("Phylo", "Multiple sequence alignment game teaching comparative genomics and alignment principles.", "http://phylo.cs.mcgill.ca/", "Play Game"));
        GAMES.add(new GameEntry("Immune Attack", "A 3D exploration game that explores cell biology and the immune system.", "https://www.hhmi.org/biointeractive/immune-attack", "Play Game"));
    }

    public static List<GameEntry> getGames() {
        return Collections.unmodifiableList(GAMES);
    }

    public static void add(GameEntry g) {
        GAMES.add(g);
    }

    public static GameEntry get(int idx) {
        return GAMES.get(idx);
    }

    public static void update(int idx, GameEntry g) {
        GAMES.set(idx, g);
    }

    public static void delete(int idx) {
        GAMES.remove(idx);
    }
}
