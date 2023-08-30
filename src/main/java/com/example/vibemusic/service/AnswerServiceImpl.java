package com.example.vibemusic.service;

import com.example.vibemusic.domain.Answer;
import com.example.vibemusic.dto.AnswerDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Log4j2
public class AnswerServiceImpl implements AnswerService{

    private final AnswerRepository answerRepository;

    private final ModelMapper modelMapper;

    @Override
    public Long answerRegister(AnswerDTO answerDTO) {

        Answer answer = modelMapper.map(answerDTO, Answer.class);

        Long ano = answerRepository.save(answer).getAno();

        return ano;
    }

    @Override
    public Page<Answer> answerListOfQuestion(Long qNo, Pageable pageable) {
        return answerRepository.answerListOfQuestion(qNo, pageable);
    }

    @Override
    public AnswerDTO answerRead(Long ano) {

        Optional<Answer> answerOptional = answerRepository.findById(ano);

        Answer answer = answerOptional.orElseThrow();

        return modelMapper.map(answer, AnswerDTO.class);
    }

    @Override
    public void answerModify(AnswerDTO answerDTO) {

        Optional<Answer> answerOptional = answerRepository.findById(answerDTO.getAno());

        Answer answer = answerOptional.orElseThrow();

        answer.changeText(answerDTO.getAnswerText());

        answerRepository.save(answer);

    }

    @Override
    public void answerRemove(Long ano) {

        answerRepository.deleteById(ano);

    }

    @Override
    public PageResponseDTO<AnswerDTO> getListOfQuestion(Long qNo, PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() <=0? 0: pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(),
                Sort.by("rno").ascending());

        Page<Answer> result = answerRepository.answerListOfQuestion(qNo, pageable);

        List<AnswerDTO> dtoList =
                result.getContent().stream().map(answer -> modelMapper.map(answer, AnswerDTO.class))
                        .collect(Collectors.toList());

        return PageResponseDTO.<AnswerDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
