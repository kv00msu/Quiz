package com.quiz.models;

import com.quiz.answerType.AnswerEnum;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
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
    @Enumerated(EnumType.STRING)
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
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


}
