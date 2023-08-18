//package com.example.vibemusic.controller;
//
//
//import com.example.vibemusic.dto.MusicDTO;
//import com.example.vibemusic.dto.PageRequestDTO;
//import com.example.vibemusic.dto.PageResponseDTO;
//import com.example.vibemusic.dto.QuestionDTO;
//
//import com.example.vibemusic.service.QuestionService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping
//@RequiredArgsConstructor
//@Slf4j
//public class QuestionController {
//    private final QuestionService questionService;
//    @GetMapping("/questionRead")
//    public void qRead(Long qNo, Model model){
//        log.info("nNo=============> : {}", qNo);
//        QuestionDTO questionDTO = questionService.read1Quest(qNo);
//
//        model.addAttribute("dto", questionDTO);
//    }
//
//
//    @GetMapping("/questionList")
//    public void qList(PageRequestDTO pageRequestDTO, Model model){
//        PageResponseDTO<QuestionDTO> responseDTO = questionService.qList(pageRequestDTO);
//        model.addAttribute("responseDTO",responseDTO);
//
//    }
//}
