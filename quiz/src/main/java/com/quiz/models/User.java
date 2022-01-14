package com.quiz.models;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Data
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
    private String quit;
    @Column(columnDefinition = "text default 'no'")
    private String isAdmin;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Quiz_User",
            joinColumns = { @JoinColumn(name = "users_id") },
            inverseJoinColumns = { @JoinColumn(name = "quiz_id") }
    )
    private List<Quiz> quiz;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Questions_User",
            joinColumns = { @JoinColumn(name = "users_id") },
            inverseJoinColumns = { @JoinColumn(name = "questions_id") }
    )
    private List<Questions> questions;
}
