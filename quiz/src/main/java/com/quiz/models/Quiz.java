package com.quiz.models;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Component
//@Data
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

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

    @OneToMany(mappedBy="quiz", fetch=FetchType.EAGER)
    private List<Questions> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameQuiz() {
        return nameQuiz;
    }

    public void setNameQuiz(String nameQuiz) {
        this.nameQuiz = nameQuiz;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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