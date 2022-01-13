package com.quiz.DAO;

import com.quiz.models.Questions;
import com.quiz.models.Quiz;
import com.quiz.repositories.QuestionsRepository;
import com.quiz.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuizDAO {
    @Autowired
    public  QuizRepository quizRepository;

    @Autowired
    public QuestionsDAO questionsDAO;

    @Autowired
    public QuestionsRepository questionsRepository;

    public List<Quiz> index() {
        return quizRepository.findAll();
    }

    public Quiz show(Long id) {
        return quizRepository.getById(id);
    }

    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public void delete(Quiz quiz) {
        quizRepository.delete(quiz);
    }

    public void edit(Long id,Quiz quiz) {
        Quiz quizToBeUpdated = show(id);
        quizToBeUpdated.setNameQuiz(quiz.getNameQuiz());
        quizToBeUpdated.setEndDate(quiz.getEndDate());
        quizToBeUpdated.setStartDate(quiz.getStartDate());
        quizToBeUpdated.setDescription(quiz.getDescription());
        quizRepository.save(quizToBeUpdated);
    }

//    public List<Questions> showQuestions(Long id) {
//        Quiz quiz = quizRepository.getById(id);
//        quiz.setQuestions(questionsRepository.getById(id));
//        return quiz.getQuestions();
//    }

}
