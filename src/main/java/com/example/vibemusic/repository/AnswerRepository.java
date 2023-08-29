package com.example.vibemusic.repository;

import com.example.vibemusic.domain.Answer;
import com.example.vibemusic.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAnswerByQuestion_qNo(Long qNo);

    @Query("select a from Answer  a where a.question.qNo = :qNo")
    Page<Answer> answerListOfQuestion(Long qNo, Pageable pageable);
}