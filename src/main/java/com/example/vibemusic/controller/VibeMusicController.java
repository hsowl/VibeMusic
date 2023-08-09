package com.example.vibemusic.controller;

import com.example.vibemusic.dto.MusicDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.dto.VibeMusicDTO;
import com.example.vibemusic.service.VibeMusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping
@Log4j2
@RequiredArgsConstructor
public class VibeMusicController {

    private final VibeMusicService vibeMusicService;

    @GetMapping({"/index","/blog","/contact","/elements","/login","/event","/blog","/albums-store" ,"/testIndex", "layout","playerbar"})
    public void main() {

    }

    @GetMapping("/test")
    public void test(Model model) {
        MusicDTO musicDTO = vibeMusicService.readOne(1L);
        model.addAttribute("dto",musicDTO);
    }

}
