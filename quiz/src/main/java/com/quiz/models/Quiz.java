package com.quiz.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Component
@Data
@Entity
@Table(name="quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name_quiz")
    private String nameQuiz;
    @Column(name="start_date")
    private Date startDate;
    @Column(name="end_date")
    private Date endDate;
    @Column(name="description")
    private String description;
    @ManyToMany(mappedBy = "quiz")
    private List<User> user;

    @OneToMany(mappedBy="quiz", fetch=FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Questions> questions;

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions.add(questions);
    }

    public Quiz() {}
    public Quiz(String nameQuiz, Date startDate, Date endDate, String description) {
        this.nameQuiz = nameQuiz;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }
}