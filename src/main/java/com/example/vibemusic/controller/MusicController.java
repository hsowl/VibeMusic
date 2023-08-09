package com.example.vibemusic.controller;

import com.example.vibemusic.dto.MusicDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.service.MusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping({"/index","/blog","/contact","/elements","/login","/event","/blog","/testIndex", "layout","playerbar"})
    public void main() {

    }

    @GetMapping("/test")
    public void test(Model model) {
        MusicDTO musicDTO = musicService.readOne(1L);
        model.addAttribute("dto",musicDTO);
    }

    @GetMapping("/albums-store")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<MusicDTO> responseDTO = musicService.listWithPaging(pageRequestDTO);
        log.info("responseDTO : {}",responseDTO.getDtoList());
        model.addAttribute("responseDTO", responseDTO);
    }

}
