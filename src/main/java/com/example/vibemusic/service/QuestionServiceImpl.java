package com.example.vibemusic.service;

import com.example.vibemusic.domain.Question;

import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.dto.QuestionDTO;
import com.example.vibemusic.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;


    @Override // 1개 읽기
    public QuestionDTO read1Quest(Long qNo) {
        Optional<Question> result = questionRepository.findById(qNo);
        Question question = result.orElseThrow();
        QuestionDTO questionDTO = modelMapper.map(question, QuestionDTO.class);

        return questionDTO;
    }

    @Override
    public Long registerQuest(QuestionDTO questionDTO) {

        Question question = modelMapper.map(questionDTO, Question.class);

        Long bno = questionRepository.save(question).getQNo();

        return bno;

    }

    @Override
    public void modQuest(QuestionDTO questionDTO){

        Optional<Question> result = questionRepository.findById(questionDTO.getQNo());

        Question question = result.orElseThrow();

        question.change(questionDTO.getQTitle(), questionDTO.getQContent());

        questionRepository.save(question);

    }


    public void removeQuest(Long qno){

        questionRepository.deleteById(qno);
    }


    @Transactional
    @Override
    public void increaseQViewCount(Long qNo) {
        Question question = questionRepository.findById(qNo)
                .orElseThrow(() -> new IllegalArgumentException("Question not found with nNo: " + qNo));

        int currentViewCount = question.getQViewCount();
        question.setQViewCount(currentViewCount + 1);

        questionRepository.save(question);
    }

    @Override
    public Page<Question> questionListPage(Pageable pageable) {
        return null;
    }

    @Override
    public PageResponseDTO<QuestionDTO> listWithNewQuestion(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("qNo");

        Page<Question> result = questionRepository.searchAll(types, keyword, pageable);

        List<QuestionDTO> dtoList = result.getContent().stream()
                .map(question -> modelMapper.map(question, QuestionDTO.class))
                .collect(Collectors.toList());

        PageResponseDTO<QuestionDTO> pageResponseDTO = new PageResponseDTO<>(pageRequestDTO, dtoList, (int) result.getTotalElements());

        return pageResponseDTO;
    }

    @Override
    public Page<Question> qlist(Pageable pageable) {
        return null;
    }


}