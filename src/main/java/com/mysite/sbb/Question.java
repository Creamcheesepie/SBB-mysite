package com.mysite.sbb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 200)
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE, CascadeType.PERSIST}) // 선택적
    private List<Answer> answers = new ArrayList<>();

    public void addAnswer(String content) {
        Answer answer= new Answer();
        answer.setContent(content);
        answer.setQuestion(this);
        answers.add(answer);
    }
}
