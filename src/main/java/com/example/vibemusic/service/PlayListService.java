package com.example.vibemusic.service;

import com.example.vibemusic.domain.PlayList;
import com.example.vibemusic.dto.MusicDTO;
import com.example.vibemusic.dto.PageResponseDTO;

import java.util.List;

public interface PlayListService {

    void addPlaylist(String plName);

    List<PlayList> getPlaylist();
}
