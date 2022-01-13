package com.quiz.models;

import com.quiz.answerType.AnswerEnum;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

//@Data
@Component
@Entity
@Table(name="questions")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="question")
    private String question;
    @Column(name="answer")
    private String answer;
    @Column(name="type")
    private AnswerEnum type;
    @Column
    private String answerText;
    @Column
    private String answerOne1;
    @Column
    private String answerOne2;
    @Column
    private String answerOne3;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public Questions() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getAnswerOne1() {
        return answerOne1;
    }

    public void setAnswerOne1(String answerOne1) {
        this.answerOne1 = answerOne1;
    }

    public String getAnswerOne2() {
        return answerOne2;
    }

    public void setAnswerOne2(String answerOne2) {
        this.answerOne2 = answerOne2;
    }

    public String getAnswerOne3() {
        return answerOne3;
    }

    public void setAnswerOne3(String answerOne3) {
        this.answerOne3 = answerOne3;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public AnswerEnum getType() {
        return type;
    }

    public void setType(AnswerEnum type) {
        this.type = type;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
