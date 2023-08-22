package com.example.vibemusic.controller;



import com.example.vibemusic.domain.Question;

import com.example.vibemusic.dto.MusicDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.dto.QuestionDTO;

import com.example.vibemusic.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    private final QuestionService questionService;
    @GetMapping("/questionRead")
    public void qRead(Long qNo, Model model){
        log.info("nNo=============> : {}", qNo);
        QuestionDTO questionDTO = questionService.read1Quest(qNo);

        model.addAttribute("dto", questionDTO);
    }

    @GetMapping("/questions")
    public void list(PageRequestDTO pageRequestDTO, Model model, Pageable pageable){
        PageResponseDTO<QuestionDTO> responseDTO = questionService.listWithNewQuestion(pageRequestDTO);
        model.addAttribute("qList", responseDTO);
    }


//    @GetMapping("/questions")
//    public String qRead(Pageable pageable, Model model){
//
//        // Set the number of items per page
//        int pageSize = 5;
//        pageable = PageRequest.of(pageable.getPageNumber(), pageSize);
//
//        // Retrieve a Page of news items using the NewsService
//        Page<Question> questionsPage = questionService.qlist(pageable);
//
//        // Add the Page of news items to the model
//        model.addAttribute("questPage", questionsPage);
//
//        // Return the view name for rendering
//        return "questions";  //

    }



