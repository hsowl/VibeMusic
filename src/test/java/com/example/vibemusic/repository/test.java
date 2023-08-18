package com.example.vibemusic.repository;

import com.example.vibemusic.domain.Music;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class test {

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private PlayListRepository playListRepository;

    @Test
    public void testRead() {
        Optional<Music> byId = musicRepository.findById(1L);
        Music music = byId.orElseThrow();
//        log.info("glglglglgllglglg===========> {}",music.getM_title());



    }
}
