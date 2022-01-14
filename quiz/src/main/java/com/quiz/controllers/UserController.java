package com.quiz.controllers;

import com.quiz.DAO.QuestionsDAO;
import com.quiz.DAO.QuizDAO;
import com.quiz.DAO.UserDAO;
import com.quiz.models.Questions;
import com.quiz.models.Quiz;
import com.quiz.models.User;
import com.quiz.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private QuizDAO quizDAO;
    @Autowired
    private UserDAO userDAO;

    @GetMapping("/{login}")
    public String main(Model model, @PathVariable("login") String login) {
        model.addAttribute("quizs",quizDAO.index());
        return "user/index";
    }

    @GetMapping("/{login}/{id}")
    public String infoQuiz(@PathVariable("id") Long id, @PathVariable("login") String login, Model model) {
        model.addAttribute("quiz", quizDAO.show(id));
        model.addAttribute("user", userDAO.getByLogin(login));
        model.addAttribute("questions", quizDAO.show(id).getQuestions());
        return "user/show";
    }
    @PostMapping("/{login}/{id}")
    public String getAnswer(@PathVariable("id") Long id, @PathVariable("login") String login, @RequestParam(name="textAnswer") String textAnswer, @RequestParam(name="yourAnswer") String yourAnswer, Model model) {
        User user=  userDAO.getByLogin(login);
        List<String> answers = quizDAO.show(id).getQuestions().stream().map(s -> s.getAnswer()).collect(Collectors.toList());
        user.setQuit("yes");
        userDAO.edit(user.getId(), user);
        System.out.println(textAnswer);
        System.out.println(yourAnswer);
        return "redirect:/user/"+login;
    }


}
