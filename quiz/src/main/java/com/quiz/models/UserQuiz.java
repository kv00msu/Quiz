package com.quiz.models;

import lombok.Data;
import org.hibernate.annotations.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Component
@Entity
@Data
@Table(name="userquiz")
@DynamicInsert
public class UserQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(columnDefinition = "text default 'no'")
    private String quit;
    @Column
    private Integer result;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "users_id", nullable = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private User users;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "quiz_id", nullable = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private Quiz quiz;
    public UserQuiz(){}
    public UserQuiz(User users, Quiz quiz1) {
        this.users = users;
        this.quiz = quiz1;
    }
}
