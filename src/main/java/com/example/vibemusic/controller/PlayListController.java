package com.example.vibemusic.controller;

import com.example.vibemusic.domain.Music;
import com.example.vibemusic.domain.PlayList;
import com.example.vibemusic.dto.MusicDTO;
import com.example.vibemusic.dto.NewsDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.service.MusicService;
import com.example.vibemusic.service.PlayListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class PlayListController {

    private final PlayListService playListService;
    private final MusicService musicService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/playlist")
    public String addList(@RequestParam String plName){
        playListService.addPlaylist(plName);
        return "redirect:/playlist"; // 리스트 페이지로 리다이렉트
    }
}
