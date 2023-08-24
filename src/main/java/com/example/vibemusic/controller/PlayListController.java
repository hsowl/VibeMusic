package com.example.vibemusic.controller;

import com.example.vibemusic.dto.NewsDTO;
import com.example.vibemusic.service.PlayListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class PlayListController {

    private final PlayListService playListService;

    @GetMapping("/playlist")
    public void main(){

    }
}
