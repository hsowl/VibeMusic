package com.example.vibemusic.controller;

import com.example.vibemusic.domain.PlayList;
import com.example.vibemusic.security.dto.MemberSecurityDTO;
import com.example.vibemusic.service.PlayListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class PlayListController {

    private final PlayListService playListService;


    @GetMapping("/playlist")
    public String showPlaylist(Model model, @AuthenticationPrincipal MemberSecurityDTO authenticatedUser){
        List<PlayList> playlists = playListService.getPlaylist(authenticatedUser);
        model.addAttribute("playlists", playlists);
        log.info("playlists:==========={}",playlists);
        log.info("-----authenticatedUser : {}", authenticatedUser);

        return "playlist"; // playlist.html
    }

    @PostMapping("/playlist/add")
    public String addList(@RequestParam String plName, @AuthenticationPrincipal MemberSecurityDTO authenticatedUser){
        playListService.addPlaylist(plName,authenticatedUser);
        return "redirect:/playlist"; // 리스트 페이지로 리다이렉트
    }

    @GetMapping("/playlist/addToExistPlaylist")
    public String addToExistList(@RequestParam Long plno, @RequestParam Long no, @AuthenticationPrincipal MemberSecurityDTO authenticatedUser){
        log.info("plNo......GET{}", plno);
        log.info("no......GET{}", no);

        List<PlayList> ListPlaylist = playListService.getPlaylist(authenticatedUser);
        log.info("ListPlaylist ---------- {}", ListPlaylist);

        playListService.addMusicToPlayList(plno,no);
        return "redirect:/playlist"; // 리스트 페이지로 리다이렉트
    }

    @PostMapping("/playlist/addToNewPlaylist")
    public String addToNewList(@RequestParam String plName, @RequestParam Long no, @AuthenticationPrincipal MemberSecurityDTO authenticatedUser, RedirectAttributes redirectAttributes){

        log.info("plName : {}", plName);
        log.info("authenticatedUser : {}", authenticatedUser);

        PlayList playList = playListService.addPlaylist(plName, authenticatedUser);

        if(playList.getPlName() == plName){
            log.info("playList : {}", playList.getPlNo());
            log.info("no : {}", no);
            redirectAttributes.addFlashAttribute("failureMessage", "같은 이름의 PlayList가 이미 존재합니다. 다른 이름을 지정해 주세요.");
        } else{
            playListService.addMusicToPlayList(playList.getPlNo(),no);
        }

        return "redirect:/playlist"; // 리스트 페이지로 리다이렉트
    }

    @GetMapping("/playlist/deletePlaylist")
    public String deletePlaylist(@RequestParam Long plno, @AuthenticationPrincipal MemberSecurityDTO authenticatedUser){
        log.info("plNo......GET{}", plno);

        playListService.removePlaylist(plno);
        return "redirect:/playlist"; // 리스트 페이지로 리다이렉트
    }

}