package com.example.vibemusic.controller;

import com.example.vibemusic.domain.Reply;
import com.example.vibemusic.dto.MusicDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.service.MusicService;
import com.example.vibemusic.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@Slf4j
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;
    private final ReplyService replyService;

    @GetMapping({"/contact","/elements","/login","/event","/testIndex", "layout","playerbar","/questionList"})
    public void main() {

    }

    @GetMapping("/test")
    public void test(Model model) {
        MusicDTO musicDTO = musicService.readOne(1L);
        model.addAttribute("dto",musicDTO);
    }

    @GetMapping("/albums-store")
    public void list(PageRequestDTO pageRequestDTO, Model model, Pageable pageable){
        PageResponseDTO<MusicDTO> responseDTO = musicService.listWithPaging(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/read")
    public void readOne(Long no, Model model){
        MusicDTO musicDTO = musicService.readOne(no);
        model.addAttribute("dto",musicDTO);
    }

    @GetMapping("/index")
    public void listWithNewMusic(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<MusicDTO> responseDTO = musicService.listWithNewMusic(pageRequestDTO);
        model.addAttribute("responseDTO",responseDTO);

    }

}
