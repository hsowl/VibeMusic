package com.example.vibemusic.controller;

import com.example.vibemusic.domain.Answer;
import com.example.vibemusic.dto.AnswerDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.dto.ReplyDTO;
import com.example.vibemusic.service.AnswerService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/answers")
@Log4j2
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @ApiOperation(value = "Answers POST", notes = "POST 방식으로 댓글 등록")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> register(@Valid @RequestBody AnswerDTO answerDTO, BindingResult bindingResult) throws BindException {

        log.info("qno =========================================> {}", answerDTO.getQno());

        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        log.info("qno : {}", answerDTO.getQno());

        Map<String, Long> resultMap = new HashMap<>();

        Long ano = answerService.answerRegister(answerDTO);

        resultMap.put("ano",ano);

        return resultMap;
    }

    @ApiOperation(value = "answers of Question", notes = "Get 방식으로 특정 게시물의 댓글 목록")
    @GetMapping(value = "/answerList/{qno}")
    public PageResponseDTO<AnswerDTO> getList(@PathVariable("qno") Long qno, PageRequestDTO pageRequestDTO){
        PageResponseDTO<AnswerDTO> responseDTO = answerService.getListOfQuestion(qno,pageRequestDTO);
                                                                //메소드만들어
        return responseDTO;
    }

    @ApiOperation(value = "Read Answer", notes = "GET 방식으로 특정 댓글 조회")
    @GetMapping("/{ano}")
    public AnswerDTO getAnswerDTO(@PathVariable("ano") Long ano){
        AnswerDTO answerDTO = answerService.answerRead(ano);
        return answerDTO;
    }

    @ApiOperation(value = "Delete Answer", notes = "DELETE 방식으로 특정 댓글 삭제")
    @DeleteMapping("/{ano}")
    public Map<String, Long> remove(@PathVariable("ano") Long ano){
        answerService.answerRemove(ano);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("ano",ano);
        return resultMap;
    }
    @ApiOperation(value = "Modify Answer", notes = "Put 방식으로 댓글 수정")
    @PutMapping(value = "/{ano}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public  Map<String, Long> remove(@PathVariable("ano") Long ano, @RequestBody AnswerDTO answerDTO){
        answerDTO.setAno(ano);
        answerService.answerModify(answerDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("ano", ano);
        return resultMap;
    }


    }


