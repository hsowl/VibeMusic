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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
    @GetMapping({"/questionRead","/questionModify"})
    public void qRead(@RequestParam Long qNo, Model model,PageRequestDTO pageRequestDTO) {
        log.info("qNo=============> : {}", qNo);
        QuestionDTO questionDTO = questionService.read1Quest(qNo);

        model.addAttribute("dto", questionDTO);
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

    @PostMapping("/questionModify")
    public String qModify(@RequestParam
            PageRequestDTO pageRequestDTO,  //페이지 요청에 관련된 데이터를 담고 있는 DTO
            @Valid QuestionDTO QuestionDTO,       //수정할 게시글의 데이터를 담고 있는 DTO
            BindingResult bindingResult,    //데이터 유효성 검사 결과를 저장하는 객체


            RedirectAttributes redirectAttributes) {
//              //리다이렉트 시에 속성(attribute)를 전달하기 위한 객체

        log.info("question modify post.." + QuestionDTO);

        if(bindingResult.hasErrors()){

            String link = pageRequestDTO.getLink();

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            redirectAttributes.addAttribute("qNo",QuestionDTO.getQNo());//수정할 게시글의 식별번호(qno)를 리다이렉트 파라미터로 추가

            return "redirect:/questionModify?"+link;
        }

        questionService.modQuest(QuestionDTO);

        redirectAttributes.addFlashAttribute("result","modified");

        redirectAttributes.addAttribute("qno", QuestionDTO.getQNo());

        return "redirect:/questions";
    }

//    @GetMapping("/questionModify")
//    public String qModify(@RequestParam Long qNo, Model model, PageRequestDTO pageRequestDTO) {
//        log.info("qNo=============> : {}", qNo);
//        QuestionDTO questionDTO = questionService.read1Quest(qNo);
//
//        model.addAttribute("dto", questionDTO);
//        return "questionModify"; // question_modify.html 뷰 이름
//    }

    @PostMapping("/questionRemove")
    public String questionRemove(Long qno, RedirectAttributes redirectAttributes){
        //bno: 삭제할 게시글의 식별번호(Long 타입)입니다.
        //리다이렉트 시에 속성(attribute)를 전달
        log.info("remove post" + qno);

        questionService.removeQuest(qno); //boardService.remove(bno) 메서드를 호출하여 해당 식별번호의 게시글을 삭제
        redirectAttributes.addFlashAttribute("result","removed"); //삭제 성공을 나타내는 속성을 리다이렉트 속성에 추가


        return "redirect:/questions";
        //게시글 목록 페이지로 리다이렉트합니다.

//    @GetMapping("/questions") //무시
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
}

