package com.quiz.repositories;

import com.quiz.models.Questions;
import com.quiz.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions,Long> {
    List<Questions> getByQuiz(Quiz quiz);
}
