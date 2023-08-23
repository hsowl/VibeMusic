package com.example.vibemusic.service;

import com.example.vibemusic.domain.Music;
import com.example.vibemusic.domain.PlayList;
import com.example.vibemusic.repository.MusicRepository;
import com.example.vibemusic.repository.PlayListRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class PlayListServiceImpl implements PlayListService {

    private final PlayListRepository playListRepository;
    private final MusicRepository musicRepository;

    @Autowired
    public PlayListServiceImpl(PlayListRepository playListRepository, MusicRepository musicRepository) {
        this.playListRepository = playListRepository;
        this.musicRepository = musicRepository;
    }


    /**
     * *********** PlayList 생성 부분 ***************
     * PlayList별 목록 보여주기
     */
    public List<PlayList> getPlaylist() {
        return playListRepository.findAll();
    }

    /**
     PlayList 목록 추가하기
     */
    public void addPlaylist(String plName) {
        PlayList playListItem = new PlayList();
        playListItem.setPlName(plName);
        playListRepository.save(playListItem);
    }

    /**
     PlayList 목록 삭제하기
     */
    public void removePlaylist(Long plNo) {
        playListRepository.deleteById(plNo);
    }



    /**
     * *********** 생성된 PlayList에 노래담기 부분 ***************
     PlayList에 넣은 노래들 목록보기
     */
    public List<Music> getMusicsInPlayList(Long plNo){
        PlayList playList = playListRepository.findById(plNo).orElseThrow(EntityNotFoundException::new);
        return playList.getMusics();
    }

    /**
     PlayList에 노래 한곡 추가하기
     */
    public void addMusicToPlayList(Long plNo, Long no) {
        PlayList playList = playListRepository.findById(plNo).orElseThrow(EntityNotFoundException::new);
        Music music = musicRepository.findById(no).orElseThrow(EntityNotFoundException::new);

        playList.getMusics().add(music);
        playListRepository.save(playList);
    }

    /**
     PlayList에 노래 여러곡 추가하기
     */
    public void addAllToPlayList(Long plNo, List<Long> nos) {
        PlayList playList = playListRepository.findById(plNo).orElseThrow(EntityNotFoundException::new);
        List<Music> musicList = new ArrayList<>();
        //nos.add();

        playList.getMusics().addAll(musicList);
        playListRepository.save(playList);
    }

    /**
     PlayList에 노래 삭제하기
     */
    public void removeMusicFromPlayist(Long plNo, Long no) {
        PlayList playList = playListRepository.findById(plNo).orElseThrow(EntityNotFoundException::new);
        Music music = musicRepository.findById(no).orElseThrow(EntityNotFoundException::new);

        playList.removeMusic(music);
        playListRepository.save(playList);
    }

}