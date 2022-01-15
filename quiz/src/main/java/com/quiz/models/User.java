package com.quiz.models;

import lombok.Data;
import org.hibernate.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

import static org.hibernate.annotations.LazyCollectionOption.FALSE;
import static org.hibernate.annotations.LazyCollectionOption.TRUE;

@Component
//@Data
@Entity
@Table(name="users")
@DynamicInsert
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column
    private String login;
    @Column
    private String password;
    @Column(columnDefinition = "text default 'no'")
    private String isAdmin;
    @OneToMany(mappedBy="users",cascade = CascadeType.REMOVE/*, fetch = FetchType.EAGER*/)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UserQuiz> userQuiz;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<UserQuiz> getUserQuiz() {
        return userQuiz;
    }

    public void setUserQuiz(UserQuiz userQuiz) {
        this.userQuiz.add(userQuiz);
    }
    public User(){}

    public User(String isAdmin, String login, String password) {
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }
}
