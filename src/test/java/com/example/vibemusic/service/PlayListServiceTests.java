package com.example.vibemusic.service;

import com.example.vibemusic.domain.Music;
import com.example.vibemusic.domain.PlayList;
import com.example.vibemusic.repository.MusicRepository;
import com.example.vibemusic.repository.PlayListRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Slf4j
public class PlayListServiceTests {

    @Autowired
    private PlayListRepository playListRepository;

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private PlayListServiceImpl playListServiceImpl;

    @Test  //PlayList 만듬
    public void addPlaylistTest() {

        PlayList playList = PlayList.builder()
                .plNo(1L)
                .plName("JM's Choice")
                .build();

        playListRepository.save(playList);
    }

    public void addMusicToPlayList(Long plNo, Long no) {
        PlayList playList = playListRepository.findById(plNo).orElseThrow(EntityNotFoundException::new);
        Music music = musicRepository.findById(no).orElseThrow(EntityNotFoundException::new);

        playList.getMusics().add(music);
        playListRepository.save(playList);
    }

    @Test  //PlayList 안에 한곡 넣기
    public void addMusicToPlayListTest() {

        Long plNo = 1L; // 플레이리스트 번호
        Long no = 1L; // 곡 번호

        addMusicToPlayList(plNo, no);
    }

    public void addAllToPlayList(Long plNo, List<Long> nos) {
        PlayList playList = playListRepository.findById(plNo).orElseThrow(EntityNotFoundException::new);
        List<Music> musicList = musicRepository.findAllById(nos);

        playList.getMusics().addAll(musicList);
        playListRepository.save(playList);
    }

    @Test  //PlayList 안에 여러곡 선택 후 넣기
    public void addAllToPlayListTest() {
        Long plNo = 2L; // 플레이리스트 번호
        List<Long> nos = Arrays.asList(1L, 2L, 3L); // 곡 번호들

        addAllToPlayList(plNo, nos);
    }
}
