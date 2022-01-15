package com.quiz.repositories;

import com.quiz.models.Quiz;
import com.quiz.models.User;
import com.quiz.models.UserQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserQuizRepository extends JpaRepository<UserQuiz, Long> {
    //UserQuiz getByQuizAndUser(Quiz quiz, User user);
    UserQuiz getByQuiz_IdAndUsers_Id(Long id1, Long id2);
}
