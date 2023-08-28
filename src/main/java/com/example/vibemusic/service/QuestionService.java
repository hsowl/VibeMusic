package com.example.vibemusic.service;


import com.example.vibemusic.domain.Question;

import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.dto.QuestionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionService {



    Long registerQuest(QuestionDTO questionDTO);

    QuestionDTO read1Quest(Long qNo);

    void modQuest(QuestionDTO questionDTO);

    void removeQuest(Long qNo);

    void increaseQViewCount(Long qNo);


    PageResponseDTO<QuestionDTO> listWithNewQuestion(PageRequestDTO pageRequestDTO);


    Page<Question> list(Pageable pageable);

    //question 리스트 불러오는 메소드 qList라고 선언

}
