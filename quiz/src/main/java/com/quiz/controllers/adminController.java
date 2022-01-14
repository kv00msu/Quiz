package com.quiz.controllers;

import com.quiz.DAO.QuestionsDAO;
import com.quiz.DAO.QuizDAO;
import com.quiz.models.Questions;
import com.quiz.models.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private QuizDAO quizDAO;
    @Autowired
    private QuestionsDAO questionsDAO;

    @GetMapping("/{login}")
    public String mainAdmin(@PathVariable("login") String login, Model model) {
        model.addAttribute("quizs",quizDAO.index());
        model.addAttribute("login", login);
        return "admin/index";
    }

    @GetMapping("/{login}/new")
    public String newQuiz(@PathVariable("login") String login, @ModelAttribute("quiz") Quiz quiz, Model model) {
        model.addAttribute("login", login);
        return "admin/new";
    }

    @PostMapping("/{login}")
    public String createQuiz(@PathVariable("login") String login, @ModelAttribute("quiz") Quiz quiz) {
        quizDAO.save(quiz);
        System.out.println(login);
        return "redirect:/admin/"+login;
    }
    @GetMapping("/{login}/{id}")
    public String infoQuiz(@PathVariable("login") String login, @PathVariable("id") Long id, Model model) {
        model.addAttribute("login", login);
        model.addAttribute("quiz", quizDAO.show(id));
        model.addAttribute("questions", quizDAO.show(id).getQuestions());
        return "admin/info";
    }

    @PostMapping("/{login}/{id}")
    public String delete(@PathVariable("login") String login, @PathVariable("id") Long id) {
        quizDAO.delete(quizDAO.show(id));
        return "redirect:/admin/"+login;
    }

    @GetMapping("/{login}/{id}/edit")
    public String change(@PathVariable("login") String login, @PathVariable("id") Long id, Model model) {
        model.addAttribute("login", login);
        model.addAttribute("quiz", quizDAO.show(id));
        return "admin/edit";
    }

    @PostMapping("/{login}/{id}/edit")
    public String update(@ModelAttribute("quiz") Quiz quiz, @PathVariable("id") Long id, @PathVariable("login") String login) {
        quizDAO.edit(id, quiz);
        return "redirect:/admin/"+login;
    }
}
