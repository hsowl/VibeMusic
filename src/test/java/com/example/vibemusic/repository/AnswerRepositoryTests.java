package com.example.vibemusic.repository;

import com.example.vibemusic.domain.Answer;
import com.example.vibemusic.domain.Music;
import com.example.vibemusic.domain.Question;
import com.example.vibemusic.domain.Reply;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@SpringBootTest
@Slf4j
class AnswerRepositoryTests {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void testInsert() {

        Long qNo = 50L;

        Question question = Question.builder().qNo(qNo).build();

        Answer answer = Answer.builder()
                .question(question)
                .answerText("답변")
                .answerer("답변자")
                .build();

        answerRepository.save(answer);
        log.info("answer:"+answer);
    }

    @Test
    public void readAll() {
       answerRepository.findAnswerByQuestion_qNo(50L).forEach(answer -> log.info("answer : {}",answer.getAno()));
    }

    @Test
    public void testDelete() {
        answerRepository.deleteById(2L);
    }

    @Test
    public void testModify() {
        Optional<Answer> byId = answerRepository.findById(1L);
        Answer answer = byId.orElseThrow();
        answer.changeText("개추개추개추개추개추");
        answerRepository.save(answer);

        log.info("answer:"+answer);
    }

    @Test
    public void testQuestReplies() {
        Long qNo = 1L;

        Pageable pageable = PageRequest.of(0,10, Sort.by("rno").descending());

        Page<Answer> result = answerRepository.answerListOfQuestion(qNo,pageable);

        result.getContent().forEach(answer -> log.info("answer : {}",answer));
    }

}