package com.example.vibemusic.controller;

import com.example.vibemusic.dto.MemberJoinDTO;
import com.example.vibemusic.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public void loginGET(String errorCode, String logout){
        log.info("-------login get-------");
        log.info("-------logout : {}", logout);

        if(logout != null){
            log.info("user logout.......................");
        }
    }


    @GetMapping("/join")
    public void joinGET() {
        log.info("join get....");
    }

    @PostMapping("/join")
    public String joinGET(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes){
        log.info("join post..........");
        log.info("memberJoinDTO : {}", memberJoinDTO);

        try{
            memberService.join(memberJoinDTO);
        }catch (MemberService.MidExistException e){

            redirectAttributes.addFlashAttribute("error","mid");
            return "redirect:/join";
        }
        redirectAttributes.addFlashAttribute("result","success");

        return "redirect:/login";
    }

}
