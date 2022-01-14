package com.quiz.DAO;

import com.quiz.models.Questions;
import com.quiz.repositories.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionsDAO {
    @Autowired
    public QuestionsRepository questionsRepository;

    public List<Questions> index() {
        return questionsRepository.findAll();
    }

    public Questions show(Long id) {
        return questionsRepository.getById(id);
    }

    public Questions save(Questions questions) {
        return questionsRepository.save(questions);
    }

    public void delete(Questions questions) {
        questionsRepository.delete(questions);
    }

    public void edit(Long id,Questions questions) {
        Questions questions1 = show(id);
        questions1.setQuestion(questions.getQuestion());
        questions1.setAnswer(questions.getAnswer());
        questions1.setType(questions.getType());
        questionsRepository.save(questions1);
    }
}
