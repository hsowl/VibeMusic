package com.example.vibemusic.controller;



import com.example.vibemusic.domain.News;
import com.example.vibemusic.domain.Question;

import com.example.vibemusic.dto.MusicDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.dto.QuestionDTO;

import com.example.vibemusic.service.AnswerService;
import com.example.vibemusic.service.QuestionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Log4j2
@Transactional
public class QuestionController {

    private final QuestionService questionService;

    private final AnswerService answerService;

//    @GetMapping("/questionRead")
//    public void qRead(Long qNo, Model model) {
//        log.info("qNo=============> : {}", qNo);
//        QuestionDTO questionDTO = questionService.read1Quest(qNo);
//
//        model.addAttribute("dto", questionDTO);
//    }
    @GetMapping({"/questionRead","/questionModify"})
    public void qRead(@RequestParam Long qNo, Model model, PageRequestDTO pageRequestDTO) {
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
        int pageSize = 12;
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
    public String modQ(
            @Valid QuestionDTO questionDTO,       //수정할 게시글의 데이터를 담고 있는 DTO
            BindingResult bindingResult,    //데이터 유효성 검사 결과를 저장하는 객체
            PageRequestDTO pageRequestDTO,  //페이지 요청에 관련된 데이터를 담고 있는 DTO
            RedirectAttributes redirectAttributes) {
//              //리다이렉트 시에 속성(attribute)를 전달하기 위한 객체

        log.info("question modify post.." + questionDTO);

        if(bindingResult.hasErrors()){

            String link = pageRequestDTO.getLink();
            log.info("got link");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            redirectAttributes.addAttribute("qNo",questionDTO.getQNo());//수정할 게시글의 식별번호(qno)를 리다이렉트 파라미터로 추가

            return "redirect:/questionModify?"+link;
        }

        questionService.modQuest(questionDTO);

        redirectAttributes.addFlashAttribute("result","modified");
        redirectAttributes.addAttribute("qNo", questionDTO.getQNo());

        return "redirect:/questionRead";


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
    public String removeQ(@RequestParam Long qNo, RedirectAttributes redirectAttributes){
        //bno: 삭제할 게시글의 식별번호(Long 타입)입니다.
        //리다이렉트 시에 속성(attribute)를 전달
        log.info("remove post" + qNo);

        questionService.removeQuest(qNo); //boardService.remove(qNo) 메서드를 호출하여 해당 식별번호의 게시글을 삭제
        redirectAttributes.addFlashAttribute("result","removed"); //삭제 성공을 나타내는 속성을 리다이렉트 속성에 추가


        return "redirect:/questions";
        //게시글 목록 페이지로 리다이렉트합니다.
    }

    @GetMapping("/questionRegister")
    public void registerQuestGET(){

        log.info("=============================등록GET============================");

    }

    @PostMapping("/questionRegister")
    public String registerQuest(
            @Valid QuestionDTO questionDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){
        // @Valid : 어노테이션을 사용하여 boardDTO 매개변수의 값이 유효한지 검증하여 유효성 검사를 통과한 데이터만 처리됨
        // bindingResult: 유효성 검사 결과를 저장하는 객체입니다. 만약 boardDTO의 유효성 검사에서 오류가 발생하면 해당 오류 정보가 이 객체에 저장됩니다.
        // RedirectAttributes : 리다이렉트 시 데이터를 전달하기 위한 객체입니다. 리다이렉트된 페이지에서 데이터를 받을 수 있습니다. 성공 메시지를 리다이렉트로 전달합니다.

        log.info("---------------------------------quest POST register--------------------------");

        log.info("questionDTO : {}", questionDTO.getQTitle());


        if(bindingResult.hasErrors()){ //유효성 검사 결과를 확인하여 오류가 있는지 체크함
            log.info("has errors");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            // 오류 정보를 redirectAttributes에 추가하여 다음 페이지로 전달합니다.

            return "redirect:/questionRegister";
        }

        log.info(questionDTO);

        Long qNo =questionService.registerQuest(questionDTO);


        redirectAttributes.addFlashAttribute("result", qNo);
        return "redirect:/questions";
         }


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

