package com.example.vibemusic.service;

import com.example.vibemusic.dto.MusicDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;

import java.util.List;

public interface MusicService {
    MusicDTO readOne(Long bno);

    PageResponseDTO<MusicDTO> listWithPaging(PageRequestDTO pageRequestDTO);

    PageResponseDTO<MusicDTO> listWithNewMusic(PageRequestDTO pageRequestDTO);


}
