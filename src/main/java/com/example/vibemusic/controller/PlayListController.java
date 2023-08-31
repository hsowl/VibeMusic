package com.example.vibemusic.controller;

import com.example.vibemusic.domain.Member;
import com.example.vibemusic.domain.Music;
import com.example.vibemusic.domain.PlayList;
import com.example.vibemusic.dto.*;
import com.example.vibemusic.security.dto.MemberSecurityDTO;
import com.example.vibemusic.service.MemberService;
import com.example.vibemusic.service.MusicService;
import com.example.vibemusic.service.PlayListService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static ognl.DynamicSubscript.mid;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class PlayListController {

    private final PlayListService playListService;
    private final MusicService musicService;
    private final MemberService memberService;


    @GetMapping("/playlist")
    public String showPlaylist(Model model, @AuthenticationPrincipal MemberSecurityDTO authenticatedUser){
        List<PlayList> playlists = playListService.getPlaylist(authenticatedUser);
        model.addAttribute("playlists", playlists);
        log.info("playlists:==========={}",playlists);
        log.info("-----authenticatedUser : {}", authenticatedUser);

        return "playlist"; // playlist.html
    }


//    @GetMapping("/{no}")
//    public String showPlaylist(Model model, @AuthenticationPrincipal MemberSecurityDTO authenticatedUser){
//        List<PlayList> playlists = playListService.getPlaylist(authenticatedUser);
//        model.addAttribute("playlists", playlists);
//
//        log.info("-----authenticatedUser : {}", authenticatedUser);
//
//        return "playlist"; // playlist.html
//    }

    @PostMapping("/playlist/add")
    public String addList(@RequestParam String plName, @AuthenticationPrincipal MemberSecurityDTO authenticatedUser){
        playListService.addPlaylist(plName,authenticatedUser);
        return "redirect:/playlist"; // 리스트 페이지로 리다이렉트
    }


    @PostMapping("/playlist/addToNewPlaylist")
    public String addToNewList(@RequestParam String plName, @RequestParam Long no,@AuthenticationPrincipal MemberSecurityDTO authenticatedUser){

        log.info("plName : {}", plName);
        log.info("authenticatedUser : {}", authenticatedUser);

        PlayList playList = playListService.addPlaylist(plName, authenticatedUser);

        log.info("playList : {}", playList.getPlNo());
        log.info("no : {}", no);

        playListService.addMusicToPlayList(playList.getPlNo(),no);
        return "redirect:/playlist"; // 리스트 페이지로 리다이렉트
    }


//    @GetMapping("/list/{mid}")
//    public String showPlayListsByMemberId(@PathVariable String mid, Model model) {
//        List<PlayList> playLists = playListService.getPlaylist(mid);
//        model.addAttribute("playLists", playLists);
//        return "playlist"; // playlist.html
//    }

}