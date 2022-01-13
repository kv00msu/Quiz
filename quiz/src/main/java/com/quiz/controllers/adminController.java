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

    @GetMapping
    public String mainAdmin(Model model) {
        model.addAttribute("quizs",quizDAO.index());
        return "admin/index";
    }

    @GetMapping("/new")
    public String newQuiz(@ModelAttribute("quiz") Quiz quiz) {
        return "admin/new";
    }

    @PostMapping
    public String createQuiz(@ModelAttribute("quiz") Quiz quiz) {
        quizDAO.save(quiz);
        return "redirect:/admin";
    }
    @GetMapping("/{id}")
    public String infoQuiz(@PathVariable("id") Long id, Model model) {
        model.addAttribute("quiz", quizDAO.show(id));
        model.addAttribute("questions", quizDAO.show(id).getQuestions());
//        System.out.println(quizDAO.show(id).getQuestions());
//        System.out.println(quizDAO.show(id).getQuestions());
        return "admin/info";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        quizDAO.delete(quizDAO.show(id));
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String change(@PathVariable("id") Long id, Model model) {
        model.addAttribute("quiz", quizDAO.show(id));
        return "admin/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("quiz") Quiz quiz, @PathVariable("id") Long id) {
        quizDAO.edit(id, quiz);
        return "redirect:/admin";
    }
}
