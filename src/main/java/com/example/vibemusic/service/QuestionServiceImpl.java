//package com.example.vibemusic.service;
//
//
//
//
//import com.example.vibemusic.domain.Music;
//import com.example.vibemusic.domain.Question;
//import com.example.vibemusic.dto.MusicDTO;
//import com.example.vibemusic.dto.PageRequestDTO;
//import com.example.vibemusic.dto.PageResponseDTO;
//import com.example.vibemusic.dto.QuestionDTO;
//import com.example.vibemusic.repository.QuestionRepository;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//@Transactional
//public class QuestionServiceImpl implements QuestionService{
//
//    private final QuestionRepository questionRepository;
//    private final ModelMapper modelMapper;
//
//    @Override
//    public Long registerQuest(QuestionDTO questionDTO) {
//
//        Question question = modelMapper.map(questionDTO, Question.class);
//
//        Long bno = questionRepository.save(question).getQNo();
//
//        return bno;
//
//    }
//    @Override
//    public QuestionDTO read1Quest(Long qNo) {
//        Optional<Question> result = questionRepository.findById(qNo);
//        Question question = result.orElseThrow();
//        QuestionDTO questionDTO = modelMapper.map(question, QuestionDTO.class);
//
//        return questionDTO;
//    }
//
//
//    @Override
//    public void modQuest(QuestionDTO questionDTO){
//
//        Optional<Question> result = questionRepository.findById(questionDTO.getQNo());
//
//        Question question = result.orElseThrow();
//
//        question.change(questionDTO.getQTitle(), questionDTO.getQContent());
//
//        questionRepository.save(question);
//
//    }
//
//
//    public void removeQuest(Long qno){
//
//        questionRepository.deleteById(qno);
//    }
//
////    @Override
////    public Page<Question> list(Pageable pageable) {
////        return questionRepository.findAll(pageable);
////    }
//
//    @Override
//    public PageResponseDTO<QuestionDTO> qList(PageRequestDTO pageRequestDTO) {
//        String[] types = pageRequestDTO.getTypes();
//        String keyword = pageRequestDTO.getKeyword();
//        Pageable pageable = pageRequestDTO.getPageable("bno");
//
//
//        Page<Question> result = questionRepository.searchAll(types,keyword,pageable);
//
//        //확인 검색..........
//        log.info("-------------------------------------------");
//        log.info("aaa getTotalPage : "+result.getTotalPages());
//        log.info("aaa getSize : "+ result.getSize());
//        log.info("aaa getTotalElements : "+ result.getTotalElements());
//        result.getContent().forEach(question -> log.info(question));
//        log.info("-------------------------------------------");
//
//
//
//        List<QuestionDTO> dtoqList = result.getContent().stream().map(question -> modelMapper.map(question,QuestionDTO.class)).collect(Collectors.toList());
//
//        PageResponseDTO<QuestionDTO> pageResponseDTO = new PageResponseDTO<QuestionDTO>(pageRequestDTO,dtoqList,(int)result.getTotalElements());
//
//        return pageResponseDTO;
//
////        return PageResponseDTO.<BoardDTO>withAll()
////                .pageRequestDTO(pageRequestDTO)
////                .dtoList(dtoList)
////                .total((int)result.getTotalElements())
////                .build();
//    }
//
//    @Override
//    public PageResponseDTO<QuestionDTO> qListPaging(PageRequestDTO pageRequestDTO) {
//
//        String[] types = pageRequestDTO.getTypes();
//        String keyword = pageRequestDTO.getKeyword();
//        Pageable pageable = pageRequestDTO.getPageable("no");
//
//        Page<Question> result = questionRepository.searchAll(types, keyword, pageable);
//
//        List<QuestionDTO> dtoList = result.getContent().stream().map(question -> modelMapper.map(question, QuestionDTO.class)).collect(Collectors.toList());
//
//        PageResponseDTO<QuestionDTO> pageResponseDTO = new PageResponseDTO<>(pageRequestDTO, dtoList, (int)result.getTotalElements());
//
//        return pageResponseDTO;
//    }
//}
