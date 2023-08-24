package com.example.vibemusic.controller;



import com.example.vibemusic.domain.News;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    private final QuestionService questionService;



//    @GetMapping("/questionRead")
//    public void qRead(Long qNo, Model model) {
//        log.info("qNo=============> : {}", qNo);
//        QuestionDTO questionDTO = questionService.read1Quest(qNo);
//
//        model.addAttribute("dto", questionDTO);
//    }
    @GetMapping("/questionRead")
    public String qRead(@RequestParam Long qNo, Model model) {
        log.info("qNo=============> : {}", qNo);
        QuestionDTO questionDTO = questionService.read1Quest(qNo);

        model.addAttribute("dto", questionDTO);
        return "questionRead"; // question_read.html 뷰 이름
    }

//    @GetMapping("/questions")
//    public void list(PageRequestDTO pageRequestDTO, Model model, Pageable pageable) {
//        PageResponseDTO<QuestionDTO> responseDTO = questionService.listWithNewQuestion(pageRequestDTO);
//        model.addAttribute("qList", responseDTO);
//    }


    @GetMapping("/questions") // DTO포함해야 표까지 정상적인 출력 가능
    public String list(Pageable pageable, Model model, PageRequestDTO pageRequestDTO) {

        // Set the number of items per page
        int pageSize = 5;
        pageable = PageRequest.of(pageable.getPageNumber(), pageSize);

        // Retrieve a Page of news items using the NewsService
        Page<Question> questionPage = questionService.list(pageable);

        // Add the Page of news items to the model
        model.addAttribute("questionPage", questionPage);

        // Ensure page number is not less than 1
        if (pageRequestDTO.getPage() < 1) {
            pageRequestDTO.setPage(1);
        }

        PageResponseDTO<QuestionDTO> responseDTO = questionService.listWithNewQuestion(pageRequestDTO);
        //DTO때문에 충돌 일어남
        model.addAttribute("qList", responseDTO);

//         Return the view name for rendering
        return "questions";  // questions.html

    }
//    @GetMapping("/questions")
//    public String list(Pageable pageable, Model model, PageRequestDTO pageRequestDTO) {
//        // Set the number of items per page
//        int pageSize = 5;
//        pageable = PageRequest.of(pageable.getPageNumber(), pageSize);
//
//        // Retrieve a Page of news items using the NewsService
//        Page<Question> questionPage = questionService.list(pageable);
//
//        // Ensure page number is not less than 1
//        if (pageRequestDTO.getPage() < 1) {
//            pageRequestDTO.setPage(1);
//        }
//
//        PageResponseDTO<QuestionDTO> responseDTO = questionService.listWithNewQuestion(pageRequestDTO);
//
//        // Update the pageSize to match the actual number of items in the responseDTO
//        pageSize = responseDTO.getDtoList().size();
//        pageable = PageRequest.of(pageable.getPageNumber(), pageSize);
//
//        // Retrieve a Page of news items using the updated pageSize
//        questionPage = questionService.list(pageable);
//
//        // Add the Page of news items to the model
//        model.addAttribute("questionPage", questionPage);
//        model.addAttribute("qList", responseDTO);
//
//        // Return the view name for rendering
//        return "questions";  // questions.html
//    }

}

