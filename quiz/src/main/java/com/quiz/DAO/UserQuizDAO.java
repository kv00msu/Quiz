package com.quiz.DAO;

import com.quiz.models.UserQuiz;
import com.quiz.repositories.UserQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserQuizDAO {
    @Autowired
    public UserQuizRepository userQuizRepository;

    public List<UserQuiz> index() {
        return userQuizRepository.findAll();
    }

    public UserQuiz show(Long id) {
        return userQuizRepository.getById(id);
    }

    public UserQuiz getByQuizAndUser(Long id1, Long id2) {
        return userQuizRepository.getByQuiz_IdAndUsers_Id(id1, id2);
    }
    public UserQuiz save(UserQuiz userQuiz) {
        return userQuizRepository.save(userQuiz);
    }

}
