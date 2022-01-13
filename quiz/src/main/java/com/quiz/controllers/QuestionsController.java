package com.quiz.controllers;

import com.quiz.DAO.QuestionsDAO;
import com.quiz.DAO.QuizDAO;
import com.quiz.models.Questions;
import com.quiz.models.Quiz;
import com.quiz.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/questions")
public class QuestionsController {
    @Autowired
    public QuestionsDAO questionsDAO;

    @Autowired
    public QuizDAO quizDAO;

    @GetMapping("/{id}")
    public String newQuiz(@ModelAttribute("questions") Questions questions, @PathVariable Long id) {
        return "questions/new";
    }

    @PostMapping("/{ques_id}")
    public String createQuiz(@ModelAttribute("questions") Questions questions, @PathVariable Long ques_id) {
        questions.setQuiz(quizDAO.show(ques_id));
        questionsDAO.save(questions);
        return "redirect:/admin";
    }
}
