package com.mysite.sbb.question;

import com.mysite.sbb.answer.Answer;
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

    //EAGER 필요한 데이터를 미리 로딩 -> 불필요한 경우에도 데이터를 미리 가져오게 된다.
    //FetchType.Lazy -> 작성안해도 기본적으로 설정되어 있다.
    //그럼 위의 두 방법 외의 방법은 없는가?
    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE, CascadeType.PERSIST}) // 선택적
    private List<Answer> answers = new ArrayList<>();

    public void addAnswer(String content) {
        Answer answer= new Answer();
        answer.setContent(content);
        answer.setQuestion(this);
        answers.add(answer);
    }
}
