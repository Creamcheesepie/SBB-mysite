package com.mysite.sbb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

	@Test
    void t1() {
        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        Question q = all.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }

    @Test
    @DisplayName("findById")
    void t2() {
        Optional<Question> oq = this.questionRepository.findById(1);

        if(oq.isPresent()) {
            Question q = oq.get();
            assertEquals("sbb가 무엇인가요?", q.getSubject());
        }
    }

    @Test
    @DisplayName("findBySubject")
    void t3() {
        Optional<Question> oq = this.questionRepository.findBySubject("sbb가 무엇인가요?");
        if(oq.isPresent()) {
            Question q = oq.get();
            assertEquals("sbb가 무엇인가요?", q.getSubject());
        }
    }

    @Test
    @DisplayName("findBySubjectAndContent")
    void t4(){
        Optional<Question> oq = this.questionRepository.findBySubjectAndContent("Sbb가 무엇인가요?","sbb에 대해서 알고 싶습니다");

        if(oq.isPresent()) {
            Question q = oq.get();
            assertEquals(q.getSubject(),"Sbb가 무엇인가요?");
        }
    }

    @Test
    @DisplayName("findByLikeSubject")
    void t5() {
        List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
        Question q = qList.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }

    @Test
    @DisplayName("데이터 수정")
    void t6() {
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 제목");
        this.questionRepository.save(q);

        Optional<Question> oq2 = this.questionRepository.findById(1);
        Question q2 = oq2.get();
        assertEquals(q2.getSubject(),"수정된 제목");

    }

    @Test
    @DisplayName("데이터 삭제")
    void t7() {
        assertEquals(2, this.questionRepository.count());
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1, this.questionRepository.count());
    }

    @Test
    @DisplayName("답변 저장 - Repository 버전")
    void t8() {
        Question q = this.questionRepository.findById(2).get();

        Answer a = new Answer();
        a.setContent("네 자동으로 생성됩니다.");
        a.setQuestion(q);  // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
        this.answerRepository.save(a);
    }

    @Test
    @DisplayName("객체 지향적으로 답변 저장")
    @Transactional
    void t9() {
        Question q = this.questionRepository.findById(2).get();

        Answer answer =new Answer();
        answer.setContent("답변 내용");
        answer.setQuestion(q);

        q.getAnswers().add(answer);
    }

    @Test
    @DisplayName("객체 지향적으로 답변 저장 v2 OneToMany 버전")
    @Transactional
    void t9_1() {
        Question q = this.questionRepository.findById(2).get();
        q.addAnswer("답변 내용1");

    }

    @Test
    @DisplayName("2번 질문의 답글 조회")
    @Transactional // Transational 을 붙이면 왜 통과?
    void t10(){
//        Question q2 = this.questionRepository.findById(2).get();
        Answer answer = this.questionRepository.findById(2).get().getAnswers().get(0);
//        Answer answer = q2.getAnswers().get(0);

    }


}
