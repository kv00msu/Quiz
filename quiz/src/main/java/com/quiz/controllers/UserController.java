package com.quiz.controllers;

import com.quiz.DAO.QuestionsDAO;
import com.quiz.DAO.QuizDAO;
import com.quiz.DAO.UserDAO;
import com.quiz.DAO.UserQuizDAO;
import com.quiz.models.Questions;
import com.quiz.models.UserQuiz;
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
    @Autowired
    private UserQuizDAO userQuizDAO;
    @Autowired
    private QuestionsDAO questionsDAO;

    @GetMapping("/{login}")
    public String main(Model model, @PathVariable("login") String login) {
        model.addAttribute("quizs",quizDAO.index());
        return "user/index";
    }

    @GetMapping("/{login}/{id}")
    public String infoQuiz(@PathVariable("id") Long id, @PathVariable("login") String login, Model model) {
        Integer result = 0;
        UserQuiz userQuiz = userQuizDAO.getByQuizAndUser(quizDAO.show(id).getId(), userDAO.getByLogin(login).getId());
        if (userQuiz != null) {
            result = userQuiz.getResult();
            Integer count = quizDAO.show(id).getQuestions().size();
            model.addAttribute("count", count);
            model.addAttribute("result", result);
            model.addAttribute("quiz", quizDAO.show(id));
            model.addAttribute("user", userQuiz);
            model.addAttribute("questions", quizDAO.show(id).getQuestions());
            return "user/show";
        }
        userQuizDAO.save(new UserQuiz(userDAO.getByLogin(login), quizDAO.show(id)));
        return "redirect:/user/"+login+'/'+id;

    }
    @PostMapping("/{login}/{id}")
        public String getAnswer(@PathVariable("id") Long id, @PathVariable("login") String login, @RequestParam(name="textAnswer", required = false) String[] textAnswer, @RequestParam(name="yourAnswer", required = false) String[] yourAnswer, Model model) {
        UserQuiz userQuiz = userQuizDAO.getByQuizAndUser(quizDAO.show(id).getId(), userDAO.getByLogin(login).getId());
        List<String> answers = quizDAO.show(id).getQuestions().stream().map(s -> s.getAnswer()).collect(Collectors.toList());
        userQuiz.setQuit("yes");
        List<Questions> questions = questionsDAO.getByQuiz(quizDAO.show(id));
        int k = 0, j = 0, res = 0;
        for (Questions i : questions) {
             if (i.getType().name() == "text") {
                 i.setAnswerText(textAnswer[k]);
                 k++;
             }
             else {
                 i.setAnswerText(yourAnswer[j]);
                 j++;
             }
             questionsDAO.save(i);
             if (i.getAnswer().equals(i.getAnswerText())) {
                 res++;
             }
        }
        userQuiz.setResult(res);
        userQuizDAO.save(userQuiz);
        return "redirect:/user/"+login;
    }


}
