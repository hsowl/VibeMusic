package com.example.vibemusic.service;

import com.example.vibemusic.domain.Music;
import com.example.vibemusic.dto.MusicDTO;
import com.example.vibemusic.repository.VibeMusicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class VibeMusicServiceImpl implements VibeMusicService{

    private final VibeMusicRepository vibeMusicRepository;
    private final ModelMapper modelMapper;


    @Override
    public MusicDTO readOne(Long bno) {
        Optional<Music> result = vibeMusicRepository.findById(bno);
        Music music = result.orElseThrow();
        MusicDTO musicDTO = modelMapper.map(music,MusicDTO.class);

        return musicDTO;
    }
}
