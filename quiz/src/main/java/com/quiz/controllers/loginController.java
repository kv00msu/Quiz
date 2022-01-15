package com.quiz.controllers;

import com.quiz.DAO.UserDAO;
import com.quiz.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class loginController {
    @Autowired
    public UserDAO userDAO;

    @GetMapping("/userLogin")
    public String userLogin(@ModelAttribute("user") User user) {
        return "login/user";
    }
    @PostMapping("/userLogin")
    public String getUserLogin(@ModelAttribute("user") User user) {
        User user1 = userDAO.getByLogin(user.getLogin());
        if (user1 != null  && user1.getPassword().equals(user.getPassword()) && user1.getIsAdmin() != "yes") {
            return "redirect:/user/" + user.getLogin();
        }
        else if (user1 == null) {
            userDAO.save(new User("no", user.getLogin(), user.getPassword()));
            return "redirect:/user/" + user.getLogin();
        }
        return "redirect:/userLogin";
    }
    @PostMapping("/adminLogin")
    public String getAdminLogin(@ModelAttribute("user") User user) {
        User user1 = userDAO.getByLogin(user.getLogin());
        if (user1 != null  && user1.getPassword().equals(user.getPassword()) && user1.getIsAdmin().equals("yes")) {
            return "redirect:/admin/" + user.getLogin();
        }
        else if (user1 == null && user.getLogin().equals("admin") && user.getPassword().equals("admin")) {
            userDAO.save(new User("yes", "admin", "admin"));
            return "redirect:/admin/" + user.getLogin();
        }
        return "redirect:/adminLogin";
    }
    @GetMapping("/adminLogin")
    public String adminLogin(@ModelAttribute("user") User user) {
        return "login/admin";
    }
}
