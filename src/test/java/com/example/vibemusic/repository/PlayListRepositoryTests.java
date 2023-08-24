package com.example.vibemusic.repository;

import com.example.vibemusic.domain.Music;
import com.example.vibemusic.domain.PlayList;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class PlayListRepositoryTests {

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private PlayListRepository playListRepository;

    @Test
    public void testInsert() {
        PlayList playlist = playListRepository.findById(1L).orElse(null);
        Music music = musicRepository.findById(1L).orElse(null);

        if (playlist != null && music != null) {
//            playlist = PlayList.builder()
//                    .pl_no(playlist.getPl_no())
//
//                    .music((List<PlayList>) playlist)
//                    .build();

            playlist = music.getPlayLists().get(1);

            PlayList save = playListRepository.save(playlist);

            log.info("save===========>"+save);
        }
    }
    @Test
    public void testReadOne() {
        Optional<Music> one = musicRepository.findById(1L);
        Music music = one.orElseThrow();
        log.info("one=========================>"+music);

    }
}
