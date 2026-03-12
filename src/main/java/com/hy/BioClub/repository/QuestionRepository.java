package com.hy.BioClub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hy.BioClub.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
