package com.example.vibemusic.service;

import com.example.vibemusic.domain.Music;
import com.example.vibemusic.dto.MusicDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MusicServiceImpl implements MusicService {

    private final MusicRepository musicRepository;
    private final ModelMapper modelMapper;


    @Override
    public MusicDTO readOne(Long bno) {
        Optional<Music> result = musicRepository.findById(bno);
        Music music = result.orElseThrow();
        MusicDTO musicDTO = modelMapper.map(music, MusicDTO.class);
        log.info("rDate = {}", musicDTO.getRDate());

        return musicDTO;
    }

    @Override
    public PageResponseDTO<MusicDTO> listWithPaging(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<Music> result = musicRepository.searchAll(types, keyword, pageable);

        List<MusicDTO> dtoList = result.getContent().stream().map(music -> modelMapper.map(music, MusicDTO.class)).collect(Collectors.toList());

        PageResponseDTO<MusicDTO> pageResponseDTO = new PageResponseDTO<>(pageRequestDTO, dtoList, (int) result.getTotalElements());

        return pageResponseDTO;
    }

    @Override
    public PageResponseDTO<MusicDTO> listWithNewMusic(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("rDate");

        Page<Music> result = musicRepository.searchAll(types, keyword, pageable);

        List<MusicDTO> dtoList = result.getContent().stream().map(music -> modelMapper.map(music, MusicDTO.class)).collect(Collectors.toList());

        PageResponseDTO<MusicDTO> pageResponseDTO = new PageResponseDTO<>(pageRequestDTO, dtoList, (int) result.getTotalElements());

        return pageResponseDTO;
    }

    @Override
    public List<MusicDTO> DanceGenre(Long no) {
        List<Music> danceMusics = musicRepository.findBymGenre("dance");

        List<MusicDTO> musicDTO = danceMusics.stream()
                .map(music -> modelMapper.map(music, MusicDTO.class))
                .collect(Collectors.toList());

        return musicDTO;
    }

    @Override
    public List<MusicDTO> BalladGenre(Long no) {
        List<Music> danceMusics = musicRepository.findBymGenre("Ballad");

        List<MusicDTO> musicDTO = danceMusics.stream()
                .map(music -> modelMapper.map(music, MusicDTO.class))
                .collect(Collectors.toList());

        return musicDTO;
    }


    @Override
    public List<MusicDTO> HipHopGenre(Long no) {
        List<Music> danceMusics = musicRepository.findBymGenre("hiphop");

        List<MusicDTO> musicDTO = danceMusics.stream()
                .map(music -> modelMapper.map(music, MusicDTO.class))
                .collect(Collectors.toList());

        return musicDTO;
    }


    @Override
    public List<MusicDTO> PopGenre(Long no) {
        List<Music> danceMusics = musicRepository.findBymGenre("pop");

        List<MusicDTO> musicDTO = danceMusics.stream()
                .map(music -> modelMapper.map(music, MusicDTO.class))
                .collect(Collectors.toList());

        return musicDTO;
    }

}




