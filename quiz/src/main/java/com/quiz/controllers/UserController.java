package com.quiz.controllers;

import com.quiz.DAO.QuizDAO;
import com.quiz.models.Quiz;
import com.quiz.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private QuizDAO quizDAO;

    @GetMapping
    public String main(Model model) {
        model.addAttribute("quizs",quizDAO.index());
        return "user/index";
    }

    @GetMapping("/{id}")
    public String infoQuiz(@PathVariable("id") Long id, Model model) {
        model.addAttribute("quiz", quizDAO.show(id));
        return "user/show";
    }

}
