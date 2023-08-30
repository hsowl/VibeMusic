package com.example.vibemusic.service;

import com.example.vibemusic.domain.Answer;
import com.example.vibemusic.dto.AnswerDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.dto.QuestionDTO;
import com.example.vibemusic.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface AnswerService {
    Long answerRegister(AnswerDTO answerDTO);

    AnswerDTO answerRead(Long ano);

    void answerModify(AnswerDTO answerDTO);

    void answerRemove(Long ano);

    Page<Answer> answerListOfQuestion(Long qNo, Pageable pageable);

    PageResponseDTO<AnswerDTO> getListOfQuestion(Long qNo, PageRequestDTO pageRequestDTO);




}
