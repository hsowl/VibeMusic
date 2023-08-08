package com.example.vibemusic.controller;

import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.dto.VibeMusicDTO;
import com.example.vibemusic.service.VibeMusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping
@Log4j2
@RequiredArgsConstructor
public class VibeMusicController {

    private final VibeMusicService vibeMusicService;

    @GetMapping({"/index","/blog","/contact","/elements","/login","/event","/blog","/albums-store"})
    public void main() {

    }

}
