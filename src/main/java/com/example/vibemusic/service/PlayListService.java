package com.example.vibemusic.service;

import com.example.vibemusic.domain.Member;
import com.example.vibemusic.domain.PlayList;
import com.example.vibemusic.security.dto.MemberSecurityDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface PlayListService {

    //개인 플레이 리스트 만들기
    void addPlaylist(String plName);

    //플레이 리스트 다 모아서 보여주기
    List<PlayList> getPlaylist(@AuthenticationPrincipal MemberSecurityDTO mid);

    void addMusicToPlayList(Long plNo, Long no);

}