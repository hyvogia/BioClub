package com.hy.BioClub.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hy.BioClub.model.Question;
import com.hy.BioClub.model.Score;
import com.hy.BioClub.repository.QuestionRepository;
import com.hy.BioClub.repository.ScoreRepository;

@RestController
@RequestMapping("/api")
public class GameApiController {

    private final ScoreRepository scores;
    private final QuestionRepository questions;

    public GameApiController(ScoreRepository scores, QuestionRepository questions) {
        this.scores = scores;
        this.questions = questions;
    }

    @GetMapping("/games")
    public ResponseEntity<List<String>> listGames(){
        // simple static list for now
        return ResponseEntity.ok(List.of("phylo","mini-foldit"));
    }

    @PostMapping("/score")
    public ResponseEntity<Score> submitScore(@RequestBody Score payload){
        if (payload.getGameKey() == null || payload.getPlayer() == null) return ResponseEntity.badRequest().build();
        Score saved = scores.save(payload);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/leaderboard/{game}")
    public ResponseEntity<List<Score>> leaderboard(@PathVariable String game){
        List<Score> list = scores.findTop20ByGameKeyOrderByScoreDescCreatedAtAsc(game);
        return ResponseEntity.ok(list.stream().limit(20).collect(Collectors.toList()));
    }

    @GetMapping("/questions")
    public ResponseEntity<List<String>> allQuestions(){
        List<String> list = questions.findAll().stream().map(q->q.getText()).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/questions/random")
    public ResponseEntity<String> randomQuestion(){
        List<Question> list = questions.findAll();
        if(list.isEmpty()) return ResponseEntity.noContent().build();
        int idx = (int)(Math.random()*list.size());
        return ResponseEntity.ok(list.get(idx).getText());
    }
}
