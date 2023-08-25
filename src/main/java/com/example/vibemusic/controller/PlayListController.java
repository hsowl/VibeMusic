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
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ognl.DynamicSubscript.mid;

@Controller
@RequestMapping("/playlist")
@RequiredArgsConstructor
@Slf4j
public class PlayListController {

    private final PlayListService playListService;
    private final MusicService musicService;


    @GetMapping
    public String viewPlaylist(String mid, Model model){
        List<PlayList> playlists = playListService.getPlaylist(mid);
        model.addAttribute("playlists", playlists);
        return "playlist"; // playlist.html
    }

    @PostMapping("/add")
    public String addList(@RequestParam String plName){
        playListService.addPlaylist(plName);
        return "redirect:/playlist"; // 리스트 페이지로 리다이렉트
    }

//    @GetMapping("/list/{mid}")
//    public String showPlayListsByMemberId(@PathVariable String mid, Model model) {
//        List<PlayList> playLists = playListService.getPlaylist(mid);
//        model.addAttribute("playLists", playLists);
//        return "playlist"; // playlist.html
//    }
}
