package com.example.vibemusic.service;

import com.example.vibemusic.dto.MusicDTO;

import java.util.List;

public interface ChartService {

    List<MusicDTO> DanceGenre(Long no);

    List<MusicDTO> BalladGenre(Long no);

    List<MusicDTO> HipHopGenre(Long no);

    List<MusicDTO> PopGenre(Long no);


}
