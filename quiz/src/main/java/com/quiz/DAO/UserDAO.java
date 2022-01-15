package com.quiz.DAO;

import com.quiz.models.Quiz;
import com.quiz.models.User;
import com.quiz.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAO {
    @Autowired
    public UserRepository userRepository;

    public List<User> index() {
        return userRepository.findAll();
    }

    public User show(Long id) {
        return userRepository.getById(id);
    }

    public User getByLogin(String login) {
        return userRepository.getByLogin(login);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void edit(Long id,User user) {
        User userToBeUpdated = show(id);
        userToBeUpdated.setLogin(user.getLogin());
        userToBeUpdated.setPassword(user.getPassword());
        userToBeUpdated.setIsAdmin(user.getIsAdmin());
        userRepository.save(userToBeUpdated);
    }

}
