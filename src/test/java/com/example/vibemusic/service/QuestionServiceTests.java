package com.example.vibemusic.service;

import com.example.vibemusic.domain.Music;
import com.example.vibemusic.domain.Question;
import com.example.vibemusic.domain.Reply;
import com.example.vibemusic.repository.QuestionRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

@Log4j2
class QuestionServiceTests {
    @Autowired
    private QuestionRepository questionRepository;


    @MockBean
    private QuestionService questionService; // Mock the QuestionService dependency


    @Test
    public void testInsert(){
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Question question = Question.builder()
                    .qTitle("질문..." + i)
                    .qContent("질문내용..." + i)
                    .qWriter("질문자" + (i % 10))
                    .build();
            Question result = questionRepository.save(question);
            log.info("QNO : " + result.getQNo());

        });

    }

    @Test
    public void testRead() {
        Optional<Question> byId = questionRepository.findById(1L);
        Question question = byId.orElseThrow();
  }



    }