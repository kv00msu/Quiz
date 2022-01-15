package com.quiz.models;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import static org.hibernate.annotations.LazyCollectionOption.FALSE;
import static org.hibernate.annotations.LazyCollectionOption.TRUE;

@Component
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
    @OneToMany(mappedBy="quiz",cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UserQuiz> userQuiz;
    @OneToMany(mappedBy="quiz", cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Questions> questions;

    public String getNameQuiz() {
        return nameQuiz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<UserQuiz> getUserQuiz() {
        return userQuiz;
    }

    public void setUserQuiz(UserQuiz userQuiz) {
        this.userQuiz.add(userQuiz);
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